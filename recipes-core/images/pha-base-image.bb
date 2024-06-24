# A baseline console image with a few features specified...
inherit core-image
IMAGE_FEATURES += "package-management splash ssh-server-openssh empty-root-password allow-empty-password debug-tweaks"
IMAGE_LINGUAS = "en-us"

# Handle RPi2 boot support...
DEPENDS:apppend:raspberrypi2 := "bcm2835-bootfiles"

# Handle hooking the right Device Tree recipe to handle this for
# normal systems and at least the Xilinx systems...
SYSTEM_DEVICETREE = "kernel-devicetree"
SYSTEM_DEVICETREE:zynq = "device-tree"
SYSTEM_DEVICETREE:zynqmp = "device-tree"

# BSP specific (Drivers, etc...) and core OS features here...
CORE_OS = " \
    packagegroup-core-boot \
    packagegroup-base \
    kernel-modules \
    ${SYSTEM_DEVICETREE} \
    fuse \
    pkgconfig \
    bash \
    eudev \
    bluez5 \
    bluez5-noinst-tools \
    batctl \
    boost \
    rpe-tools \
    brcm-patchram-plus \
    dtbocfg \
    pha-svcs \
    target-dtbos \
    ncdu \
    procrank \
    "

# Remove a few things from above IF we're running an X86_64 QEMU or similar
CORE_OS:remove:qemux86-64 = " dtbocfg"
CORE_OS:remove:qemux86-64 = " kernel-devicetree"
CORE_OS:remove:intel-corei7-64 = " dtbocfg"
CORE_OS:remove:intel-corei7-64 = " kernel-devicetree"

# Also remove a few things from above IF we're on a Xilinx platform.
# (Their fork of the Kernel has their OWN version of this functionality.)
CORE_OS:remove:zynq = " dtbocfg"
CORE_OS:remove:zynqmp = " dtbocfg"

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
    bridge-utils \
    connman \
    connman-tools \
    connman-client \
    f2fs-tools \
    iproute2 \
    "


# Core WiFi tools and features...
WIFI_SUPPORT = " \
    iw \
    iwd \
    "

# General benchmarking items...
BENCHMARK_SUPPORT = ""
BENCHMARK_SUPPORT:benchmark = " \
    iperf3 \
    bonnie++ \
    iozone3 \
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

# Break out additional FPGA support...  (FIXME : Need to make this be
# a bit more Xilinx specific so that a Zynq/ZynqMP/Versal can trigger
# the current ones.  That being said, this is pretty much a ONE-STOP-SHOP.
# It will now let you use either first-stage bootloader and load the FPGA
# as a task item in LINUX instead of the bootloader.  Just feed it the
# external-hdf as a .bbappend, reference xilinx-fpga-firmware and GO.)
FPGA_SUPPORT = " \
    fpga-manager-script \
    xilinx-fpga-firmware \
    "

# Define out the image install from the above lists...
IMAGE_INSTALL += " \
    ${CORE_OS} \
    ${EXTRA_TOOLS_INSTALL} \
    ${BENCHMARK_SUPPORT} \
    ${NET_SUPPORT} \
    ${WIFI_SUPPORT} \
    "

# Add in for the only FPGA SOC target we have for right now so it's
# as described above.  Plug and play, just build and go.  We'll
# clean this up better later.  Otherwise this is *BROKEN* for any
# person trying to use this build recipe that doesn't build for
# one of our supported FPGA targets.
IMAGE_INSTALL:append:pha-fpga  = " \
    ${FPGA_SUPPORT} \
    "

# Make our cross-compile chain include these things, just in case...
TOOLCHAIN_HOST_TASK += " \
    nativesdk-boost \
    "

inherit extrausers
EXTRA_USERS_PARAMS = " \
    useradd log; \
    groupadd wheel; \
    groupadd netdev; \
    "
