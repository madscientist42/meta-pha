# Look in the local appended project directory here...
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# Add the ADS1015/ADS1115 device support as a line-item for RaspberryPi's...
SRC_URI:append := " \
	file://ADS1015_support.cfg \
	file://hauppauge_usb_tuner.cfg \
	"

# Set the crypto support where we want it for the kernel.
SRC_URI:append:raspberrypi4-64 = " \
	file://crypto.cfg \
	"


