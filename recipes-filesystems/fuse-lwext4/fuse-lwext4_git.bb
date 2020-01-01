DESCRIPTION = "An lwext4 based FUSE filesystem"
HOMEPAGE = ""
SECTION = "universe/otherosfs"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

S = "${WORKDIR}/git"

DEPENDS = "fuse lwext4 acl"
RDEPENDS_${PN} = "fuse lwext4 acl"

SRC_URI = "\
	git://github.com/madscientist42/fuse-lwext4.git;protocol=https \
    "

SRCREV = "cc1b381f7d32596a2db4c0c36f3f38741a59add3"

inherit cmake

BBCLASSEXTEND = "native nativesdk"