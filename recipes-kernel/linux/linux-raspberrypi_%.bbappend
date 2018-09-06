# Look in the local appended project directory here...
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# Add the ADS1015/ADS1115 device support as a line-item for RaspberryPi's...
SRC_URI_append := " \
	file://ADS1015_support.cfg \
	file://I2C_forced_support.cfg \
	"

# Add a few extra device tree overlays we need...
KERNEL_DEVICETREE += "\
    overlays/ads1115.dtbo \
    overlays/i2c0-bcm2708.dtbo \
    overlays/i2c1-bcm2708.dtbo \
    overlays/i2c-bcm2708.dtbo \  
    "

