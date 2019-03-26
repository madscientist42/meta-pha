# Change the WiFi config rules...
WIFI_EN_OPTS = "${@bb.utils.contains('DISTRO_FEATURES', 'iwd', '--enable-iwd --enable-wifi', '--disable-wifi', d)}"
WIFI_DEPS = "${@bb.utils.contains('DISTRO_FEATURES', 'iwd', 'iwd', 'wpa-supplicant', d)}"
PACKAGECONFIG[wifi] = "${WIFI_EN_OPTS}, --disable-wifi, ${WIFI_DEPS}, ${WIFI_DEPS}"
