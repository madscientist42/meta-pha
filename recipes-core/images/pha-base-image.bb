# Base this image on rpi-hwup-image
include recipes-core/images/core-image-minimal.bb

# Bring in some of the pieces we need for a PHA base image...
IMAGE_INSTALL += " \
	kernel-modules \
	packagegroup-pha-test \
	fuse \
	encfs \
        avahi-daemon \
	avahi-autoipd \
	"

IMAGE_FEATURES += "ssh-server-dropbear"
