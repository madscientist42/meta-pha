DESCRIPTION = "Encrypt-Mix-Encrypt wide-block encryption for Golang"
HOMEPAGE = "https://github.com/rfjakob/eme"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6fee026f0b48abb4d7cd72e25032503c"

GO_IMPORT = "github.com/rfjakob/eme"

SRC_URI = " \
    git://${GO_IMPORT};protocol=https \
    "

SRCREV = "2222dbd4ba467ab3fc7e8af41562fcfe69c0d770"

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
    find ${S} -name benchmark* -exec rm -f {} \;
}
do_patch[postfuncs] += " remove_unit_tests "

FILES_${PN} = "${libdir}/go/src"

