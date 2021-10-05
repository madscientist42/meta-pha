SUMMARY = "CurvedSalsa - Encryption using "
HOMEPAGE = "https://github.com/konradmiller/CurvedSalsa"
SECTION = "console/tools"
# Program is Public Domain...
LICENSE = "PD"

LIC_FILES_CHKSUM = "file://README;md5=5987e04c8ba9986b454f7983f9c9c879"

S = "${WORKDIR}/git"

SRC_URI = "git://github.com/madscientist42/CurvedSalsa.git;protocol=https;branch=master"
SRCREV = "88ae61e009a6f9229f0412d0a8e6641b4197d60c"

DEPENDS = "boost"
RDEPENDS:${PN} = "boost"

PV = "1.0+git${SRCPV}"

# CMake build...
inherit cmake

# Make this part of native and nativesdk...
BBCLASSEXTEND = "native nativesdk"



