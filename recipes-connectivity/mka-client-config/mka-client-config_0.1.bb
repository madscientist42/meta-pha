DESCRIPTION = "MKA Daemon server config"
LICENSE = "CLOSED"

SRC_URI = " \
    file://mkad.conf \
    "

do_install(){
    mkdir -p ${D}/etc/mkad
    install ${WORKDIR}/mkad.conf ${D}/etc/mkad
}