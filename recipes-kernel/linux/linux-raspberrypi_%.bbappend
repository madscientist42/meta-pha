# Look in the local appended project directory here...
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# Add the ADS1015/ADS1115 device support as a line-item for RaspberryPi's...
SRC_URI_append := " \
	file://ADS1015_support.cfg \
	file://hauppauge_usb_tuner.cfg \
	"

SRC_URI_append_raspberrypi4-64 = " \
	file://crypto.cfg \
	"
