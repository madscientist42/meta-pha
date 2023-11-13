# This is a nasty, hackish Kludge to work around a problem
# Xilinx made for anyone and everyone trying to make Yocto
# transparently work for their hardware...  The device-tree
# recipe ends up with the platform-init files, nice n' tidy
# from out of the .xsa, so we're going to drop them in the
# ${DEPLOYDIR} as a directory and contents off of the same
# and then make the platform init depend off of this one...
inherit xilinx-platform-init

devicetree_do_deploy:append() {
    DEPLOY_PATH="${DEPLOYDIR}/platform_init_files"
    mkdir -p ${DEPLOY_PATH}
    for INITFILE in ${PLATFORM_INIT_FILES}; do
        cp ${B}/device-tree/${INITFILE} ${DEPLOY_PATH}
    done
}
