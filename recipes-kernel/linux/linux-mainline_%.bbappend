# Look in the local appended project directory here...
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

# We depend on having all of openssl in the native leg for this...
DEPENDS += "openssl-native"

# There's a few things needing to be done to build against Sumo in Yocto...
SRC_URI += " \
	file://00000-build-with-openssl-native.patch \
	file://00001-neo-plus2-devicetree-fixes.patch \
    "    

# Some of the extra build hints...
KERNEL_EXTRA_ARGS += " \
    STAGING_LIBDIR_NATIVE=${STAGING_LIBDIR_NATIVE} \
    STAGING_INCDIR_NATIVE=${STAGING_INCDIR_NATIVE} \
    "

# And something that should've been copied OVER by kernel.bbclass that **ISN'T**...
do_compile_append() {
    install -d ${STAGING_KERNEL_BUILDDIR}/include
    install -d ${STAGING_KERNEL_BUILDDIR}/include/config
    cp -f ${B}/include/config/auto.conf ${STAGING_KERNEL_BUILDDIR}/include/config
}
