#!/bin/sh

if [ ${RFKILL_STATE} = 2 -o ${RFKILL_STATE} = 0 ]; then
	# Do nothing
	sleep 1 &
else
	# (Re-)init the Bluetooth chip...
    	/etc/init.d/start_bt.sh &
fi
