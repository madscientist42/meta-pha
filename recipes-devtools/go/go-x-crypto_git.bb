DESCRIPTION = "Supplementary Cryptographic components for Golang"
HOMEPAGE = "https://godoc.org/golang.org/x/crypto"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5d4950ecb7b26d2c5e4e7b4e0dd74707"

SRC_URI = " \
    git://github.com/golang/crypto;protocol=https;branch=master \
    "

SRCREV = "ae814b36b87190c757eede9bc2d32ed77df88551"
PV = "git+${SRCPV}"

S = "${WORKDIR}/git"

GO_IMPORT = "golang.org/x/crypto"

# This is packaging only since the repo is stashed in the way we need to do this.
do_install() {
    install -d ${D}${libdir}/go/src/${GO_IMPORT}
    cp -Rp --no-preserve=ownership ${S}/* ${D}${libdir}/go/src/${GO_IMPORT}
    rm -f ${D}/usr/lib/go/src/golang.org/x/crypto/curve25519/internal/field/sync.sh
    rm -rf ${D}/usr/lib/go/src/golang.org/x/crypto/ssh/test
}

FILES:${PN} = "${libdir}/go/src"


