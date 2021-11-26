DESCRIPTION = "Silicon Dust HD Homerun control/access library"
HOMEPAGE = "https://www.silicondust.com/support/linux/"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fc178bcd425090939a8b634d1d6a9594"

inherit cmake

SRC_URI = " \
    http://download.silicondust.com/hdhomerun/${PN}_${PV}.tgz \
    file://CMakeLists.txt \
    "

SRC_URI[sha256sum] = "deaf463bbcc3eefa72f97199efb6213f7b0e2c8e91f1b3d2cbf52056a8715d15"

S = "${WORKDIR}/${PN}"

do_configure_prepend() {
    cp ${WORKDIR}/CMakeLists.txt ${S}
}

FILES:${PN} = "/usr/lib"
FILES:${PN}-dev = "/usr/include"
