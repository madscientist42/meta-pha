#!/bin/sh

# udev helper script to initialize BT when rfkill is turned back on for BT
# on targets where we're handling the BT load this way...  (Right now anything
# with an AMPAK AP6212...)

export PATH="$PATH:/usr/bin"

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


