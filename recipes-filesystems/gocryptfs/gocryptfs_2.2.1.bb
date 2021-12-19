DESCRIPTION = "An Encrypting FUSE Filesystem written in Go"
HOMEPAGE = "https://nuetzlich.net/gocryptfs/"
SECTION = "universe/otherosfs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=6fee026f0b48abb4d7cd72e25032503c"

DEPENDS = "go-fuse go-gitignore go-eme go-jacobsa-crypto go-x-crypto go-x-sync go-x-sys"

inherit go-mod

GO_IMPORT = "github.com/rfjakob/gocryptfs"

SRC_URI = " \
    git://${GO_IMPORT};protocol=https \
    "

SRCREV = "v${PV}"

S = "${WORKDIR}/git"

# Use the golang NATIVE crypto solution....
GOBUILDFLAGS:append = " -tags without_openssl"

GO_INSTALL = "${GO_IMPORT}"

# FIXME -- We're going to wedge in a post-patch hack here to strip test
#          functions out of this.  We need to sort out testing as a
#          packaging on Golang stuff...
remove_unit_tests() {
    rm -rf ${S}/src/${GO_IMPORT}/test
    rm -rf ${S}/src/${GO_IMPORT}/tests
    find ${S} -name test* -exec rm -f {} \;
}
do_patch[postfuncs] += " remove_unit_tests "

do_compile_ptest_base[noexec] = "1"

RDEPENDS:${PN}-dev = "bash"
