# A baseline console image with a few features specified...

IMAGE_FEATURES += "package-management splash"
IMAGE_LINGUAS = "en-us"
MACHINE_FEATURES += "usbhost wifi"

inherit core-image

DEPENDS_raspberrypi2 += "bcm2835-bootfiles"

TASK_BASIC_SSHDAEMON = "openssh-sshd openssh-sftp openssh-sftp-server"

CORE_OS = " \
	packagegroup-core-boot \
	packagegroup-base \
	kernel-modules \
	fuse \
	pkgconfig \
	bash \
	eudev \
	udev-rules-rpi \
	linux-firmware-ath9k \
	linux-firmware-bcm43430 \
	bluez5 \
	"

WIFI_SUPPORT = " \
	iw \
	wireless-tools \
	wpa-supplicant \
	hostapd \
	"

TEST_SUPPORT = " \
	"

EXTRA_TOOLS_INSTALL = " \
	bc \
	bzip2 \
	devmem2 \
	dhcp-client \
	dosfstools \
	ethtool \
	findutils \
	i2c-tools \
	iftop \
	iperf3 \
	htop \
	less \
	nano \
	openssh-ssh \
	openssh-scp \
	openssh-misc \
	openssh-keygen \
	procps \
	rsync \
	sysfsutils \
	tcpdump \
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
	${WIFI_SUPPORT} \
	"


