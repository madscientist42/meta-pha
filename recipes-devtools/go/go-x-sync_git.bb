DESCRIPTION = "Supplementary Concurrency components for Golang"
HOMEPAGE = "https://godoc.org/golang.org/x/sync"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5d4950ecb7b26d2c5e4e7b4e0dd74707"

SRC_URI = " \
    git://github.com/golang/sync;protocol=https;branch=master \
    "

SRCREV = "036812b2e83c0ddf193dd5a34e034151da389d09"
PV = "git+${SRCPV}"

S = "${WORKDIR}/git"

GO_IMPORT = "golang.org/x/sync"

# This is packaging only since the repo is stashed in the way we need to do this.
do_install() {
    install -d ${D}${libdir}/go/src/${GO_IMPORT}
    cp -Rp --no-preserve=ownership ${S}/* ${D}${libdir}/go/src/${GO_IMPORT}
}

FILES:${PN} = "${libdir}/go/src"


