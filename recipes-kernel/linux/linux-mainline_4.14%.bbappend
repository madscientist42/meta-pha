FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.14:"

# Add a patch.  It's annoying that they're lagging the kernel work with device/board 
# support for things like the better NanoPI Neo2/Neo2+ boards.  Since we're needing
# and using it...add a patch to fix the device tree problems.  This adds the ethernet
# configuration on the Neo2 and adds the Neo2+ properly.
SRC_URI += " \
	file://0004-enable-nanopi-neo2-neo2lus.patch \
	"

# Inherit a bit of additional behavior.  uImage support... (Sigh)
inherit kernel-uimage
