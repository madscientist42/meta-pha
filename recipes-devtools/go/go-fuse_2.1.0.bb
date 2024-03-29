DESCRIPTION = "Golang FUSE bindings"
HOMEPAGE = "https://github.com/hanwen/go-fuse"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=430d04ce687760975ee4d1833cfd5d4f"

GO_IMPORT = "github.com/hanwen/go-fuse"

SRC_URI = " \
    git://${GO_IMPORT};protocol=https;branch=master \
    "

SRCREV = "v${PV}"

S = "${WORKDIR}/git"

# This is packaging only since this is a library...
do_install() {
    install -d ${D}${libdir}/go/src/${GO_IMPORT}
    cp -Rp --no-preserve=ownership ${S}/* ${D}${libdir}/go/src/${GO_IMPORT}
}

# FIXME -- We're going to wedge in a post-patch hack here to strip test
#          functions out of this.  We need to sort out testing as a
#          packaging on Golang stuff...
remove_unit_tests() {
    rm -rf ${S}/src/${GO_IMPORT}/test
    rm -rf ${S}/src/${GO_IMPORT}/tests
    find ${S} -name test* -exec rm -f {} \;
}
do_patch[postfuncs] += " remove_unit_tests "

FILES:${PN} = "${libdir}/go/src"

