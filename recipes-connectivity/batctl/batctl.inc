DESCRIPTION = "Control application for B.A.T.M.A.N. routing protocol kernel module for multi-hop ad-hoc mesh networks."
HOMEPAGE = "http://www.open-mesh.net/"
SECTION = "console/network"
PRIORITY = "optional"
LICENSE = "GPL-2.0-or-later"

INC_PR = "r0"

DEPENDS = " \
  libnl \
  pkgconfig \
  pkgconfig-native \
  "

# This causes a QA warning flag- it's legit, but it probably needs to be expressed/flagged
# better so there's no warnings like this...
RDEPENDS_${PN} = "kernel-module-batman-adv"

LIC_FILES_CHKSUM = "file://LICENSES/preferred/GPL-2.0;md5=fc6177742f3ff0569ececd42b9b7d5fc"

SRC_URI = " \
  git://git.open-mesh.org/batctl.git;protocol=https;branch=main \
  "
S = "${WORKDIR}/git"

do_compile() {
  oe_runmake
}

do_install() {
  install -d ${D}${bindir}
  install -m 0755 batctl ${D}${bindir}
}

