DESCRIPTION = "arping arp pinging tool"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE;md5=751419260aa954499f7abaabaa882bbe"

DEPENDS = " \
    libnet \
    "

RDEPENDS:${PN} = " \
    libnet \
    "

SRC_URI = " \
    git://github.com/ThomasHabets/arping.git;protocol=https;branch=arping-2.x \
    "

SRCREV = "d2cb45f2ece40a2b687ee1b827b64890c414a41b"
S = "${WORKDIR}/git"

inherit autotools pkgconfig pha-shorthash
