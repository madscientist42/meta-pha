
# This sequence handles the FILESEXTRAPATHS:prepend FOR you so it can handle things.
# IF you don't specify LOCAL_FILES_PATH, it blows up on you- use what you'd use here
# for this and the .bbclass will handle it for you and allow you to generate
# config frags for this recipe into this .bbappend dir.
LOCAL_FILES_PATH := "${THISDIR}/${PN}"
inherit pha-menuconfig

# Add a few kernel configs for select additional support items...
SRC_URI:append = " \
    file://initramfs-image-bundle.cfg \
    file://configfs-support.cfg \
    file://squashfs-extensions.cfg \
    file://cifs-support.cfg \
    file://crypto-support.cfg \
    file://rfkill-support.cfg \
    file://batman-adv-support.cfg \
    "

