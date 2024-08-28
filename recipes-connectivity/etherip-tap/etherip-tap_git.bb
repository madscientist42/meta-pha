DESCRIPTION = "EtherIP (RFC 3378) IP encapsulation tunnel for Ethernet using TAP driver."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e885e5cd8cbc652d4eebf7ae1d22fcd4"

SRC_URI = " \
    git://git@gitserver.local.earlconsult.com:3001/frank/etherip-tap.git;protocol=ssh;branch=main \
    file://sv/etherip/run \
    "
SRCREV = "69bf0aeebf77ba2c7657e512eb4d15d5f79d2054"
PV = "git+${SRCREV}"
S = "${WORKDIR}/git"

inherit cmake runit

