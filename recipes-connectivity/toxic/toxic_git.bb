DESCRIPTION = "ncurses based TOX P2P client"
HOMEPAGE = "https://github.com/JFreegman/toxic"
SECTION = "connectivity/chat"
LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=8f0e2cd40e05189ec81232da84bd6e1a"

S = "${WORKDIR}/git"

SRC_URI = " \
	  git://github.com/JFreegman/toxic.git;protocol=https \
	  file://build_fix.patch \
	  "

SRCREV = "70bd39eb74411f4dd0bb7adaccff54117a833932"

# We specifically and explicitly depend on the following stuffle...
DEPENDS = "ncurses curl libnotify libconfig libqrencode openal-soft freealut"
RDEPENDS_toxic = "ncurses curl libnotify libconfig libqrencode openal-soft freealut"


# Toxic is a bit of a goofy beast- it wants to build/install in one basic pass...  
# So, we're going to let it after we set the stage for an OE build of the same...
do_compile () {
	oe_runmake test > test_dump.txt

	oe_runmake PREFIX=${D}/usr
}


