SUMMARY = "Allwinner XR819 kernel driver (wifi)"
DESCRIPTION = "Experimental XR819 driver derived from the Armbian fork of Allwinner's code."
HOMEPAGE = "https://github.com/fifteenhex/xradio"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a23a74b3f4caf9616230789d94217acb"

DEPENDS = "virtual/kernel"

SRC_URI = " \
    git://github.com/fifteenhex/xradio;protocol=https;branch=master \
    file://fix-sdio-c-remove-irrelevant-mach-types.patch \
    file://firmware/boot_xr819.bin \
    file://firmware/etf_xr819.bin \
    file://firmware/fw_xr819.bin \
    file://firmware/sdd_xr819.bin \
	"

SRCREV = "6bf0e2e21c80456e2a3d4ad1267caecde7165871"

S = "${WORKDIR}/git"

DEPENDS = "virtual/kernel"

inherit module

EXTRA_OEMAKE = "-C ${STAGING_KERNEL_BUILDDIR} ARCH=${ARCH} M=${S}"


do_install () {
    install -d ${D}/lib/modules/${KERNEL_VERSION}
    install -d ${D}/lib/firmware/xr819
    install -m 0755 ${B}/xradio_wlan.ko ${D}/lib/modules/${KERNEL_VERSION}
    install -m 0655 ${WORKDIR}/firmware/boot_xr819.bin ${D}/lib/firmware/xr819
    install -m 0655 ${WORKDIR}/firmware/etf_xr819.bin ${D}/lib/firmware/xr819
    install -m 0655 ${WORKDIR}/firmware/fw_xr819.bin ${D}/lib/firmware/xr819
    install -m 0655 ${WORKDIR}/firmware/sdd_xr819.bin ${D}/lib/firmware/xr819
}

FILES:${PN} += " \
    /lib/firmware \
    "

