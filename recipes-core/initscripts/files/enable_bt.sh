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

rfkill list | grep -q $DEV
if [ $? -eq 0 ]; then
    reset_bt "$DEV"
fi

