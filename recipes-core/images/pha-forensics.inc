# Grab the baseline we're using for the image build.
require ${IMG_BASE}.bb

# Force the presence of specific tools in the host sysroot...
DEPENDS = "python python3 ruby-native"

IMAGE_INSTALL += " \
	aircrack-ng \
	python \
	python3 \
	python-pybluez \
	python-dbus \
	python-pip \
	ruby \
	bundler \
	nmap \
	"
