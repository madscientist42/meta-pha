DESCRIPTION = "TOXcore TOX P2P secure communications library"
HOMEPAGE = "http://tox.chat"
SECTION = "connectivity/libs"
LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

S = "${WORKDIR}/git"

# TOXcore requires libsodium and a few other things...
RDEPENDS_toxcore = "libsodium"
DEPENDS = "libsodium"

SRC_URI = "\
	  git://github.com/irungentoo/toxcore.git;protocol=https \
          "

SRCREV = "532629d486e3361c7d8d95b38293cc7d61dc4ee5"

# Builds with autotools...
inherit autotools

# But, use the autogen.sh scripting first...
do_configure_prepend() {
        cd ${S}
        NOCONFIGURE=yes ./autogen.sh
        cd ${B}
}
