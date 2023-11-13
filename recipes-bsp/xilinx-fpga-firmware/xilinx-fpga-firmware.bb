DESCRIPTION = "FPGA Firmware Bundler for Xilinx Systems"

# This is a cheat, but there's any number of licenses 
# involved here and they're tied to the FPGA bit file
# which will have it's licensing elsewhere.  And, in most
# cases, the binary is NOT under a FOSS license.
LICENSE = "CLOSED"

# Do a bit of yocto legerdemain...select which inherit we 
# do based off of said families...  It's all the same recipe
INHERIT_CLASS = ""
INHERIT_CLASS:zynq = "dfx_dtg_zynq_full"
INHERIT_CLASS:zynqmp = "dfx_dtg_zynqmp_full"
INHERIT_CLASS:versal = "dfx_dtg_versal_full"
inherit ${INHERIT_CLASS}

# Baseline is not compatible.  Let's add machine families here
# to the list of approved so we can actually BUILD this for 
# our target
COMPATIBLE_MACHINE:zynq = "zynq"
COMPATIBLE_MACHINE:zynqmp = "zynqmp"
COMPATIBLE_MACHINE:versal = "versal"

# We're going to look in the deploy directory directly...
# it all got put in by way of the external-hdf for us to
# use here the "right" way...
FILESEXTRAPATHS:prepend := "${DEPLOY_DIR}/images/${MACHINE}:"

# This is generic for any FPGA, perversely.  It's the name Xilinx
# chose to use to gather up the contents from the .xsa so they could
# make it utterly generic for their parts.  So, now, we do it for
# OURS.
DEPENDS += "virtual/hdf"
SRC_URI += " \
    file://Xilinx-${MACHINE}.xsa \
    "