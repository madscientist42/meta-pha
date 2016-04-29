# Base this image on rpi-hwup-image
include recipes-core/images/core-image-minimal.bb

# Bring in some of the pieces we need for a PHA base image...
IMAGE_INSTALL += " \
	kernel-modules \
	fuse \
	encfs \
        avahi-daemon \
	avahi-autoipd \
	iperf \
	hostap-utils \	
	"

IMAGE_FEATURES += "ssh-server-dropbear"
