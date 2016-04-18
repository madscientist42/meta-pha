DESCRIPTION = "An Encrypting FUSE Filesystem"
HOMEPAGE = "http://www.arg0.net/#!encfs/c1awt"
SECTION = "universe/otherosfs"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=951b5c8c6c484058f2eafac08b65c272"

S = "${WORKDIR}/git"

DEPENDS = "fuse rlog openssl boost"
RDEPENDS_encfs = "fuse rlog openssl boost"

SRC_URI = "\
	  git://github.com/vgough/encfs.git;protocol=https \
	  file://cmakelists_correction.patch \
          "

SRCREV = "0426051a3e520c29d261b7fe83461d90b9d54ddf"

inherit cmake
