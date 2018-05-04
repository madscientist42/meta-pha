SECTION = "kernel"
DESCRIPTION = "Mainline Linux kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"
COMPATIBLE_MACHINE = "(sun4i|sun5i|sun7i|sun8i|sun50i)"

inherit kernel

require linux.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

# Since we're not using git, this doesn't make a difference, but we need to fill
# in something or kernel-yocto.bbclass will fail.
KBRANCH ?= "master"

# We depend on having all of openssl in the native leg for this...
DEPENDS += "openssl-native"

# Pull in the devicetree files into the rootfs
RDEPENDS_${KERNEL_PACKAGE_NAME}-base += "kernel-devicetree"

KERNEL_EXTRA_ARGS += "STAGING_LIBDIR_NATIVE=${STAGING_LIBDIR_NATIVE} \
		      STAGING_INCDIR_NATIVE=${STAGING_INCDIR_NATIVE} \
                      LOADADDR=${UBOOT_ENTRYPOINT}"

S = "${WORKDIR}/linux-${PV}"
	
SRC_URI[md5sum] = "036169df65e9153b9ffc2c801522280e"
SRC_URI[sha256sum] = "fa919108d37f6b5dec85ce3fde3bc76eabdfc93b0b8c8386878ad8fb46168a4b"

SRC_URI = "https://www.kernel.org/pub/linux/kernel/v4.x/linux-${PV}.tar.xz \
	file://00000-build-with-openssl-native.patch \
	file://00001-neo-plus2-fixes.patch \
	file://00002-neo-plus2-fixes2.patch \
	file://00003-neo-plus2-enable-bt-uart.patch \
        file://defconfig \
        "



# The LD_LIBRARY_PATH variable is not set when building kernel.
# Work around this by adding the build's sysroot libraries to LD_LIBRARY_PATH.
kernel_do_compile_prepend(){
	export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:${STAGING_LIBDIR_NATIVE}:${STAGING_LIBDIR_NATIVE}/../../lib
}

do_install_prepend(){
	export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:${STAGING_LIBDIR_NATIVE}:${STAGING_LIBDIR_NATIVE}/../../lib
}
