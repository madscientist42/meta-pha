# A baseline console image with a few features specified...

IMAGE_FEATURES += "package-management splash"
IMAGE_LINGUAS = "en-us"

inherit core-image

DEPENDS += "bcm2835-bootfiles"

CORE_OS = " \
	tzdata \
	kernel-modules \
	fuse \
	encfs \
        avahi-daemon \
	pkgconfig \
	bash \
	"

WIFI_SUPPORT = " \
	iw \
	wireless-tools \
	wpa-supplicant \
	hostap-utils \	
	hostapd \
	"

TEST_SUPPORT = " \
	lua \
	luajit \
	nodejs \
	nodejs-npm \
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
	openssh-sshd \
	openssh-scp \
	openssh-sftp \
	openssh-sftp-server \
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


