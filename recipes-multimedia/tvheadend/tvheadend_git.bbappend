FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

inherit runit

SRC_URI += " \
    file://sv/tvheadend/run \
    " 

# Handle runit behaviors...
RUNIT-SERVICES = " \
    ${PN} \
    "

