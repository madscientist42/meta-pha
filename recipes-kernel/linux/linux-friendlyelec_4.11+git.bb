SECTION = "kernel"
DESCRIPTION = "FriendlyElec branch of the Linux kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"
COMPATIBLE_MACHINE = "(nanopi-neo2|nanopi-neo-plus2)"

inherit kernel

require linux.inc

KBRANCH ?= "sunxi-4.11.y"
SRCREV = "492574d41038623d5a41c623c033a30533a462a7"
PV = "4.11+git${SRCPV}"

# Pull in the devicetree files into the rootfs
RDEPENDS_${KERNEL_PACKAGE_NAME}-base += "kernel-devicetree"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

S = "${WORKDIR}/git"
	
SRC_URI[md5sum] = "e1051f6b15d6399a5de2441dd4e15537"
SRC_URI[sha256sum] = "ffc393a0c66f80375eacd3fb177b92e5c9daa07de0dcf947e925e049352e6142"

SRC_URI = " git://github.com/friendlyarm/linux.git;protocol=https;branch=sunxi-4.11.y \
	file://defconfig \
	"
