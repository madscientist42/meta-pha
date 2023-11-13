DESCRIPTION = "Linux port of the Android procrank tool for mem usage."
HOMEPAGE = "https://github.com/madscientist42/procrank_linux"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9645f39e9db895a4aa6e02cb57294595"

inherit cmake

SRC_URI = " \
    git://github.com/madscientist42/procrank_linux.git;protocol=https;branch=master \
    "
SRCREV = "430cd716d0104ec0eff822f37c5d4ef5f40938ce"
PV = "git+${SRCPV}"
S = "${WORKDIR}/git"