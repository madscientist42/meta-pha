WIFI_MGR = "${@bb.utils.contains('DISTRO_FEATURES', 'iwd', 'iwd', 'wpa-supplicant' ,d)}"
PACKAGECONFIG[wifi] = "--enable-wifi, --disable-wifi, ${WIFI_MGR}, ${WIFI_MGR}"
