SUMMARY = "CurvedSalsa - Encryption using "
HOMEPAGE = "https://github.com/konradmiller/CurvedSalsa"
SECTION = "console/tools"
# Program is Public Domain...
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://README;md5=5987e04c8ba9986b454f7983f9c9c879"

DEPENDS = "boost"
RDEPENDS:${PN} = "boost"


SRC_URI = "git://github.com/madscientist42/CurvedSalsa.git;protocol=https;branch=master"
SRCREV = "88ae61e009a6f9229f0412d0a8e6641b4197d60c"
S = "${WORKDIR}/git"

# CMake build...
inherit cmake pha-shorthash

# Make this part of native and nativesdk...
BBCLASSEXTEND = "native nativesdk"



