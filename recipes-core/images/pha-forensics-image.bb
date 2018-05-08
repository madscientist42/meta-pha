# Build image that packages enough to build kernel modules with...

# Grab the baseline...
require pha-base-image.bb

# Force the presence of specific tools in the host sysroot...
DEPENDS = "python python3 ruby-native"

IMAGE_INSTALL += " \
	aircrack-ng \
	gpsd \
	python \
	python3 \
	python-pybluez \
	python-dbus \
	python-pip \
	ruby \
	gem-bundler \
	nmap \
	"
