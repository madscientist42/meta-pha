DESCRIPTION = "Supplementary System specific components for Golang"
HOMEPAGE = "https://godoc.org/golang.org/x/sys"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5d4950ecb7b26d2c5e4e7b4e0dd74707"

SRC_URI = " \
    git://github.com/golang/sys;protocol=https;branch=master \
    "

SRCREV = "fe61309f888157de161a48facf03d9412635cffe"
PV = "git+${SRCPV}"

S = "${WORKDIR}/git"

GO_IMPORT = "golang.org/x/sys"

# This is packaging only since the repo is stashed in the way we need to do this.
do_install() {
    install -d ${D}${libdir}/go/src/${GO_IMPORT}
    cp -Rp --no-preserve=ownership ${S}/* ${D}${libdir}/go/src/${GO_IMPORT}
    # Can't realistically use Windows stuff here, so...
    rm -rf ${D}${libdir}/go/src/${GO_IMPORT}/windows
}

FILES:${PN} = "${libdir}/go/src"



