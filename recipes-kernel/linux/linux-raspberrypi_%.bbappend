# Look in the local appended project directory here...
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"


SRC_URI_append_raspberrypi0-wifi := " \
	file://ADS1015_support.cfg \
	"

SRC_URI_append_raspberrypi0 := " \
	file://ADS1015_support.cfg \
	"

SRC_URI_append_raspberrypi := " \
	file://ADS1015_support.cfg \
	"
