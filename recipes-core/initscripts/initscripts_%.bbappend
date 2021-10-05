FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

# FIXME - We probably don't need to do this anymore for a neo2- there's probably a cleaner
#         runit based way to do things and we're not likely at PHA Technologies or any
#         of it's direct affiliated companies and projects to be using sysvinit anymore.
#         As it stands, the Neo2 support's busted until I lay hands on it over the next
#         handful of days because of that fact because PHA-Linux doesn't do anything
#         but runit/meta-runit these days...

SRC_URI = " \
	file://enable_batman.sh \
	"

# For now, we're going to make this subject to application when we're
# a Neo-Plus2...
SRC_URI:append:nanopi-neo-plus2 = " \
	file://enable_bt.sh \
    "

do_install:append () {
	# Install all of our initscripts specific to PHA Linux
	install -m 0755 ${WORKDIR}/enable_batman.sh ${D}${sysconfdir}/init.d
	update-rc.d -r ${D} enable_batman.sh start 95 5 .
}

# For now, we're going to make this subject to application when we're
# a Neo-Plus2...
do_install:append:nanopi-neo-plus2() {
	install -m 0755 ${WORKDIR}/enable_bt.sh ${D}${sysconfdir}/init.d
	update-rc.d -r ${D} enable_bt.sh start 50 5 .
}
