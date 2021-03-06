# A baseline console image with a few features specified...

IMAGE_FEATURES += "package-management splash ssh-server-openssh empty-root-password allow-empty-password"
IMAGE_LINGUAS = "en-us"

inherit core-image

# Handle RPi2 boot support...
DEPENDS_raspberrypi2 += "bcm2835-bootfiles"

# BSP specific (Drivers, etc...) and core OS features here...
CORE_OS = " \
    packagegroup-core-boot \
    packagegroup-base \
    kernel-modules \
    kernel-devicetree \
    fuse \
    pkgconfig \
    bash \
    eudev \
    udev-rules-rpi \
    linux-firmware-ralink \
    linux-firmware-bcm43430 \
    rtl88x2bu \
    bluez5 \
    bluez5-noinst-tools \
    batctl \
    boost \
    rpe-tools \
    brcm-patchram-plus \
    ap6212-bt-firmware \
    dtbocfg \
    pha-svcs \
    "

# Higher-level network stuff, but not things like webservers (Those are
# apps and external tools...)
NET_SUPPORT = " \
    ethtool \
    dhcpcd \
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
    bridge-utils \
    f2fs-tools \
    "

# Core WiFi tools and features...
WIFI_SUPPORT = " \
    iw \
    iwd \
    "

TEST_SUPPORT = " \
    iperf3 \
    bonnie++ \
    iozone3 \
    glmark2 \
    gdb \
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

# Make our cross-compile chain include these things, just in case...
TOOLCHAIN_HOST_TASK += " \
    nativesdk-boost \
    "

inherit extrausers
EXTRA_USERS_PARAMS = " \
    useradd log; \
    usermod -p $(openssl passwd toor) root; \
    "

