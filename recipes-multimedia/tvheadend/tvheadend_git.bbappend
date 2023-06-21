# Extend the current recipe's search into ourselves.
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

# Make it kick-start via runit.
inherit runit

SRC_URI:append = " \
    file://sv/tvheadend/run \
    "

# Handle runit behaviors- make *SURE* you have a "tvheadend" user and group or the
# expanded recipe here WILL NOT WORK.
RUNIT-SERVICES = " \
    ${PN};log \
    "

# Grab the latest...
SRCREV = "1705297c27d76848a87cff34dd6bfe7d9d74c87a"

# Handle supporting other configs for hardware that's present or not.  If the machine
# is a Pi0/1/2/3/4 then we rather wanted to shift gears and turn on OMX support.  Do this
# for each special case of HW support we're trying to support with the .bbappend we want
# whatever HW transcode support that might exist here able to be used.
DEPENDS:append:rpi= " \
    libomxil \
    "

RDEPENDS:${PN}:append:rpi = " \
    libomxil \
    "

EXTRA_OECONF += " \
    --disable-tvhcsa \
    "

EXTRA_OECONF:append:rpi = " \
    --enable-omx \
    "


