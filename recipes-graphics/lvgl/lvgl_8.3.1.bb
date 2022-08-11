DESCRIPTION = "Lightweight and Versatile Graphics Library (LVGL)"
HOMEPAGE = "https://lvgl.io/"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENCE.txt;md5=bf1198c89ae87f043108cea62460b03a"

# Choosing to use CMake instead of SCons...
inherit cmake

# Compute the first two digits of ${PV} as Base PV- we use this to help checkout
# via a branch.  If you're wanting the dev-tip, you'll want to copy this recipe
# and change out some of this...
BPV = "${@'.'.join(d.getVar('PV').split('.')[0:2])}"

SRCREV = "v${PV}"
SRC_URI = " \
    git://github.com/lvgl/lvgl.git;protocol=https;branch=release/v${BPV} \
    "

S = "${WORKDIR}/git"

# Make it an .so solution for now...
EXTRA_OECMAKE = " \
    -DBUILD_SHARED_LIBS=on \
    "

# Compensate for the confusion that Yocto has with things on packaging
# because of how LVGL's team installs out.
FILES_${PN} = "/usr/lib"
FILES_${PN}-dev = "/usr/include"