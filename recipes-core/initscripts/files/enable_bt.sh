#!/bin/sh

# Simple thing, really...issue an rfkill for the linux-sunxi BT device and then 
# turn it back on.  Hardware switch, does a hard reset.  We rely on udev to do
# the right things for us when this is done in the background so we can get to
# booted state quicker.

export PATH="$PATH:/usr/bin"
DEV="sunxi-bt"

function reset_bt()
{
    BTWAKE=/proc/bluetooth/sleep/btwake
    if [ -f ${BTWAKE} ]; then
        echo 0 > ${BTWAKE}
    fi
    index=`rfkill list | grep $1 | cut -f 1 -d":"` 
    if [[ -n ${index}} ]]; then
        rfkill block ${index}
        sleep 1
        rfkill unblock ${index}
        sleep 1
    fi
}

# Check to see if we have our rfkill device that we use as a reset/etc. control 
# for the BT chip...
rfkill list | grep -q $DEV
if [ $? -eq 0 ]; then
	# Bounce the BT device through the RFKILL control for the 
	# chipset...
	reset_bt "$DEV"

	# Compute a BD_ADDR.  We're going to use pieces of the device serial number for the LAP part.
	SERIAL=`cat /proc/device-tree/serial-number | cut -c9-`
	B1=`echo $SERIAL | cut -c3-4`
	B2=`echo $SERIAL | cut -c5-6`
	B3=`echo $SERIAL | cut -c7-8`
	BDADDR=`printf b8:27:eb:%02x:%02x:%02x $((0x$B1 ^ 0xaa)) $((0x$B2 ^ 0xaa)) $((0x$B3 ^ 0xaa))`

	# Now, try to initialize the chip.  This can take a small amount of time.
	brcm_patchram_plus -d --no2bytes --bd_addr $BDADDR --patchram /lib/firmware/brcm/bcm43438a1.hcd /dev/ttyS3 

	# If we're initialized, hciattach the UART device - it looks like it's only capable of 115k 
	# at start right now.
	hciattach /dev/ttyS3 any 115200 flow 
	hciconfig hci0 up    
fi

