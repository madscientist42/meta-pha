DESCRIPTION = "Abseil C++ Library- this is the fundamental core of Google's frameworks made as a FOSS library"
LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=df52c6edb7adc22e533b2bacc3bd3915"

SRC_URI = " \
	git://github.com/abseil/abseil-cpp \
	"

SRCREV = "cad3f30b44c2bfac54ee82c6fc9e49ba49078620"

PR = "r0"

S = "${WORKDIR}/git"

inherit cmake pkgconfig