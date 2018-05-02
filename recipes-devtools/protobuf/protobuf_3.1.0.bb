SUMMARY = "Protocol Buffers - structured data serialisation mechanism"
DESCRIPTION = "Protocol Buffers are a way of encoding structured data in an \
efficient yet extensible format. Google uses Protocol Buffers for almost \
all of its internal RPC protocols and file formats."
HOMEPAGE = "https://github.com/google/protobuf"
SECTION = "console/tools"
LICENSE = "BSD-3-Clause"

PACKAGE_BEFORE_PN = "${PN}-compiler"

DEPENDS = "zlib"
RDEPENDS_${PN}-compiler = "${PN}"
RDEPENDS_${PN}-dev += "${PN}-compiler"

LIC_FILES_CHKSUM = "file://LICENSE;md5=35953c752efc9299b184f91bef540095"

SRCREV = "9f69353562fe1fbb3fbd11345ea3676b0eb267cd"

PV = "3.1.0+git${SRCPV}"

SRC_URI = "git://github.com/google/protobuf.git;protocol=https;branch=3.1.x"

EXTRA_OECONF += " --with-protoc=echo"

inherit autotools

S = "${WORKDIR}/git"

FILES_${PN}-compiler = "${bindir} ${libdir}/libprotoc${SOLIBS}"

BBCLASSEXTEND = "native nativesdk"
