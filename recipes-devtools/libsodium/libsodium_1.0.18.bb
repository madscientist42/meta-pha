DESCRIPTION = "Portable, high-performance NaCL crypto library implementation"
HOMEPAGE = "https://download.libsodium.org/doc/"
SECTION = "devel/libs"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2278eb2755b451372dde7ffeae8cde98"

S = "${WORKDIR}/git"

SRC_URI = "\
    git://github.com/jedisct1/libsodium.git;protocol=https \
    "

SRCREV = "${PV}"

# Builds with autotools...
inherit autotools

# But, use the autogen.sh scripting first...
do_configure:prepend() {
        cd ${S}
        NOCONFIGURE=yes ./autogen.sh
        cd ${B}
}

# Provide this to the SDK for inclusion if it's specified in the populate task list...
BBCLASSEXTEND += "native nativesdk"

