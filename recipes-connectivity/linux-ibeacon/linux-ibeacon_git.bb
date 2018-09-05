DESCRIPTION = "Linux iBeacon helper"
HOMEPAGE = "https://github.com/dburr/linux-ibeacon"
SECTION = "connectivity"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5b5abc1c9239b77316c7fa6a292f2e31"

DEPENDS = "python"
RDEPENDS_${PN} = "python"

SRC_URI = " \
    git://github.com/dburr/linux-ibeacon.git;protocol=https \
    "    

SRCREV = "56b64cb173c7c818accdae75167b6d1be998bcc6"

S = "${WORKDIR}/git"

# Just an override stub so we do nada in this phase...
do_compile() {
}

do_install() {
    # Just copy the single app python file since it's all we need...
    install -d ${D}${bindir}
    install -m 0755 ${S}/ibeacon ${D}${bindir}/ibeacon.py
}
