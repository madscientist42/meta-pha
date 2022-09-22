DESCRIPTION = "Packaged version of iLBC codec from the WebRTC project"
HOMEPAGE = "https://github.com/TimothyGu/libilbc"
LICENSE = "BSD"

LIC_FILES_CHKSUM = "file://COPYING;md5="

inherit cmake

DEPENDS = " \
    abseil \
    "

RDEPENDS_${PN} = " \
    abseil \
    "

SRC_URI = " \
    git://github.com/TimothyGu/libilbc.git;protocol=https;branch=main \
    file://0001-fix-cmake-for-yocto.patch \
    "

SRCREV = "v${PV}"

S = "${WORKDIR}/git"

EXTRA_OECMAKE += " \
    "


