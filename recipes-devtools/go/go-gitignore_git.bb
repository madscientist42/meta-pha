DESCRIPTION = ".gitignore format parser for go"
HOMEPAGE = "https://github.com/sabhiram/go-gitignore"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=6fee026f0b48abb4d7cd72e25032503c"

GO_IMPORT = "github.com/sabhiram/go-gitignore"

SRC_URI = " \
    git://${GO_IMPORT};protocol=https \
    "

SRCREV = "d3107576ba9425fc1c85f4b3569c4631b805a02e"

inherit go

GO_INSTALL = "${S}/src/${GO_IMPORT}"


