# Look in the local appended project directory here...
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# Add the ADS1015/ADS1115 device support as a line-item for RaspberryPi's...
SRC_URI_append := " \
	file://ADS1015_support.cfg \
	" 

# Override the device tree definitions...
RPI_KERNEL_DEVICETREE = " \
	bcm2708-rpi-0-w.dtb \
	bcm2708-rpi-b.dtb \
	bcm2708-rpi-b-plus.dtb \
	bcm2708-rpi-cm.dtb \
	bcm2709-rpi-2-b.dtb \
	bcm2710-rpi-3-b-plus.dtb \
	bcm2710-rpi-3-b.dtb \
	bcm2710-rpi-cm3.dtb \
	"

RPI_KERNEL_DEVICETREE_raspberrypi3-64 = " \
	broadcom/bcm2710-rpi-3-b.dtb \
	broadcom/bcm2710-rpi-3-b-plus.dtb \
	broadcom/bcm2837-rpi-3-b.dtb \
	"
KERNEL_DEVICETREE = " \
	${RPI_KERNEL_DEVICETREE} \
	overlays/dwc2.dtbo \
	overlays/hifiberry-amp.dtbo \
	overlays/hifiberry-dac.dtbo \
	overlays/hifiberry-dacplus.dtbo \
	overlays/hifiberry-digi.dtbo \
	overlays/i2c-rtc.dtbo \
	overlays/iqaudio-dac.dtbo \
	overlays/iqaudio-dacplus.dtbo \
	overlays/lirc-rpi.dtbo \
	overlays/pitft22.dtbo \
	overlays/pitft28-resistive.dtbo \
	overlays/pitft35-resistive.dtbo \
	overlays/pps-gpio.dtbo \
	overlays/rpi-ft5406.dtbo \
	overlays/w1-gpio.dtbo \
	overlays/w1-gpio-pullup.dtbo \
	overlays/pi3-disable-bt.dtbo \
	overlays/pi3-miniuart-bt.dtbo \
	overlays/vc4-kms-v3d.dtbo \
	overlays/at86rf233.dtbo \
	overlays/ads1015.dtbo \
	overlays/ads1115.dtbo \
	overlays/ads7846.dtbo \
	overlays/i2c0-bcm2708.dtbo \
	overlays/i2c1-bcm2708.dtbo \
	overlays/i2c-bcm2708.dtbo \
	"


