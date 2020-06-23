LINUX_VERSION ?= "5.4.47"
LINUX_RPI_BRANCH ?= "rpi-5.4.y"

SRCREV = "dec0ddc506ab5d93a7de4b8a7c8dc98e0a96f85c"

require linux-raspberrypi_5.4.inc

SRC_URI += "file://0001-Revert-selftests-bpf-Skip-perf-hw-events-test-if-the.patch \
            file://0002-Revert-selftests-bpf-Fix-perf_buffer-test-on-systems.patch \
            file://powersave.cfg \
           "

# Bit of hackery here...  Add a few symlinks to make this work right for build
# on something earlier than Dunfell.  (Dunfell's broken...BADLY...in varying ways.)
do_configure_prepend () {
    ln -fs ${S}/arch/arm/boot/dts/overlays/act-led-overlay.dts ${S}/arch/arm/boot/dts/overlays/pi3-act-led-overlay.dts
    ln -fs ${S}/arch/arm/boot/dts/overlays/disable-bt-overlay.dts ${S}/arch/arm/boot/dts/overlays/pi3-disable-bt-overlay.dts
    ln -fs ${S}/arch/arm/boot/dts/overlays/disable-wifi-overlay.dts ${S}/arch/arm/boot/dts/overlays/pi3-disable-wifi-overlay.dts
    ln -fs ${S}/arch/arm/boot/dts/overlays/miniuart-bt-overlay.dts ${S}/arch/arm/boot/dts/overlays/pi3-miniuart-bt-overlay.dts
}