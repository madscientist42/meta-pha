DESCRIPTION = ".gitignore format parser for go"
HOMEPAGE = "https://github.com/sabhiram/go-gitignore"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=bc1bb35f6ed8f35d0af3848bd675d349"

GO_IMPORT = "github.com/sabhiram/go-gitignore"

SRC_URI = " \
    git://${GO_IMPORT};protocol=https \
    "

SRCREV = "d3107576ba9425fc1c85f4b3569c4631b805a02e"

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

FILES_${PN} = "${libdir}/go/src"
