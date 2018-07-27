SUMMARY = "ShadowX specific SysV initscripts"
SECTION = "base"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS = " update-rc.d-native"

PR = "r1"

SRC_URI = " \
	file://enable_batman.sh \
	file://LICENSE \
	"
	
# Add native BT hardware support for the NanoPI Neo-Plus2, possibly others later...	
SRC_URI_append_nanopi-neo-plus2 = " \
	file://enable_bt.sh \
	file://start_bt.sh \
	file://udev_bt.sh \
	file://90-rfkill.rules \
    "    

S = "${WORKDIR}"

INHIBIT_DEFAULT_DEPS = "1"

do_install () {
	# Install all of our initscripts specific to ShadowX
	install -d ${D}${sysconfdir}/init.d
	install -d ${D}${sysconfdir}/rc5.d
	install -m 0755 ${WORKDIR}/enable_batman.sh ${D}${sysconfdir}/init.d
	update-rc.d -r ${D} enable_batman.sh start 95 5 .	
}

# Extend the install to handle our Neo-Plus2 case as well...
do_install_append_nanopi-neo-plus2 () {
	install -d ${D}${sysconfdir}/udev
	install -d ${D}${sysconfdir}/udev/rules.d
	install -m 0755 ${WORKDIR}/enable_bt.sh ${D}${sysconfdir}/init.d	
	install -m 0755 ${WORKDIR}/start_bt.sh ${D}${sysconfdir}/init.d	
	install -m 0755 ${WORKDIR}/udev_bt.sh ${D}${sysconfdir}/init.d	
	install -m 0644 ${WORKDIR}/90-rfkill.rules ${D}${sysconfdir}/udev/rules.d
	update-rc.d -r ${D} enable_bt.sh start 50 5 .
}
