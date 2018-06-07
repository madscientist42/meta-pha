SUMMARY = "CurvedSalsa - Encryption using "
HOMEPAGE = "https://github.com/konradmiller/CurvedSalsa"
SECTION = "console/tools"
# Program is Public Domain...
LICENSE = "PD"

LIC_FILES_CHKSUM = "file://README;md5=5987e04c8ba9986b454f7983f9c9c879"

S = "${WORKDIR}/git"

SRC_URI = "git://github.com/konradmiller/CurvedSalsa.git;protocol=https;branch=master"
SRCREV = "fb78fc7614017032ec830459ef179cbc357af0b9"

DEPENDS = "boost"
RDEPENDS_${PN} = "boost"

PV = "1.0+git${SRCPV}"

# CMake build...
inherit cmake


