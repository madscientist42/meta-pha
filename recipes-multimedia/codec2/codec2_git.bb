DESCRIPTION = "Codec2 low-bandwidth vocoder"
HOMEPAGE = "https://www.rowetel.com/?page_id=452"
LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=776e198b48f3a1004a437be10854038b"

inherit cmake

DEPENDS += " \
    codec2-native \
    "

SRC_URI = " \
    git://github.com/drowe67/codec2.git;protocol=https;branch=main \
    file://0001-fix-cmake-for-yocto.patch \
    file://0002-fix-codebook-gen.patch \
    "
SRCREV = "3d69c8d0639059851e60c0ddf882f86bf2fba380"
# If, for any reason, you use the "right" way to do this, and you check out
# with devtool for recent Yocto versions, things BREAK.  Bug to be submitted
# to upstream shortly.
PV = "git+${SRCREV}"

S = "${WORKDIR}/git"

# Clean up after ourselves...  Need a Postinst operation here.
postinst_target() {
    # Remove the codebook generator...
    rm -rvf ${D}/usr/bin
}
POSTINST_OPS = ""
POSTINST_OPS:class-target = "postinst_target"
do_install[postfuncs] += "${POSTINST_OPS}"

BBCLASSEXTEND += "native"