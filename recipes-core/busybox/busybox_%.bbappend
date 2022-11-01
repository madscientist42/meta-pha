# This sequence handles the FILESEXTRAPATHS:prepend FOR you so it can handle things.
# IF you don't specify LOCAL_FILES_PATH, it blows up on you- use what you'd use here
# for this and the .bbclass will handle it for you and allow you to generate
# config frags for this recipe into this .bbappend dir.
LOCAL_FILES_PATH := "${THISDIR}/files"
inherit pha-menuconfig

SRC_URI:append = " \
    file://enable_timeout_cmd.cfg \
    "
