# This sequence handles the FILESEXTRAPATHS:prepend FOR you so it can handle things
# IF you don't specify LOCAL_FILES_PATH, it blows up on you- use what you'd use here
# for this and the .bbclass will handle it for you and allow you to generate
# config frags for this recipe into this .bbappend dir.
LOCAL_FILES_PATH := "${THISDIR}/${PN}"
inherit pha-menuconfig

# Add the ADS1015/ADS1115 device support as a line-item for RaspberryPi's...
SRC_URI:append := " \
	file://ADS1015_support.cfg \
	file://hauppauge_usb_tuner.cfg \
	"

# Set the crypto support where we want it for the kernel.
SRC_URI:append:raspberrypi4-64 = " \
	file://crypto.cfg \
    file://google-edgetpu.cfg \
	"


