# Define a MACsec capable POC image.  This is a bridge that secures one or
# more hops via bridging/brouter rules with MACsec level AES 128/256 bit
# security for the client nodes using wpa-supplicant.  Ultimately it acts
# as a secure only link into a network, blocking unsecured traffic from
# crossing the bridge.
include pha-base-image.bb

# hostapd provides the MKA edge of things.  Anything should be able to
# link up with the supplicant.  This will make the secure leg of things
# while brouter rules will do the right things for barricade/routing/etc.
#
# We want a secure link here wherein one side authenticates against
# us and then routes/bridges a frame that is MACsec to transition it
# as a hop/routing item unsecured, accepting the stuff on the far
# unsecured side into itself and securing the hop.
IMAGE_INSTALL += " \
    mka-daemon \
    mka-server-config \
    "