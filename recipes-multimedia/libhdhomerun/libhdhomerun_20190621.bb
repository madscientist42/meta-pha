DESCRIPTION = "Silicon Dust HD Homerun control/access library"
HOMEPAGE = "https://www.silicondust.com/support/linux/"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fc178bcd425090939a8b634d1d6a9594"

inherit cmake

SRC_URI = " \
    http://download.silicondust.com/hdhomerun/${PN}_${PV}.tgz \
    file://CMakeLists.txt \
    "

SRC_URI[md5sum] = "0720a217952dbe05fba6e32ce430f7c7"
SRC_URI[sha256sum] = "9a60f663b00de5f820bdb85806662e25f22da248b14942e33a8b43a0331f855a"

S = "${WORKDIR}/${PN}"

do_configure_prepend() {
    cp ${WORKDIR}/CMakeLists.txt ${S}
}

FILES_${PN} = "/usr/lib"
FILES_${PN}-dev = "/usr/include"
