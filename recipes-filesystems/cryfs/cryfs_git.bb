DESCRIPTION = "CryFS encrypts your files, so you can safely store them anywhere."
HOMEPAGE = "https://www.cryfs.org"
LIC_FILES_CHKSUM = "file://LICENCE.txt;md5=e6a600fd5e1d9cbde2d983680233ad02"
LICENSE = "GPLv3"

SRCREV = "cf3023406969b14610df03a043fca8a078c9c195"
SRC_URI = "git://github.com/cryfs/cryfs.git"

DEPENDS += " \
    boost \
    fuse \
    "

EXTRA_OECONF += " -DBUILD_SHARED_LIBS=on-DCRYFS_UPDATE_CHECKS=off -DCMAKE_NO_SYSTEM_FROM_IMPORTED=on"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

do_removeconfig () {
    rm ${WORKDIR}/git/CMakeSettings.json
}

addtask removeconfig after do_unpack before do_configure
