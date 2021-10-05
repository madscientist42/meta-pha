DESCRIPTION = "Motion camera motion detection and recording engine"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM="file://COPYING;md5=8ca43cbc842c2336e835926c2166c28b"

DEPENDS = " \
	jpeg \
	libmicrohttpd \
	libwebp \
	ffmpeg \
	update-rc.d-native \
	"

RDEPENDS:${PN} = " \
	jpeg \
	libmicrohttpd \
	libwebp \
	ffmpeg \
	start-stop-daemon \
	"

SRCREV = "7bd179c0850115c51474caa5d6bcd4784f6b8d65"
SRC_URI = " \
	git://github.com/Motion-Project/motion.git;protocol=https \
	file://motion.sh \
	"

# We need to remove a few "examples" since we don't need the examples
# (And they commit a "/usr/bash" sin for an initscript example...)
#
# Also, let's add an init appropriately for this to work with.
do_install_append() {
	# Remove the problem stuff...
	rm -rf ${D}/usr/share/motion/examples

	# Install sysv init next...
	install -m 0755 -d ${D}${sysconfdir}/init.d
	install -m 0755 -d ${D}${sysconfdir}/rc5.d
	install -m 0755 ${WORKDIR}/motion.sh ${D}${sysconfdir}/init.d
	update-rc.d -r ${D} motion.sh start 95 5 .
}

S = "${WORKDIR}/git"

inherit pkgconfig autotools
