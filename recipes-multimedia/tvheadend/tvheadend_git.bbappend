# Extend the current recipe's search into ourselves.
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

# Make it kick-start via runit.
inherit runit

SRC_URI += " \
    file://sv/tvheadend/run \
    " 

# Handle runit behaviors- make sure you have a "tvheadend" user and group or the
# expanded recipe here WILL NOT WORK.
RUNIT-SERVICES = " \
    ${PN};log \
    "

# Grab the latest...
SRCREV = "221c29b40b1e53ae09a69d9458442dd4fea665f5"

# Now, let's expand what it does, what it DEPENDS/RDEPENDS on because we want
# more (Like supporting HD Homeruns...) capability added to this recipe and 
# the build so that we can cut appliances for home PVRs with this stuff.
DEPENDS += " \
    libhdhomerun \
    libomxil \
    "

RDEPENDS_${PN} += " \
    libhdhomerun \
    libomxil \
    "

EXTRA_OECONF += " \
    --enable-omx \
    --enable-hdhomerun_client \
    --disable-tvhcsa \
    "


