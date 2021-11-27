# Handle making something menuconfigable if it's not
# already so and to save off the config-frags in a proper
# place to be usable as a helper for doing config-frags
# "right"...

# Assign the defined LOCAL_FILES_PATH to FILESEXTRAPATHS to help avoid risky duplication
# of operations...
FILESEXTRAPATHS:prepend = "${LOCAL_FILES_PATH}:"

# Handle menuconfigs if we're not already doing so- can't do this without it.
inherit cml1

# Now, hook the menuconfig end to generate config_frag.cfg so you can copy
# over the precise things you menuconfig-ed for to a proper .cfg file.
generate_config_frag() {
    # Presume the .config is where it's at. Diff against the original .config in the proper place.  Drop it
    # in the recipe's files dir for easier working with integrating fragsets.  We clobber the previous one
    # so pay attention to what you're doing.
    if [ -e ${B}/.config ] ; then
        if [ -e ${B}/.config.orig ] ; then
            diff -uN ${B}/.config.orig ${B}/.config | awk -f ${TOPDIR}/../meta-pha/classes/process_frag.awk > ${LOCAL_FILES_PATH}/config_frag.cfg || true
        else
            diff -uN ${B}/.config.old ${B}/.config | awk -f ${TOPDIR}/../meta-pha/classes/process_frag.awk > ${LOCAL_FILES_PATH}/config_frag.cfg || true
        fi
    else
        bberror Did not have a .config to process to a fragment for config updates.
    fi
}
do_menuconfig[postfuncs] += " generate_config_frag "