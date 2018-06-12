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

