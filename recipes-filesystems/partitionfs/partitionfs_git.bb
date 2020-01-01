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

SRCREV = "38f7bd1ea090e75da7d98a68bc060016646cd589"

inherit cmake

BBCLASSEXTEND = "native nativesdk"