WIFI_MGR = "${@bb.utils.contains('DISTRO_FEATURES', 'iwd', 'iwd', 'wpa-supplicant', d)}"

