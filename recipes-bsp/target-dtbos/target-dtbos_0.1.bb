DESCRIPTION = " \
    DTBOs for varying target systems. This is more a template for using \
    pha-dtbo.bbclass than anything else right now."

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=e5d0d4c2085d8b121508a347bfcdc057"

# Handle this as a dtbo builder recipe.  We'll be supporting autoload of those here
# in a little bit in PHA Linux.
inherit pha-dtbo

# This is very much a machine-specific build for recipe here as it
# handles differing machine DTBOs in at least part.


# Declare out our search pathing for includes here.  Use pathing relative
# to our per-package target sysroot or our staging kernel source dir for this.
# Be sure to specify all paths you need, there's only a few defaults and it
# won't go finding it all for you.  This is in expansion with any other .h
# type files you might have expected out of the target sysroot's /usr/include.
#
# Since the bbclass handles quite a few defaults for you, this is empty here.
DTS_INCLUDES += " \
    "

# Generic DTBOs- these may have a single or a machine specific definition for the
# same part, but they're common to two or more platforms.
SRC_URI = " \
    file://COPYING \
    file://test-dtbo.dts \
    "

# PI 2, 3, or 4 specific DTBOs.  These are expressions that can't be generic, per se, but
# can be similar across devices...they comprise specific SPI, I2C paths, etc. for a
# given device attached to these class of machines.
SRC_URI:append:rpi = " \
    file://waveshare-oled-display.dts \
    file://waveshare-eink-display.dts \
    "


# The bbclass takes care of the rest for you.  If you pull from a git repo and redefine
# ${S} properly like you'd do for a git fetcher, this will do the right things as well
# for you here.
#
# Mixed sets should even work if you do ${S} properly for a Version Controlled checkout
# with a few local add-ins.
