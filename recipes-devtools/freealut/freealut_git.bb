DESCRIPTION = "Free OpenALUT library"
HOMEPAGE = "https://github.com/vancegroup/freealut"
SECTION = "devel/library"
LICENSE = "LGPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=7ec51bd663119a90c3d50704d6051fae"

S = "${WORKDIR}/git"

DEPENDS = "openal-soft"
RDEPENDS:encfs = "openal-soft"

SRC_URI = "\
	  git://github.com/vancegroup/freealut.git;protocol=https;branch=master \
          "

SRCREV = "fc814e316c2bfa6e05b723b8cc9cb276da141aae"
PV = "git+${SRCPV}"

inherit cmake
