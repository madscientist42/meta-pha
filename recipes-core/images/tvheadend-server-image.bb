IMAGE_FEATURES += "package-management splash ssh-server-openssh empty-root-password allow-empty-password"
IMAGE_LINGUAS = "en-us"

inherit core-image extrausers

# Handle RPi2 boot support...
DEPENDS_raspberrypi2 += "bcm2835-bootfiles"

# Core components.
CORE_OS = " \
    packagegroup-core-boot \
    packagegroup-base \
    kernel-modules \
    kernel-devicetree \
    fuse \
    pkgconfig \
    eudev \
    udev-rules-rpi \
    linux-firmware-ralink \
    linux-firmware-bcm43430 \
    bluez5 \
    bluez5-noinst-tools \
    brcm-patchram-plus \
    dtbocfg \
    avahi-daemon \
    avahi-utils \
    "

# Higher-level network stuff, but not things like webservers (Those are 
# apps and external tools...)
NET_SUPPORT = " \
    dhcp-client \
    openssh-ssh \
    openssh-scp \
    openssh-sftp \
    openssh-sftp-server \
    openssh-misc \
    openssh-keygen \
    tcpdump \
    connman \
    connman-tools \
    connman-client \
    "

# Core WiFi tools and features...
WIFI_SUPPORT = " \
    iw \
    "

EXTRA_TOOLS_INSTALL = " \
    bzip2 \
    devmem2 \
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
    wget \
    zip \
    "

IMAGE_INSTALL += " \
    ${CORE_OS} \
    ${EXTRA_TOOLS_INSTALL} \
    ${NET_SUPPORT} \
    ${WIFI_SUPPORT} \
    dvb-apps \
    ffmpeg \
    tvheadend \
    "

EXTRA_USERS_PARAMS += " \
    useradd -U log; \
    useradd -U tvheadend; \
    "