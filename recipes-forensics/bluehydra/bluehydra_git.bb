SUMMARY="Pwnie Express' Blue Hydra Bluetooth tracking engine..."
DESCRIPTION="Pwnie Express' Blue Hydra Bluetooth tracking engine..."

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=26eca531f08426845812a2b1780de6b4"

SRCREV = "de2d2e126713f75aea2eabeee8071fbcaca09dbf"

S = "${WORKDIR}/git"

SRC_URI = " \
    git://github.com/pwnieexpress/blue_hydra;protocol=https \
    "

# Bring in the things we MUST have, and force their build before us...
RDEPENDS_${PN} = "sqlite"

# We're a bundler packaging...
inherit bundler


