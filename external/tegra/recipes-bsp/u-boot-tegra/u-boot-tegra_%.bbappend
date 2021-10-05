# Just in case we have to add patches later.  This needed the expansion
# below to properly follow Python 3.X support for build that also used
# recipe specific sysroot support.  You never know with this stuff
# Since people are lagging a bit on OE and we're working on latest (But
# not bleeding edge...)
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

# Side-step a problem with config/build that is with specifically this
# metadata set that's not obvious- we don't have a step that actually
# hands the tools build what it needs for version.h in tools.  So, we're
# going to symlink it here (which should've had a symlink or copy done
# per other patches, etc. in later versions of U-Boot...)in our recipe
# so that it's assured to do the right things period.
do_configure_append() {
    ln -s -f ${S}/include/version.h ${S}/tools
}