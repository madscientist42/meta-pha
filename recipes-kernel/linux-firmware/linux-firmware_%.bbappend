FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

# Declare files we're needing to stage into the firmware directories that
# aren't in linux-firmware (Like the NVRAM config file for Broadcom 
# WiFi chipsets...
SRC_URI += " \
	file://brcmfmac43430-sdio.txt \
	"


# Stage the files into the firmware staging directory...
do_install_prepend() {
	install -d  ${D}${nonarch_base_libdir}/firmware/
	install -d  ${D}${nonarch_base_libdir}/firmware/brcm
	cp ../brcm* ${D}${nonarch_base_libdir}/firmware/brcm
}

# Attach the formerly missing files to the respective packaging 
FILES_${PN}-bcm43430 += " \
	${nonarch_base_libdir}/firmware/brcm/brcmfmac43430-sdio.txt \
	"

