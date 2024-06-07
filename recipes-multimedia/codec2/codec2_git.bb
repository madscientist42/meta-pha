DESCRIPTION = "Codec2 low-bandwidth vocoder"
HOMEPAGE = "https://www.rowetel.com/?page_id=452"
LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=776e198b48f3a1004a437be10854038b"

inherit cmake

SRC_URI = " \
    git://github.com/drowe67/codec2.git;protocol=https;branch=main \
    "
SRCREV = "3d69c8d0639059851e60c0ddf882f86bf2fba380"
PV = "git+${@bb.fetch2.get_srcrev(d)}"

S = "${WORKDIR}/git"
