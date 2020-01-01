DESCRIPTION = "A libparted based FUSE filesystem for partition access"
HOMEPAGE = ""
SECTION = "universe/otherosfs"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e8c1458438ead3c34974bc0be3a03ed6"

S = "${WORKDIR}/git"

DEPENDS = "fuse parted"
RDEPENDS_${PN} = "fuse parted"

SRC_URI = "\
	git://github.com/madscientist42/partitionfs.git;protocol=https \
    "

SRCREV = "b366ebfbf0a6cfdf469b82c62da5b8befa87de26"

inherit cmake

BBCLASSEXTEND = "native nativesdk"