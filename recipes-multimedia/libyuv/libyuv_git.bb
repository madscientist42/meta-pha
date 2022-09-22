DESCRIPTION = "YUV scaling and conversion functionality from Chromium project"
HOMEPAGE = "https://chromium.googlesource.com/libyuv/libyuv/"
LICENSE = "BSD"

LIC_FILES_CHKSUM = "file://LICENSE;md5=464282cfb405b005b9637f11103a7325"

inherit cmake

SRC_URI = " \
    git://chromium.googlesource.com/libyuv/libyuv;protocol=https;branch=main \
    "

SRCREV = "f9fda6e7d819c07a684ba6bc97cbf0e257bbcb37"

S = "${WORKDIR}/git"

INSANE_SKIP_${PN}-dev = "dev-elf"