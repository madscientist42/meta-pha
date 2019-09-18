# Look in the local appended project directory here...
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# Add the ADS1015/ADS1115 device support as a line-item for RaspberryPi's...
SRC_URI_append := " \
	file://ADS1015_support.cfg \
	" 


