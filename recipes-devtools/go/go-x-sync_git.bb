DESCRIPTION = "Supplementary Concurrency components for Golang"
HOMEPAGE = "https://godoc.org/golang.org/x/sync"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5d4950ecb7b26d2c5e4e7b4e0dd74707"

SRC_URI = " \
    git://github.com/golang/sync;protocol=https \
    "

SRCREV = "cd5d95a43a6e21273425c7ae415d3df9ea832eeb"

S = "${WORKDIR}/git"

GO_IMPORT = "golang.org/x/sync"

# This is packaging only since the repo is stashed in the way we need to do this.
do_install() {
    install -d ${D}${libdir}/go/src/${GO_IMPORT}
    cp -Rp --no-preserve=ownership ${S}/* ${D}${libdir}/go/src/${GO_IMPORT}
}

FILES:${PN} = "${libdir}/go/src"


