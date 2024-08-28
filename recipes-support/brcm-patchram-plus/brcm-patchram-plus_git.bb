DESCRIPTION = "Portable, lightweight set of small, sharp tools for code dev"
HOMEPAGE = "https://github.com/madscientist42/rpe_tools"
SECTION = "devel/libs"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=691691b063f1b4034300dc452e36b68d"

S = "${WORKDIR}/git"

DEPENDS = "bluez5"

SRC_URI = " \
    git://github.com/madscientist42/brcm_patchram.git;protocol=https;branch=master \
    "

SRCREV = "48201687f51de13a93693d8d58c66b5e7c416e38"

inherit cmake pkgconfig pha-shorthash




