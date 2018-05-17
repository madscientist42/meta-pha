# Build image that packages enough to build kernel modules with...

# Grab the baseline...
require pha-base-image.bb

IMAGE_INSTALL += " \
	packagegroup-sdk-target \
	kernel-devsrc \
	go \
	go-helloworld \
	"
