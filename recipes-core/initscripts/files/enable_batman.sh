#!/bin/sh
### BEGIN INIT INFO
# Provides:          hciattach for BT support at power on.
# Required-Start:    $remote_fs $network
# Should-Start:      
# Required-Stop:     
# Default-Start:     2 3 4 5
# Default-Stop:      0 1 6
# Short-Description: Start shadowx application
# Description:       Start/Stop ShadowX
### END INIT INFO

# PATH should only include /usr/* if it runs after the mountnfs.sh script
PATH=/sbin:/usr/sbin:/bin:/usr/bin
DESC="enable batman-adv networking support"
NAME=start_shadow
SCRIPTNAME=/etc/init.d/$NAME

case "$1" in
  start)
                echo "Enabling BATMAN-Adv mesh support..."
                modprobe br-netfilter
                modprobe batman-adv
                exit 0
                ;;

  *)
                echo "Usage: $SCRIPTNAME {start}" >&2
                exit 3
                ;;
esac


