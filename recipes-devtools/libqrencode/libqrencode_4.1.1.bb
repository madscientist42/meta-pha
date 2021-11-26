DESCRIPTION = "QR code encode library"
HOMEPAGE = "http://fukuchi.org/works/qrencode/"
SECTION = "devel/libs"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=2d5025d4aa3495befef8f17206a5b0a1"

S = "${WORKDIR}/git"

SRC_URI = " \
	git://github.com/fukuchi/libqrencode.git;protocol=https \
    "

SRCREV = "v${PV}"

# We're a pkgconfig and autotools project...
inherit pkgconfig autotools

# But there's a nifty autogen.sh, so let's make sure to USE it.
do_configure_prepend() {
        cd ${S}
        NOCONFIGURE=yes ./autogen.sh
        cd ${B}
}

# Provide this to the SDK for inclusion if it's specified in the populate task list...
BBCLASSEXTEND += "native nativesdk"
