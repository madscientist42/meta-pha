DESCRIPTION = "Waveshare/Adafruit E-Ink support Kernel Module(s)"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ddd9011b02532015cfd87a98b42962c0"

inherit module

SRC_URI += " \
    git://git@github.com:/madscientist42/fb-ws-eink.git;protocol=ssh;branch=master \
    "
# FIXME- We're in heavy dev here.  So, let's autorev this for a while.
#        we'll do versioned pull tags in the repo when we finalize at
#        least partial updates/tricolor.  Make PV match the git hash
#        so we know what we're doing and can discriminate between
#        cuts of the repo as we do this.
SRCREV = "0607c892e4a9a6f97cccffd4590bb99478cd2073"
PV = "git${SRCPV}"

S = "${WORKDIR}/git"