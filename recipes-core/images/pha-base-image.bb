# A baseline console image with a few features specified...

IMAGE_FEATURES += "package-management splash"
IMAGE_LINGUAS = "en-us"
MACHINE_FEATURES += "usbhost wifi"

inherit core-image

# Handle RPi2 boot support...
DEPENDS_raspberrypi2 += "bcm2835-bootfiles"

# Add a few SSH daemon packagings...
TASK_BASIC_SSHDAEMON = "openssh-sshd openssh-sftp openssh-sftp-server"

# BSP specific (Drivers, etc...) and core OS features here...
CORE_OS = " \
	packagegroup-core-boot \
	packagegroup-base \
	kernel-modules \
	rtl8812au-aircrack-ng \
	fuse \
	pkgconfig \
	bash \
	eudev \
	udev-rules-rpi \
	linux-firmware-ath9k \
	linux-firmware-ath10k \
	linux-firmware-bcm43430 \
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


