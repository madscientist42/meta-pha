#!/bin/sh
. /etc/runit/functions
msg "Enabling BATMAN-Adv mesh support..."
modprobe br-netfilter
modprobe batman-adv


