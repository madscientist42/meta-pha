#!/bin/sh

if [ ${RFKILL_STATE} = 2 -o ${RFKILL_STATE} = 0 ]; then
    # Tag that we're in the off state...
    echo "PRIOR_STATE=${RFKILL_STATE}" > /run/prior_rfkill_state
else
	# (Re-)init the Bluetooth chip if it's the right type of on event...
	# if we're in the on state or the wrong class of device, do nothing...
	if [ -e /run/prior_rfkill_state ] ; then
	    . /run/prior_rfkill_state
	else
	    # We're in first run (i.e. ran before enable_bt.sh has run)...lie about it.
	    PRIOR_STATE=1
	fi
    if [ ${PRIOR_STATE} = 2 -o ${PRIOR_STATE} = 0 ]; then
        # Check to see that we're doing this for the right class of event...
        # (We only do sunxi-bt rfkills with this stuff...)
	    if [[ "${RFKILL_NAME}" == "sunxi-bt" ]] ; then
	        # We SHOULD be in a proper state to do this operation and NEED to
	        # to turn on BT function.
            nohup /etc/init.d/start_bt.sh 
            disown
        fi        
    fi
        
    # Log our next "prior" state...if it's the first-run, it'll let 
    # us keep it in the un-enabled state until someone triggers us.
	echo "PRIOR_STATE=${RFKILL_STATE}" > /run/prior_rfkill_state
fi
