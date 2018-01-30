# Extend this to understand cross support so you can nab/build gems right...
BBCLASSEXTEND += " cross"

# We depend on the native environment being there for this to work...
DEPENDS_class-cross = "ruby-native"

# Call it "ruby-cross-<arch>...
PN_class-cross = "ruby-cross-${TARGET_ARCH}"

RPROVIDE_class-cross = "${PN_class-cross}"

# Tell it how to compile ruby-cross-<arch> images...
EXTRA_OECONF_class-cross = "\
    --disable-versioned-paths \
    --disable-rpath \
    --enable-shared \
    --enable-load-relative \
    --host=${TARGET_SYS} \
    --libdir=${STAGING_DIR_TARGET}${target_libdir} \
    --includedir=${STAGING_DIR_TARGET}${target_includedir} \
    --bindir='$''{exec_prefix}/bin' \
    --sbindir='$''{exec_prefix}/sbin' \
    --libexecdir='$''{exec_prefix}/libexec' \
"

# This snippet lets compiled extensions which rely on external libraries,
# such as zlib, compile properly.  If we don't do this, then when extmk.rb
# runs, it uses the native libraries instead of the target libraries, and so
# none of the linking operations succeed -- which makes extconf.rb think
# that the libraries aren't available and hence that the extension can't be
# built.
do_configure_prepend() {
    sed -i "s#%%TARGET_CFLAGS%%#$TARGET_CFLAGS#; s#%%TARGET_LDFLAGS%%#$TARGET_LDFLAGS#" ${S}/common.mk
    rm -rf ${S}/ruby/
}


do_install_class-cross() {
    export CPPFLAGS_FROM_CROSS="${CPPFLAGS}"
    export CPPFLAGS_FOR_TARGET="${TARGET_CPPFLAGS}"
    export STRIP_FOR_TARGET="${TARGET_SYS}-strip"
    export NM_FOR_TARGET="${TARGET_SYS}-nm"
    export OBJDUMP_FOR_TARGET="${TARGET_SYS}-objdump"
    export AS_FOR_TARGET="${TARGET_SYS}-as"
    export AR_FOR_TARGET="${TARGET_SYS}-ar"
    export RANLIB_FOR_TARGET="${TARGET_SYS}-ranlib"
    export CPP_FOR_TARGET="${TARGET_SYS}-gcc -E --sysroot=${STAGING_DIR_TARGET} ${TARGET_CC_ARCH}"
    export CXXFLAGS_FROM_CROSS="${CXXFLAGS}"
    export CXXFLAGS_FOR_TARGET="${TARGET_CXXFLAGS}"
    export CXX_FOR_TARGET="${TARGET_SYS}-g++ --sysroot=${STAGING_DIR_TARGET} ${TARGET_CC_ARCH}"
    export LDFLAGS_FROM_CROSS="${LDFLAGS}"
    export LDFLAGS_FOR_TARGET="${TARGET_LDFLAGS} --sysroot=${STAGING_DIR_TARGET}"
    export CFLAGS_FROM_CROSS="${CFLAGS}"
    export CFLAGS_FOR_TARGET="${TARGET_CFLAGS}"
    export CC_FOR_TARGET="${TARGET_SYS}-gcc --sysroot=${STAGING_DIR_TARGET} ${TARGET_CC_ARCH}"
    export CROSS_TARGET_SYS_DIR="${CROSS_TARGET_SYS_DIR}"
    oe_runmake 'DESTDIR=${D}' install-cross
}

FILES_${PN}-cross = "${libdir}/ruby/*/*"


SYSROOT_PREPROCESS_FUNCS_class-cross += "ruby_cross_populate"

ruby_cross_populate() {
    sysroot_stage_dir ${D}/${STAGING_DIR_NATIVE}${target_libdir} ${SYSROOT_DESTDIR}/${STAGING_DIR_NATIVE}${target_libdir}
}



