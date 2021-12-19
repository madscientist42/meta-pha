DESCRIPTION = "Intel Embedded Linux Library"
HOMEPAGE = "https://01.org/ell"
SECTION = "devel/libs"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=fb504b67c50331fc78734fed90fb0e09"

DEPENDS = "virtual/kernel"

S = "${WORKDIR}/git"

SRC_URI = "\
    git://git.kernel.org/pub/scm/libs/ell/ell.git;protocol=https \
    file://reinstate_useful_h_to_installed.patch \
    "

# If this is a version number, we just specify the tag.  If it's "git" in PV,
# we're going to use a githash...
SRCREV = "${PV}"

# Builds with autotools
inherit autotools-brokensep pkgconfig

# Need to run the bootstrap script...
do_configure:prepend() {
    ${S}/bootstrap
}

# Provide this to the SDK for inclusion if it's specified in the populate task list...
BBCLASSEXTEND += "native nativesdk"

