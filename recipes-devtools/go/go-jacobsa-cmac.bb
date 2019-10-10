DESCRIPTION = "Golang Cryptography routines not in the Golang standard library"
HOMEPAGE = "https://github.com/jacobsa/crypto"
LICENSE = "Apache2.0"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=430d04ce687760975ee4d1833cfd5d4f"

GO_IMPORT = "github.com/jacobsa/crypto"

SRC_URI = " \
    git://${GO_IMPORT};protocol=https \
    "

SRCREV = "9f44e2d11115452dad8f404f029574422855f46a"

inherit go

GO_INSTALL = "${S}/src/${GO_IMPORT}/cmac"

