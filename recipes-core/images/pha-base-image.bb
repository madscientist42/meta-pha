# A baseline console image with a few features specified...

IMAGE_FEATURES += "package-management splash ssh-server-openssh"
IMAGE_LINGUAS = "en-us"
MACHINE_FEATURES += "usbhost wifi"

inherit core-image

# Handle RPi2 boot support...
DEPENDS_raspberrypi2 += "bcm2835-bootfiles"

# BSP specific (Drivers, etc...) and core OS features here...
CORE_OS = " \
	packagegroup-core-boot \
	packagegroup-base \
	kernel-modules \
	fuse \
	pkgconfig \
	bash \
	eudev \
	udev-rules-rpi \
	linux-firmware-ralink \
	linux-firmware-bcm43430 \
	rtl8812au-aircrack-ng \
	bluez5 \
	batctl \
	"

# Higher-level network stuff, but not things like webservers (Those are 
# apps and external tools...)
NET_SUPPORT = " \
	ethtool \
	dhcp-client \
	openssh-ssh \
	openssh-scp \
	openssh-sftp \
	openssh-sftp-server \
	openssh-misc \
	openssh-keygen \
	tcpdump \
	"

# Core WiFi tools and features...
WIFI_SUPPORT = " \
	iw \
	wireless-tools \
	wpa-supplicant \
	hostapd \
	"

TEST_SUPPORT = " \
	iperf3 \
	"

# Everything else that needs to be in the baseline image that isn't one of the above or
# an image feature item...
EXTRA_TOOLS_INSTALL = " \
	bc \
	bzip2 \
	devmem2 \
	dosfstools \
	findutils \
	i2c-tools \
	iftop \
	htop \
	less \
	nano \
	procps \
	rsync \
	sysfsutils \
	unzip \
	util-linux \
	vim-tiny \
	wget \
	zip \
	"

IMAGE_INSTALL += " \
	${CORE_OS} \
	${EXTRA_TOOLS_INSTALL} \
	${TEST_SUPPORT} \
	${NET_SUPPORT} \
	${WIFI_SUPPORT} \
	"


