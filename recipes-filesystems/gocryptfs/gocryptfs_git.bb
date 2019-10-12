DESCRIPTION = "An Encrypting FUSE Filesystem written in Go"
HOMEPAGE = "https://nuetzlich.net/gocryptfs/"
SECTION = "universe/otherosfs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=6fee026f0b48abb4d7cd72e25032503c"

DEPENDS = "go-fuse go-gitignore go-eme go-jacobsa-siv go-x-crypto go-x-sync go-x-sys openssl"

GO_IMPORT = "github.com/rfjakob/gocryptfs"

SRC_URI = " \
    git://${GO_IMPORT};protocol=https \
    "

SRCREV = "8cb104f004cc5d3343bc3b5ef2187c1830e00f19"

inherit go pkgconfig

GO_INSTALL = "${S}/src/${GO_IMPORT}"

# FIXME -- We're going to wedge in a post-patch hack here to strip test
#          functions out of this.  We need to sort out testing as a 
#          packaging on Golang stuff...
remove_unit_tests() {
    rm -rf ${S}/src/${GO_IMPORT}/test
    find ${S} -name test* -exec rm -f {} \;
}
do_patch[postfuncs] += " remove_unit_tests "

RDEPENDS_${PN}-dev = "bash"
