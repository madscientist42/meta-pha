# Build image that packages enough to build kernel modules with...

# Grab the baseline...
require pha-base-image.bb

IMAGE_INSTALL += " \
	aircrack-ng \
	gpsd \
	python-pybluez \
	python-dbus \
	python-pip \
	ruby \
	gem-bundler \
	"
