FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-{PV}:"

# Wedge in our own config items.  Use a similar method against the defconfig IN
# the workdir, mainly because we can't hook this any other way (Sigh...nothing like
# someone being "clever" and dynamically 'correcting' the defconfig before do_configure...)

# Ultimately, set a variable in defconfig
# $1 - Configure variable to be set
# $2 - value [n/y/value]
kernel_config_variable() {
	# Stage to remove the config setting in the actual defconfig file...
	CONF_SED_SCRIPT="$CONF_SED_SCRIPT /kernel_config_variable $1[ =]/d;"
	if test "$2" = "n"
	then
		echo "# CONFIG_$1 is not set" >> ${WORKDIR}/.defconfig
	else
		echo "CONFIG_$1=$2" >> ${WORKDIR}/.defconfig
	fi
}

# So, we're doing a bit of a very, very twisted thing- we're tagging onto
# the tail end of the do_kernel_configme process wherein it has made a 
# defconfig, but we've not applied the linux-raspberrypi normal edits
# to the same by it's recipe.  However, it DOES seem to work all the same
# and allows us to wedge our own mods into the defconfig.
do_kernel_configme_append () {
	rm -f ${WORKDIR}/.defconfig

	# Start setting our proper entries...
	kernel_config_variable WL_TI y
	kernel_config_variable WL1251 m
	kernel_config_variable WL1251_SDIO m
	kernel_config_variable WL12XX m
	kernel_config_variable WL18XX m
	kernel_config_variable WLCORE m
	kernel_config_variable WLCORE_SDIO m
	kernel_config_variable WILINK_PLATFORM_DATA y
	kernel_config_variable NL80211_TESTMODE y
	kernel_config_variable CFG80211_DEVELOPER_WARNINGS y
	kernel_config_variable CFG80211_REG_DEBUG y
	kernel_config_variable CFG80211_CERTIFICATION_ONUS y
	kernel_config_variable CFG80211_REG_CELLULAR_HINTS y
	kernel_config_variable CFG80211_REG_RELAX_NO_IR y
	kernel_config_variable CFG80211_DEFAULT_PS y
	kernel_config_variable CFG80211_DEBUGFS y
	kernel_config_variable CFG80211_INTERNAL_REGDB y
	kernel_config_variable CFG80211_CRDA_SUPPORT y
	kernel_config_variable CFG80211_WEXT y

	# Remove all modified configs and add the rest to defconfig
	sed -e "${CONF_SED_SCRIPT}" ${WORKDIR}/defconfig > ${WORKDIR}/tmpconfig
	cat ${WORKDIR}/.defconfig ${WORKDIR}/tmpconfig > ${WORKDIR}/defconfig
}

# Now that we actually have a wedger to put these changes in, let's slide it in before
# the rest of the stuff in the linux-raspberrypi prelims...
#addtask do_testmode_config_edits after kernel_configme before do_patch
