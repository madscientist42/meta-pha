DESCRIPTION = "Golang Cryptography routines not in the Golang standard library"
HOMEPAGE = "https://github.com/jacobsa/crypto"
LICENSE = "Apache2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

GO_IMPORT = "github.com/jacobsa/crypto"

SRC_URI = " \
    git://${GO_IMPORT};protocol=https \
    "

SRCREV = "9f44e2d11115452dad8f404f029574422855f46a"

S = "${WORKDIR}/git"

# This is packaging only since this is a library...
do_install() {
    install -d ${D}${libdir}/go/src/${GO_IMPORT}
    cp -Rp --no-preserve=ownership ${S}/* ${D}${libdir}/go/src/${GO_IMPORT}
}

FILES_${PN} = "${libdir}/go/src"
