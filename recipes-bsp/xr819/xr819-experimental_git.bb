SUMMARY = "Allwinner XR819 kernel driver (wifi)"
DESCRIPTION = "Experimental XR819 driver derived from Armbian fork of Allwinner's code."
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a23a74b3f4caf9616230789d94217acb"

DEPENDS = "virtual/kernel"

SRC_URI = " \
    git://github.com/karabek/xradio;protocol=https;branch=master \
	file://00001-out-of-kernel-makefile-mods.patch \
	"

SRCREV = "354e8c32e7948d46a63796d0ca266b1f702999b0"

S = "${WORKDIR}/git"

DEPENDS = "virtual/kernel"

inherit module

EXTRA_OEMAKE = "ARCH=${ARCH} -C ${STAGING_KERNEL_BUILDDIR}"

do_compile () {
    unset LDFLAGS
    oe_runmake
}

do_install () {
    install -d ${D}/lib/modules/${KERNEL_VERSION}
    install -m 0755 ${B}/xradio_wlan.ko ${D}/lib/modules/${KERNEL_VERSION}
    install -m 0655 ${S}/firmware/*bin ${D}/lib/firmware/xr819
}

