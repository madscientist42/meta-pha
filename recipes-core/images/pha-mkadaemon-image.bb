# Define a MACsec capable POC image.  This is a bridge that secures one or
# more hops via bridging/brouter rules with MACsec level AES 128/256 bit
# security for the client nodes using wpa-supplicant.  Ultimately it acts
# as a secure only link into a network, blocking unsecured traffic from
# crossing the bridge.
include pha-base-image.bb

# Provide a framework for proper PSK MKA support (hostapd provides adequate
# EAP-TLS support with a RADIUS server, but doesn't seem to handle Connectivity
# Association preshared keying right- so we reach for MKA Daemon which is
# an Automotive class MKA daemon for that very purpose...)  Everything presumes
# client and we hand edit the mode from that to be a SERVER for the unit
# we designate that role for.
IMAGE_INSTALL += " \
    mka-daemon \
    mka-client-config \
    tapio \
    socat \
    "

