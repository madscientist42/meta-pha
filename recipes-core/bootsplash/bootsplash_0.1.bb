DESCRIPTION = "Bootsplash testing"
LICENSE = "CLOSED"

inherit efl-theme

SRC_URI += " \
    file://theme/ \
    file://bootsplash-test \
    "

RDEPENDS_${PN} += " \
    exquisite \
    "

# Cheat and declare S to be WORKDIR so the efl-theme.bbclass can do it's thing...
S = "${WORKDIR}"

# Install our test scripting...the .bbclass we inherited from will take care
# of the install of the theme FOR us, regardless of whether we state
# a compile/install or not.
do_install() {
    install -d ${D}/usr/bin
    install -m 0755 ${WORKDIR}/bootsplash-test ${D}/usr/bin
}