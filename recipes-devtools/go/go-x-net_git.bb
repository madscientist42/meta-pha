DESCRIPTION = "Supplementary System specific components for Golang"
HOMEPAGE = "https://godoc.org/golang.org/x/net"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5d4950ecb7b26d2c5e4e7b4e0dd74707"

SRC_URI = " \
    git://github.com/golang/net;protocol=https \
    "

SRCREV = "d66e71096ffb9f08f36d9aefcae80ce319de6d68"

S = "${WORKDIR}/git"

GO_IMPORT = "golang.org/x/net"

# This is packaging only since the repo is stashed in the way we need to do this.
do_install() {
    install -d ${D}${libdir}/go/src/${GO_IMPORT}
    cp -Rp --no-preserve=ownership ${S}/* ${D}${libdir}/go/src/${GO_IMPORT}
    # Can't realistically use Windows stuff here, so...
    rm -rf ${D}${libdir}/go/src/${GO_IMPORT}/windows
}

FILES_${PN} = "${libdir}/go/src"



