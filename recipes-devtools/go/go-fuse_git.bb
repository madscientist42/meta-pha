DESCRIPTION = "Golang FUSE bindings"
HOMEPAGE = "https://github.com/hanwen/go-fuse"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=430d04ce687760975ee4d1833cfd5d4f"

GO_IMPORT = "github.com/hanwen/go-fuse"

SRC_URI = " \
    git://${GO_IMPORT};protocol=https \
    "

SRCREV = "1266c0dcb6edad0f68613398974b339ea18cec5d"

inherit go

GO_INSTALL = "${S}/src/${GO_IMPORT}"
