DESCRIPTION = "Intel Inet Wireless Daemon"
HOMEPAGE = "https://git.kernel.org/pub/scm/network/wireless/iwd.git/about/"
SECTION = "networking"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=fb504b67c50331fc78734fed90fb0e09"

DEPENDS = " \
    ell \
    readline \
    dbus \
    "

RDEPENDS:${PN} = " \
    ell \
    readline \
    "

S = "${WORKDIR}/git"

SRC_URI = "\
    git://git.kernel.org/pub/scm/network/wireless/iwd.git;protocol=https \
    file://00001-avoid-nulls-being-strduped.patch \
    "

SRCREV = "${PV}"

# Builds with autotools
inherit autotools-brokensep pkgconfig

# Need to run the bootstrap script...
do_configure_prepend() {
    ${S}/bootstrap
}

# FIXME -- This is for MY purposes.  Need to turn on/off the systemd stuff per
#          distro rules.  (While I am a runit dev, I'm not going to cut them
#          off...)
EXTRA_OECONF = "--disable-systemd-service --enable-external-ell --disable-manual-pages"

FILES:${PN} += " \
    /usr/share/dbus-1/system.d/iwd-dbus.conf \
    "

# Provide this to the SDK for inclusion if it's specified in the populate task list...
BBCLASSEXTEND += "native nativesdk"

