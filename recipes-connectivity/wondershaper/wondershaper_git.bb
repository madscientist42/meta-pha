DESCRIPTION = "Command-line utility for limiting an adapter's bandwidth"
HOMEPAGE = "https://github.com/magnific0/wondershaper"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "\
    git://github.com/magnific0/wondershaper.git;protocol=https \
    "

SRC_REV = "5e8359464938163f2a308710c3f489e2e5d9a7c8"

S = "${WORKSPACE}/git"

# Install only process.  It's a shell script that automates these processes
# for everyone in a much easier manner than the steps to do it with the 
# "official" tools
do_compile[noexec] = "1"
do_install () {
    install -d -m 0755 ${D}/usr/sbin
    install -m 0755 ${S}/wondershaper ${D}/usr/sbin
}


