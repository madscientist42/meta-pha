# Just in case we have to add patches later.  This needed the expansion 
# below to properly follow Python 3.X support for build that also used
# recipe specific sysroot support.  You never know with this stuff
# Since people are lagging a bit on OE and we're working on latest (But
# not bleeding edge...)
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"


# "Fix" things for Python 3.X support...this will periodically break but until 
# we can un-pooch this build process so it can use stock U-Boot's build and
# do the right things for this image, we'll live with it.
BUILD_CFLAGS += "  -I${RECIPE_SYSROOT_NATIVE}/usr/include/python3.5m "


