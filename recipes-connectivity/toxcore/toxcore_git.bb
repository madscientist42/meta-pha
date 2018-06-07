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

SRCREV = "fda74a8454a028c9ec063f0074062e19fd25f7e7"

# Builds with autotools...
inherit autotools

# But, use the autogen.sh scripting first...
do_configure_prepend() {
        cd ${S}
        NOCONFIGURE=yes ./autogen.sh
        cd ${B}
}
