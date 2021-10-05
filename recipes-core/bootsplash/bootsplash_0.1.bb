DESCRIPTION = "Experimental Bootsplash"
# FIXME - Pick a license for this.  Probably one of the Creative Commons ones...
LICENSE = "CLOSED"

# There's an automation/convenience bbclass for this to handle the theme...leverage it.
inherit efl-theme

SRC_URI += " \
    file://theme/ \
    file://splash \
    file://bootsplash-test \
    "

RDEPENDS:${PN} += " \
    exquisite \
    "

# Cheat and declare S to be WORKDIR so the efl-theme.bbclass can do it's thing...
S = "${WORKDIR}"

# Install our test scripting...the .bbclass we inherited from will take care
# of the install of the theme FOR us, regardless of whether we state
# a compile/install or not.
do_install() {
    install -d ${D}/etc/default
    install -m 0644 splash ${D}/etc/default
    install -d ${D}/usr/bin
    install -m 0755 bootsplash-test ${D}/usr/bin
}