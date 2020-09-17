DESCRIPTION = "Google GASKET drivers.  Includes GASKET framework and base module along with Edge TPU 'Apex' module.  This is a peel-out of the same from the kernel it was from."
HOMEPAGE = "http://github.com/madscientist42/google-gasket"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=eb723b61539feef013de476e68b5c50a"

inherit module

SRC_URI = " \
    git://github.com/madscientist42/google-gasket.git;protocol=https \
    "

SRCREV = "3e50ff5c6ecf3737a03f6581fbe58ac540aaf1cd"

S = "${WORKDIR}/git"

PACKAGES = "${PN} ${PN}-dev google-apex"
RDEPENDS_google-apex = "${PN}"

FILES_${PN} = " \
    /lib/modules/${LINUX_VERSION}/extra/gasket.ko \
    "

FILES_${PN}-dev = " \
    /usr/include \
    "

FILES_google-apex = " \
    /lib/modules/${LINUX_VERSION}/extra/apex.ko \
    "