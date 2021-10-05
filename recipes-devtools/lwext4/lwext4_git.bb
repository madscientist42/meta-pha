DESCRIPTION = "Lightweight Ext2/3/4 filesystem manipulation library"
HOMEPAGE = "http://sourceforge.net/projects/lwext4/"
SECTION = "devel/libs"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f2f6bda03240fed3aad412fa763388c5"

S = "${WORKDIR}/git"

SRC_URI = "\
	git://github.com/gkostka/lwext4.git;protocol=https \
    file://cmake_install_target_fix.patch \
    "

SRCREV = "733b2c40d7121900e339bee8784977467a9fe8c9"

inherit cmake

EXTRA_OECMAKE = "-DLWEXT4_BUILD_SHARED_LIB=1"

BBCLASSEXTEND = "native nativesdk"

PACKAGES += "${PN}-utils"

FILES:${PN} = "/usr/lib"
FILES:${PN}-dev = "/usr/include"
FILES:${PN}-utils = "/usr/bin"