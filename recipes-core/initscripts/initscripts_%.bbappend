FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI = " \
	file://enable_batman.sh \
	"
	
# For now, we're going to make this subject to application when we're 
# a Neo-Plus2...
SRC_URI_append_nanopi-neo-plus2 = " \
	file://enable_bt.sh \
    "	

do_install_append () {
	# Install all of our initscripts specific to PHA Linux
	install -m 0755 ${WORKDIR}/enable_batman.sh ${D}${sysconfdir}/init.d
	update-rc.d -r ${D} enable_batman.sh start 95 5 .	
}

# For now, we're going to make this subject to application when we're 
# a Neo-Plus2...
do_install_append_nanopi-neo-plus2() {
	install -m 0755 ${WORKDIR}/enable_bt.sh ${D}${sysconfdir}/init.d	
	update-rc.d -r ${D} enable_bt.sh start 50 5 .
}
