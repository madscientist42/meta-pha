# Build image that packages enough to build kernel modules with...

# Grab the baseline...
require pha-base-image.bb

# Force the presence of specific tools in the host sysroot...
DEPENDS = "protobuf-compiler-native python python3 ruby-native"

IMAGE_INSTALL += " \
	aircrack-ng \
	gpsd \
	python-pybluez \
	python-dbus \
	python-pip \
	ruby \
	gem-bundler \
	"
