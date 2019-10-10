DESCRIPTION = "Encrypt-Mix-Encrypt wide-block encryption for Golang"
HOMEPAGE = "https://github.com/rfjakob/eme"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=6fee026f0b48abb4d7cd72e25032503c"

GO_IMPORT = "github.com/rfjakob/eme"

SRC_URI = " \
    git://${GO_IMPORT};protocol=https \
    "

SRCREV = "2222dbd4ba467ab3fc7e8af41562fcfe69c0d770"

inherit go

GO_INSTALL = "${S}/src/${GO_IMPORT}"

# The testing, etc. stuff requires bash to be in the mix...
RDEPENDS_${PN}-dev += "bash"

