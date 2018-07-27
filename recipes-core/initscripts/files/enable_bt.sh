#!/bin/sh

### BEGIN INIT INFO
# Provides:             brcm_patchram_plus
# Required-Start:       $remote_fs $syslog
# Required-Stop:        $remote_fs $syslog
# Default-Start:        2 3 4 5
# Default-Stop:
# Short-Description:    brcm_patchram_plus
### END INIT INFO

export PATH="$PATH:/usr/bin"

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

case "$1" in
    start|"")
	HCIATTACH=hciattach
	HCICONFIG=hciconfig
	PATCHRAM=brcm_patchram_plus
	SERIAL=`cat /proc/device-tree/serial-number | cut -c9-`
	B1=`echo $SERIAL | cut -c3-4`
	B2=`echo $SERIAL | cut -c5-6`
	B3=`echo $SERIAL | cut -c7-8`
	BDADDR=`printf b8:27:eb:%02x:%02x:%02x $((0x$B1 ^ 0xaa)) $((0x$B2 ^ 0xaa)) $((0x$B3 ^ 0xaa))`
        if [ -d /sys/class/rfkill/rfkill${index} ]; then
            reset_bt "sunxi-bt"
	fi
	$PATCHRAM -d --no2bytes --bd_addr $BDADDR --patchram /lib/firmware/brcm/bcm43438a1.hcd /dev/ttyS3 
	$HCIATTACH /dev/ttyS3 any 115200 flow 
	$HCICONFIG hci0 up
	;;

    *)
        echo "Usage: enable_bt.sh start" >&2
        exit 3
        ;;
esac
