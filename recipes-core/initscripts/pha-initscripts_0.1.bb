SUMMARY = "ShadowX specific SysV initscripts"
SECTION = "base"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS = " update-rc.d-native"

PR = "r1"

SRC_URI = " \
    file://brcm_patchram_plus \
	file://enable_batman.sh \
	file://LICENSE \
	"

S = "${WORKDIR}"

INHIBIT_DEFAULT_DEPS = "1"

do_install () {
	# Install all of our initscripts specific to ShadowX
	install -d ${D}${sysconfdir}/init.d
	install -d ${D}${sysconfdir}/rc5.d
	install -m 0755 ${WORKDIR}/brcm_patchram_plus ${D}${sysconfdir}/init.d	
	install -m 0755 ${WORKDIR}/enable_batman.sh ${D}${sysconfdir}/init.d	
	update-rc.d -r ${D} brcm_patchram_plus start 50 5 .
	update-rc.d -r ${D} enable_batman.sh start 95 5 .	
}
