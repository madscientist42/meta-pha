# No files needed...we're explicitly changing up a behavior for the RRECOMENDS
# in neard.  It shouldn't be solely referring wpa_supplicant since IWD
# should work in concert in a similar manner there.  So, if IWD is specced
# for a DISTRO_FEATURE, we should **NOT** be jamming in wpa_supplicant there.
# Bluez & Wifi are not mandatory except for handover
WIFI_MGR = "${@bb.utils.contains('DISTRO_FEATURES', 'iwd', 'iwd', 'wpa-supplicant', d)}"
RRECOMMENDS_${PN} = "\
    ${@bb.utils.contains('DISTRO_FEATURES', 'bluetooth', '${BLUEZ}', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wifi','${WIFI_MGR}', '', d)} \
    "

