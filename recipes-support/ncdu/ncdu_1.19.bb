DESCRIPTION = "NCurses Disk Usage TUI tool"
HOMEPAGE = "https://dev.yorhel.nl/ncdu"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=a73abd2b8c8d6c4e1e2b7b449cd29629"

inherit autotools-brokensep pkgconfig

DEPENDS += " \
    ncurses \
    "

SRC_URI = " \
    git://code.blicky.net/yorhel/ncdu.git;protocol=https;branch=master \
    file://0001-fix-pod2man-build-problem.patch \
    "

SRCREV = "1ca0c5769b4a3252e09d7c5f4333e61c8d2fdd4a"
PV = "git+${SRCPV}"

S = "${WORKDIR}/git"

