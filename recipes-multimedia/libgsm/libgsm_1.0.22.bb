DESCRIPTION = "GSM 06.10 lossy speech compression"
HOMEPAGE = "https://quut.com/gsm/"
LICENSE = "BSD"

LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=fc1372895b173aaf543a122db37e04f5"

SRC_URI = " \
    https://www.quut.com/gsm/gsm-1.0.22.tar.gz \
    file://0001-fix-makefile-for-yocto.patch \
    "

SRC_URI[sha256sum] = "f0072e91f6bb85a878b2f6dbf4a0b7c850c4deb8049d554c65340b3bf69df0ac"

S = "${WORKDIR}/gsm-1.0-pl22"

# do_compile appears to work with this one (With a patch...)- but the installer's duff
# for the purposes of a cross-compile/distribution builder perspective, so we'll
# package it out ourselves.  We'll cheat a smidge and make the demo apps be the ${PN}
# packaging so it builds right without jumping through idiot flaming hoops for the build.
PACKAGES = "${PN} ${PN}-dev ${PN}-staticdev ${PN}-dbg"

do_install() {
    install -d ${D}/usr
    install -d ${D}/usr/lib
    install -d ${D}/usr/include
    install -d ${D}/usr/bin
    install -m 0755 ${S}/lib/libgsm.a ${D}/usr/lib
    install -m 0644 ${S}/inc/* ${D}/usr/include
    install -m 0755 ${S}/bin/tcat ${D}/usr/bin
    install -m 0755 ${S}/bin/toast ${D}/usr/bin
    install -m 0755 ${S}/bin/untoast ${D}/usr/bin
}

FILES_${PN} = " \
    /usr/bin/tcat \
    /usr/bin/untoast \
    /usr/bin/toast \
    "
FILES_${PN}-dev = "/usr/include"
FILES_${PN}-staticdev = "/usr/lib"
FILES_${PN}-dbg = "/usr/bin/.debug"
