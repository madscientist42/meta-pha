DESCRIPTION = "An Encrypting FUSE Filesystem written in Go"
HOMEPAGE = "https://nuetzlich.net/gocryptfs/"
SECTION = "universe/otherosfs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=6fee026f0b48abb4d7cd72e25032503c"

DEPENDS = "go-fuse go-gitignore go-eme go-jacobsa-siv go-x-crypto go-x-sync go-x-sys openssl"

# FIXME - Need to do a PR for upstream, so we're going to take the PR stream
# in for now before the official upstream absorbs the fixes...
#GO_IMPORT = "github.com/rfjakob/gocryptfs"
GO_IMPORT = "github.com/madscientist42/gocryptfs"

SRC_URI = " \
    git://${GO_IMPORT};protocol=https \
    "

SRCREV = "0ea6df18daf7c1fb120c8631a7252ac99ae138d9"

inherit go pkgconfig

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

# FIXME -- give the code a binky.  We need to have rfjakob's pathing 
# as he's using it within his code...so...
temp_patch_paths() {
    ln -s ${S}/src/github.com/madscientist42 ${S}/src/github.com/rfjakob
}
do_patch[postfuncs] += " temp_patch_paths "

do_compile_ptest_base[noexec] = "1"

RDEPENDS_${PN}-dev = "bash"
