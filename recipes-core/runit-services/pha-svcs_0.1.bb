SUMMARY = "PHA specific runit services scripting"
SECTION = "base"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/LICENSE;md5=69bc7ae45dda8f7d0e5eef62685147b2"

DEPENDS = "runit"

PR = "r2"

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
