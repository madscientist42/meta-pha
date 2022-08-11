DESCRIPTION = "LVGL demo for PHA Linux"
HOMEPAGE = "https://github.com/madscientist42/lvgl-demo"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE"

SRCREV = "${PV}"
SRC_URI = " \
    git://github.com/madscientist42/lvgl-demo.git;protocol=https \
    "

inherit cmake
