SUMMARY = "PHA specific runit services scripting"
SECTION = "base"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS = "runit"

PR = "r1"

SRC_URI = " \
	file://core-services/02=batman-kmod.sh/run \
	file://LICENSE \
	"
	
# For now, we're going to make this subject to application when we're 
# a Neo-Plus2...
SRC_URI_append_nanopi-neo-plus2 = " \
	file://sv/bt_radio_svc/run \
    "	

inherit runit

RUNIT_SERVICES = " \
    DEFAULT \
    "

install_runit_initscripts() {
    # Set up the core-services...
	install -d -m 0755 ${D}/etc/runit/core-services
	for I in ${WORKDIR}/core-services/* ; do
		install -m 0755 $I ${D}/etc/runit/core-services
	done 
}
do_install[postfuncs] += "${@bb.utils.contains('DISTRO_FEATURES', 'runit', 'install_runit_initscripts', '', d)} "

