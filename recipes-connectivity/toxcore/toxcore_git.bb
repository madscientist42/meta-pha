DESCRIPTION = "TOXcore TOX P2P secure communications library"
HOMEPAGE = "http://tox.chat"
SECTION = "connectivity/libs"
LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

S = "${WORKDIR}/git"

# TOXcore requires libsodium and a few other things...
RDEPENDS:toxcore = "libsodium"
DEPENDS = "libsodium"

SRC_URI = "\
    git://github.com/irungentoo/toxcore.git;protocol=https;branch=master \
    "

SRCREV = "bf69b54f64003d160d759068f4816b2d9b2e1e21"

# Builds with autotools...
inherit autotools pha-shorthash

# But, use the autogen.sh scripting first...
do_configure:prepend() {
        cd ${S}
        NOCONFIGURE=yes ./autogen.sh
        cd ${B}
}
