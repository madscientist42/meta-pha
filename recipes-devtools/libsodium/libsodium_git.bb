DESCRIPTION = "Portable, high-performance NaCL crypto library implementation"
HOMEPAGE = "https://download.libsodium.org/doc/"
SECTION = "devel/libs"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2278eb2755b451372dde7ffeae8cde98"

S = "${WORKDIR}/git"

SRC_URI = "\
	  git://github.com/jedisct1/libsodium.git;protocol=https \
          "

SRCREV = "24daccad117311f308d7df4fae4b21d997770452"

# Builds with autotools...
inherit autotools

# But, use the autogen.sh scripting first...
do_configure_prepend() {
        cd ${S}
        NOCONFIGURE=yes ./autogen.sh
        cd ${B}
}

# Provide this to the SDK for inclusion if it's specified in the populate task list...
BBCLASSEXTEND += "native nativesdk"

