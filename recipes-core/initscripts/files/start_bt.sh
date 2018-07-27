#!/bin/sh

# udev helper script to initialize BT when rfkill is turned back on for BT
# on targets where we're handling the BT load this way...  (Right now anything
# with an AMPAK AP6212...)

export PATH="$PATH:/usr/bin"
LOG="/run/brcm_log.txt"

# Compute a usable BD_ADDR.  We're going to use pieces of the device serial number for the LAP part.
# FIXME - We're going to pilfer (for now) the UAP and NAP from RaspberryPI's scripting here.
SERIAL=`cat /proc/device-tree/serial-number | cut -c9-`
B1=`echo $SERIAL | cut -c3-4`
B2=`echo $SERIAL | cut -c5-6`
B3=`echo $SERIAL | cut -c7-8`
BDADDR=`printf b8:27:eb:%02x:%02x:%02x $((0x$B1 ^ 0xaa)) $((0x$B2 ^ 0xaa)) $((0x$B3 ^ 0xaa))`

# Now, try to initialize the chip.  Because of issues with firmware available, etc. the 
# blob load goes through but the tool's landmark may not be emitted.  So, we're going to
# cheat a bit.  We're going to ask it to enable HCI support against the device (some of
# the HCI attach steps) where it'll provide a "completed" landmark for us to work against
# to know we initialized.
brcm_patchram_plus -d --no2bytes --enable_hci --tosleep 5000 --bd_addr $BDADDR --patchram /lib/firmware/brcm/bcm43438a1.hcd /dev/ttyS3 >$LOG 2>&1 &

# We'll wait a minute here.  It should be DONE by then...
let TIMEOUT=60
let WAIT=1

# Loop through, checking to see if our landmark shows up.  If it does, bail out early...
while [ ${TIMEOUT} -gt 0 ] ; do
    grep -q "Done setting line discpline" ${LOG}
    if [ $? -eq 0 ] ; then
        # We may/may not still have this sitting there.  It can fail to hand us back
        # the response patchram_plus is looking for- but if we get the grepped for
        # item above, we *KNOW* it is firmware loaded because the other stages won't
        # work right if the chip's dead silent, but it will respond to the BT setup
        # instead of the Broadcom/Cypress custom HCI command...
        killall -9 brcm_patchram_plus
        
        # Check to see that it didn't choke on the init to HCI mode...
        grep -q "fail" ${LOG}
        if [ $? -ne 0 ] ; then 
            # Nope.  Launch!  Right now, it looks like the chip's only able to 
            # init to 115k...  (Sigh)
            hciattach /dev/ttyS3 any 115200 flow             
            hciconfig hci0 up
        fi
        
        # Regardless of hciconfig success/failure, exit the while loop, we've tried to load.
        TIMEOUT=0
    else
        # Wait $WAIT seconds...
        TIMEOUT=$((TIMEOUT-WAIT))
        sleep $WAIT
    fi
done

# Quietly attempt to kill it *AGAIN* since we might've timed out...
killall -9 brcm_patchram_plus

# Clean up after ourselves...logfile needs to die...
rm -f ${LOG}
