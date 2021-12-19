# Handle making something menuconfigable if it's not
# already so and to save off the config-frags in a proper
# place to be usable as a helper for doing config-frags
# "right"...

# Assign the defined LOCAL_FILES_PATH to FILESEXTRAPATHS to help avoid risky duplication
# of operations...
FILESEXTRAPATHS:prepend = "${LOCAL_FILES_PATH}:"

# Handle menuconfigs if we're not already doing so- can't do this without it.
inherit cml1

# Check to see that LOCAL_FILES_PATH is even present...if not, don't even let it menuconfig.
do_check_local_files_path() {
    if [ ! -e ${LOCAL_FILES_PATH} ] ; then
        bbfatal "LOCAL_FILES_PATH(${LOCAL_FILES_PATH}) is not present in the recipe directory."
    fi
}
do_menuconfig[prefuncs] += " do_check_local_files_path "

# Now, hook the menuconfig end to generate config_frag.cfg so you can copy
# over the precise things you menuconfig-ed for to a proper .cfg file.
do_generate_frag() {
    # Presume the .config is where it's at. Diff against the original .config in the proper place.  Drop it
    # in the recipe's files dir for easier working with integrating fragsets.  We clobber the previous one
    # so pay attention to what you're doing.
    if [ -e ${B}/.config ] ; then
        # We presume the ".old" version is there and it's the one with the changesets we care about
        if [ -e ${B}/.config.old ] ; then
            diff -uN ${B}/.config.old ${B}/.config | awk -f ${TOPDIR}/../meta-pha/classes/process_frag.awk > ${LOCAL_FILES_PATH}/config_frag.cfg
        else
            bbfatal "Did NOT have a .config.old to work with??"
        fi

        # Check to see if the diff did anything or not...
        changes=`wc -l ${LOCAL_FILES_PATH}/config_frag.cfg | awk '{print $1}'`
        if [ "$changes" = "0" ] ; then
            bbplain "${PN}-${PV}-${PV} do_menuconfig: No changes detected, cleaning up."
            rm -f ${LOCAL_FILES_PATH}/config_frag.cfg
        else
            bbplain "${PN}-${PV}-${PV} do_menuconfig: Config fragment wrote to ${LOCAL_FILES_PATH}/config_frag.cfg"
            chmod 644 ${LOCAL_FILES_PATH}/config_frag.cfg
        fi
    else
        bbfatal "Did not have a .config to process to a fragment for config updates??"
    fi
}
do_menuconfig[postfuncs] += " do_generate_frag "