#
# Handle intelligent and mostly transparent processing of
# dts files (including macro expanded #include files with the
# c preprocessor) into .dtbo files on the target.
#
# Just handle making sure any .dtsi's and .h's needed are in
# the recipe proper, or in the specifiec DTS_INCLUDES pathlist and
# it will handle the rest for you.
#

DEPENDS += "dtc-native"

DTBO_INSTALL_PATH ?= "/etc/dtbo"

# Provide some default pathing for the recipe.  The recipe either appends
# to this whitespace separated list of paths, or the dev must specify it
# all for full control of includes.  We include a few "common" answers for
# this for like GPIO lines and pinctrl so you have a core ability to handle
# many of the common answers for DTBO use.
DTS_INCLUDES ?= " \
    ${S} \
    ${RECIPE_SYSROOT}/usr/include \
    ${RECIPE_SYSROOT}/usr/include/linux \
    ${STAGING_KERNEL_DIR}/include \
    ${STAGING_KERNEL_DIR}/include/dt-bindings/gpio \
    ${STAGING_KERNEL_DIR}/include/dt-bindings/pinctrl \
    "
S ?= "${WORKDIR}/device-tree"
B ?= "${WORKDIR}/build"

# Handle, transparently, unpacking any specced things from the workdir that's an .h, .dts, or .dtsi
# (This allows for mixed solutions where it's not in a git repo- and it allows someone to override
#  our default and properly do this to THAT.)
#
# It's not that you have to...but it lets us be flexible and handle most of the types of attempted
# use cases of this .bbclass.  Make sure your licensing file is in COPYING or LICENSE if you're
# leveraging the ${WORKDIR} helper here.  We don't support, currently, any other types of naming.
dtbo_unpack_workdir () {
    cd ${WORKDIR}
    for FILETYPE in COPYING LICENSE *.h *.dts *.dtsi ; do
        if [ -e ${FILETYPE} ] ; then
            cp -f ${FILETYPE} ${S}
        fi
    done
}
do_unpack[postfuncs] += " dtbo_unpack_workdir "

# Allow for the ability to strip symbolics from the dataset.  This is to compensate for
# HW targets or families thereof doing the wrong things with the static DTB.  We presume
# symbols by default.  This can be explicitly full-on overridden or modified by override
# syntax expressions.
SYMBOL_COMPILE ?= "-@"

# Force the kernel sources to be unpacked for us regardless of shared state...
do_compile[depends] += "virtual/kernel:do_shared_workdir"

do_compile() {
    mkdir -p ${B}
    cd ${B}
    # Supply a bit of machine info to let this work.  You MAY have to extend
    # the case statement in some cases for new CPU arches not previously supported.
    MACHINE_INFO="-D__${TARGET_ARCH}__"
    case ${TARGET_ARCH} in
        aarch64 )
            # Add __LP64__ to things, we're ONLY ever 64-bits here.
            MACHINE_INFO="$MACHINE_INFO -D__LP64__"
            ;;
    esac
    CPP_INCLUDES=""
    DTC_INCLUDES=""
    for INC_PATH in ${DTS_INCLUDES} ; do
        CPP_INCLUDES="$CPP_INCLUDES -I$INC_PATH"
        DTC_INCLUDES="$DTC_INCLUDES -i $INC_PATH"
    done
    if [ `ls -1 ${WORKDIR}/*.dts 2>/dev/null | wc -l` -ne 0 ]  ; then
        for dtsfile in ${S}/*.dts ; do
            dtsbase=`basename $dtsfile`
            dtsname="${dtsbase%.*}"
            cpp -nostdinc $MACHINE_INFO $CPP_INCLUDES -I. -undef -x assembler-with-cpp $dtsfile > $dtsname.dtspp
            ${RECIPE_SYSROOT_NATIVE}/usr/bin/dtc ${SYMBOL_COMPILE} -I dts -O dtb $DTC_INCLUDES -o $dtsname.dtbo $dtsname.dtspp
        done
    else
        bbwarn "No .dts files found, skipping dtc stage."
    fi
}

do_install() {
    if [ `ls -1 ${B}/*.dtbo 2>/dev/null | wc -l` -ne 0 ]  ; then
        install -m 0755 -d ${D}${DTBO_INSTALL_PATH}
        for dtbofile in ${B}/*.dtbo ; do
            install -m 0644 $dtbofile ${D}${DTBO_INSTALL_PATH}
        done
    else
        bbwarn "No .dtbo files found, skipping install stage."
    fi
}

FILES_${PN} += "{DTBO_INSTALL_PATH}"
