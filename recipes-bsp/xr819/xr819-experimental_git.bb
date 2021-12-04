SUMMARY = "Allwinner XR819 kernel driver (wifi)"
DESCRIPTION = "Experimental XR819 driver derived from the Armbian fork of Allwinner's code."
HOMEPAGE = "https://github.com/fifteenhex/xradio"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a23a74b3f4caf9616230789d94217acb"

DEPENDS = "virtual/kernel"

# FIXME - we're going to try to make a real pass at something resembling
#         unifying the XR829 drvier in the D1 Tina Linux SDK- which bears
#         a stunning resemblance to the code over at fifteenhex's repo
#         and offers tantalizing things like real fault tolerance in the driver
#         and amazing things like proper completion of the P2P, monitor, and
#         mesh modes.  We'll at least bring in the best of the new features
#         which seem to be MOSTLY bolt-ons to the core driver code.  So,
#         fork for now we shall DO!  I'll make sure the checkins match a
#         tagged "version" and I'm calling versions here.  This way you will
#         get the freshest stable out of this experiment (It's an experimental
#         driver version, after all...)  If you want just the upstream (The
#         recipe will be accounting for this as things progress...), just
#         move the URI from me (madscientist42) out of the URI list and put this
#         following URI back in it's place.  It's hoped to merge back to the
#         upstream here with this- it's the "official" for the most stable
#         set of all of this.  Verified by PHA Technologies in PHA Linux latest (v0.1)
#
#           github.com/fifteenhex/xradio;protocol=https;branch=master
#
SRC_URI = " \
    git://github.com/madscientist42/xradio.git;protocol=https;branch=master \
    file://fix-sdio-c-remove-irrelevant-mach-types.patch \
    file://firmware/boot_xr819.bin \
    file://firmware/etf_xr819.bin \
    file://firmware/fw_xr819.bin \
    file://firmware/sdd_xr819.bin \
	"

# FIXME - This is the upstream (fifteenhex) repo's commit line where we forked
#         the repo to try to make magic happen.
#
# SRCREV = "6bf0e2e21c80456e2a3d4ad1267caecde7165871"

# This is the official stable set of changes...  If you want to use the stuff in
# progress that's known to largely or completely work, this is what you want to
# use here.
#
SRCREV = "v0.1"

# If you want to live *dangerously*, you can choose to use the "tip" of this fork.
#
# This is the current tip that I'm "releasing" in the release early, release often
# philosophy.  It is not assured to work, not kill your cat/dog/horse, etc. so you're
# on your own with this one.  I accept, graciously, help here- but it's viewed
# as help trying to make this robust, etc. and come willing to *help* if you're
# using this or any other commit line other than the official two above.
#
# Complaints against this tip...they'll be happily ignored by myself or anyone in
# the team on this note.
#
# SRCREV = "6bf0e2e21c80456e2a3d4ad1267caecde7165871"

S = "${WORKDIR}/git"
PV = "git+${SRCPV}"

DEPENDS = "virtual/kernel"

inherit module

EXTRA_OEMAKE = "-C ${STAGING_KERNEL_BUILDDIR} ARCH=${ARCH} M=${S}"


do_install () {
    install -d ${D}/lib/modules/${KERNEL_VERSION}
    install -d ${D}/lib/firmware/xr819
    install -m 0755 ${B}/xradio_wlan.ko ${D}/lib/modules/${KERNEL_VERSION}
    install -m 0655 ${WORKDIR}/firmware/boot_xr819.bin ${D}/lib/firmware/xr819
    install -m 0655 ${WORKDIR}/firmware/etf_xr819.bin ${D}/lib/firmware/xr819
    install -m 0655 ${WORKDIR}/firmware/fw_xr819.bin ${D}/lib/firmware/xr819
    install -m 0655 ${WORKDIR}/firmware/sdd_xr819.bin ${D}/lib/firmware/xr819
}

FILES:${PN} += " \
    /lib/firmware \
    "

