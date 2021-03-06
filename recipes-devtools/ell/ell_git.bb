DESCRIPTION = "Intel Embedded Linux Library"
HOMEPAGE = "https://01.org/ell"
SECTION = "devel/libs"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=fb504b67c50331fc78734fed90fb0e09"

DEPENDS = "virtual/kernel"

S = "${WORKDIR}/git"


SRC_URI = "\
    git://git.kernel.org/pub/scm/libs/ell/ell.git;protocol=https \
    "

SRCREV = "c156b921accae7d556f5d4166d02c0204cd50f56"

# Builds with autotools
inherit autotools-brokensep pkgconfig

# Need to run the bootstrap script...
do_configure_prepend() {
    ${S}/bootstrap
}

# Provide this to the SDK for inclusion if it's specified in the populate task list...
BBCLASSEXTEND += "native nativesdk"

