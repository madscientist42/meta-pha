SUMMARY = "PHA specific runit services scripting"
SECTION = "base"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS = "runit"

PR = "r2"

# Make this machine specific...

SRC_URI = " \
	file://core-services/01-batman-kmod.sh \
	file://LICENSE \
	"

# For now, we're going to make this subject to application when we're
# a Neo-Plus2...
SRC_URI_append_nanopi-neo-plus2 = " \
	file://sv/bt_radio_svc/run \
    "

inherit runit

RUNIT_SERVICES = " \
    DEFAULT \
    "
