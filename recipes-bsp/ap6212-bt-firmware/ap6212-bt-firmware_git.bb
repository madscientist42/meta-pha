SUMMARY = "AMPAK AP6212 Bluetooth Firmware"
DESCRIPTION = "Firmware Images from FriendlyElec's git repositories used by their Ubuntu Core for BT support"
# CLosed for now- need to get the precise license associated with it (It's redistributable, but I don't know specifics yet)
SECTION = "kernel"
LICENSE = "CLOSED"

SRC_URI = " \
	git://github.com/friendlyarm/android_vendor_broadcom_nanopi2.git;protocol=https;branch=nanopi-k2-lollipop \
	"

SRCREV = "f240d3fca2e66804f8be0ad3c037943bc8d9cc63"

S = "${WORKDIR}/git"

PV = "0.1-git+${SRCREV}"

# Just need to copy over files...
do_install() {
	install -d  ${D}${nonarch_base_libdir}/firmware/
	install -d  ${D}${nonarch_base_libdir}/firmware/brcm
	cp -r ${S}/proprietary/*.hcd ${D}${nonarch_base_libdir}/firmware/brcm
}

FILES_${PN} = " \
	/lib \ 
	/lib/firmware \
	/lib/firmware/brcm \
	/lib/firmware/brcm/bcm43438a0.hcd \
	/lib/firmware/brcm/bcm43438a1.hcd \
	"

