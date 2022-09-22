DESCRIPTION = "A Fork of the Doubango 3GPP IMS implementation"
HOMEPAGE = "https://github.com/madscientist42/doubango"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://Licensing.md;md5=30f1fe3b9d227ca76fe5efe11f37cb68"

inherit autotools-brokensep pkgconfig
prefix = "/usr"

# FIXME!!
#
# Current missing functionality is SRTP and OpenH264- both have bitrot going on
# due to drift in codebases on upstream.  We're not going to go and use OLD crap
# in the implementation here for PHA Linux- we've forked it to do versioning right
# so in for a penny, in for a pound.  We'll fix this over time along with the
# silly build system.

DEPENDS = " \
    alsa-lib \
    libgsm \
    openssl \
    libogg \
    libtheora \
    libvorbis \
    libopus \
    speex \
    libvpx \
    opencore-amr \
    libsrtp \
    libilbc \
    "

RDEPENDS_${PN} = " \
    alsa-lib \
    openssl \
    libogg \
    libtheora \
    libvorbis \
    libopus \
    speex \
    libvpx \
    opencore-amr \
    libsrtp \
    libilbc \
    "

SRC_URI = " \
    git://git@github.com/madscientist42/doubango.git;protocol=ssh \
    "

SRCREV = "${PV}"

S = "${WORKDIR}/git"

# There's *breakage* unless you turn libyuv support or specify it's explicit path.
#
# (Ah, autotools.  So very easy to fubar yourself on a cross-compile situation.)
#
# We're not using the support right now because it's going to take someone fixing
# the stuff they have in hand for checks, etc. as the code has drifted upstream.
#
# That being said, we should be able to bring in the rest of the features properly
# and then properly sort out the libyuv stuff in the build system either by fixing
# the broken crap or replacing all of autotools for CMake or Meson.  We only need
# one or two things turned more properly on/off and we'll have the IMS core for VoLTE
# and VoWIFI.
EXTRA_OECONF += " \
    --without-yuv \
    "