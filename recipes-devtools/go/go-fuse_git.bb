DESCRIPTION = "Golang FUSE bindings"
HOMEPAGE = "https://github.com/hanwen/go-fuse"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=430d04ce687760975ee4d1833cfd5d4f"

GO_IMPORT = "github.com/hanwen/go-fuse"

SRC_URI = " \
    git://${GO_IMPORT} \
    "

SRCREV = "1266c0dcb6edad0f68613398974b339ea18cec5d"

S = "${WORKDIR}/git"

# This is packaging only since this is a library...
do_install() {
    install -d ${D}${libdir}/go/src/${GO_IMPORT}
    cp -Rp --no-preserve=ownership ${S}/* ${D}${libdir}/go/src/${GO_IMPORT}
}

FILES_${PN} = "${libdir}/go/src"

