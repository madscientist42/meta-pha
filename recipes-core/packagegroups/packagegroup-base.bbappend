# This may seem redundant, stupid, etc.   The problem is that the 
# we need to choose IWD or wpa_supplicant as the WiFi manager
# for the purposes of our distro...at least until it proves out
# and can be PR-ed into mainline at Sumo or later.
RDEPENDS_packagegroup-base-wifi_remove = "wpa-supplicant"
RDEPENDS_packagegroup-base-wifi += " \
    ${@bb.utils.contains('COMBINED_FEATURES', 'iwd', 'iwd', 'wpa-supplicant',d)} \
    "
