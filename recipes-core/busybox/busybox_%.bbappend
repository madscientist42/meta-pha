FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append += " \
    file://enable_timeout_cmd.cfg \
    "
