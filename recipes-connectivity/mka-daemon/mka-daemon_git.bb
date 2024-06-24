DESCRIPTION = "Automotive class MKA daemon.  Handles preshared CAK keying properly.  Skips EAP/TLS auth,"
HOMEPAGE = "https://github.com/Technica-Engineering/MKAdaemon"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=75859989545e37968a99b631ef42722e"

# Project is built via WAF and needs pkgconfig.  This is also going to be
# a runit specific packaging (If you're not using runit, you'll want to
# crib from it and extend support for systemd, sysvinit, etc.)  Being that
# it self starts, you will want to provide a config file in /etc/mkad per
# the project's config requirements.
inherit waf pkgconfig runit

# Runtime dependencies...note, if you're getting FIPS certification, you're probably
# going to want to do it ONLY ONCE and will want OpenSSL in kTLS, etc. mode and
# certify the Kernel modules.
DEPENDS = " \
    glib-2.0 \
    glib-2.0-native \
    libbsd \
    libyaml \
    libnl \
    libxml2 \
    openssl \
    "

# FIXME - We're going to use Expect to hook this silly thing.  It wants a console
# in foreground mode to STAY there.  (Sigh...)
RDEPENDS:${PN} = " \
    glib-2.0 \
    libbsd \
    libyaml \
    libnl \
    libxml2 \
    openssl \
    "

SRC_URI = " \
    gitsm://github.com/Technica-Engineering/MKAdaemon.git;protocol=https;branch=main \
    file://sv/mkad/run \
    "

# Pull from tip...  (FIXME:We have to use the full SRCREV for PV because of a bug.)
SRCREV = "9c53cd5e62363013e99c3bc4a0f6995a5704672d"
PV = "git+${SRCREV}"

S = "${WORKDIR}/git"

# Declare it to be auto-enabled under runit as a default, log the console.
RUNIT-SERVICES = "DEFAULT"
RUNIT_DEFAULT_MODS = "log"

# There's an installable that needs to be dropped in the right place that their
# WAF build does NOT do for you (Sigh...), so handle at least this much postinst.
handle_post_install_ops() {
    mkdir -p ${D}/usr/share/dbus-1/system.d
    install ${S}/dbus-policies/de.technica_engineering.mkad.conf ${D}/usr/share/dbus-1/system.d
}
do_install[postfuncs] += "handle_post_install_ops"

FILES:${PN} += "/usr/share/dbus-1"