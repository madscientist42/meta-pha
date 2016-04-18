DESCRIPTION = "A C++ Logging Library"
HOMEPAGE = "https://github.com/vgough/rlog"
SECTION = "devel/libs"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=e77fe93202736b47c07035910f47974a"

S = "${WORKDIR}/git"

SRC_URI = "\
	  git://github.com/vgough/rlog.git;protocol=https \
          "

SRCREV = "040240df98ba5f9d581d556bde7692e0f5a8c217"

# Builds with autotools...
inherit autotools

