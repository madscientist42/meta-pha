DESCRIPTION = "Simple TUN/TAP adapter daemon exposing it for stdio/pipe use"
LICENSE = "Unlicense"
LIC_FILES_CHKSUM = "file://UNLICENSE;md5="

# FIXME - This is going into the main upstream repo for tracking purposes.
#         It should not be used right now unless you have a copy and then
#         you can adjust this to be your server's copy of this.  It's
#         not fully vetted right now so it's internal to ECS/PHA for now.
SRC_URI = " \
    ssh://git@gitserver.local.earlconsult.com:3001/frank/tapio.git \
    "
SRCREV = "${AUTOINC}"
# FIXME : This is convoluted...but it works right with a short-hash....probably need to condense
# this into a Pythonic function that we get at globally from our .bbclass-es.
PV = "1.0+git"
S = "${WORKDIR}/git"

inherit cmake pha-shorthash
