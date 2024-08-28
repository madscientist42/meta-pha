DESCRIPTION = "High performance APRS-IS core server in C."
HOMEPAGE = "http://he.fi/aprsc/"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8db0e78737fa37cfb9fb20564b53b966"

# Sigh...broken separate builds...
inherit autotools-brokensep

DEPENDS += " \
    libevent \
    "

PACKAGES = "${PN} ${PN}-munin"

RDEPENDS:${PN} += " \
    libevent \
    "

RDEPENDS:${PN}-munin += " \
    aprsc \
    perl \
    "

SRC_URI = " \
    git://github.com/hessu/aprsc.git;protocol=https;branch=main \
    "
SRCREV = "${AUTOREV}"
S = "${WORKDIR}/git/src"

# Side step some BS in the process due to how Autoconf is being used
# by this project...  Several nasty things going on:
#
#  - It's generating a DUFF .debug binary with TMPDIR in it.
#  - It's deeply confused as to what is linking, using GCC flags
#    with LD... (Sigh)
#
# So we have to axe several debug support items in Yocto,
# followed by turning ON shared code support, followed by
# axing the offending flags...just to get it to build right.
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
DEBUG_PREFIX_MAP = ""
CFLAGS += "-fPIC"
LDFLAGS += "-shared"
LDFLAGS:remove = "-Wl,-O1"
LDFLAGS:remove = "-Wl,--hash-style=gnu"
LDFLAGS:remove = "-Wl,--as-needed"

LDFLAGS:remove = "-Wl,-z,relro,-z,now"

# Postinstall cleanup.  We don't need a chroot script or manpages...
JUNK = " \
    /usr \
    /opt/aprsc/sbin/aprsc-prepare-chroot.sh \
    "
clean_out_junk() {
    for ITEM in ${JUNK}; do
        rm -rvf ${D}${ITEM}
    done
}
do_install[postfuncs] += "clean_out_junk"

# For now, move everything to /opt for install.  It needs help, but
# that's not the same thing as packaging it properly for now.
FILES:${PN} = " \
    /opt/aprsc/logs \
    /opt/aprsc/web \
    /opt/aprsc/data \
    /opt/aprsc/etc \
    /opt/aprsc/sbin/aprsc \
    "

FILES:${PN}-munin = " \
    /opt/aprsc/sbin/aprsc_munin \
    "