DESCRIPTION = "FPGA Firmware Bundler for Xilinx Systems"

# This is a cheat, but there's any number of licenses 
# involved here and they're tied to the FPGA bit file
# which will have it's licensing elsewhere.  And, in most
# cases, the binary is NOT under a FOSS license.
LICENSE = "CLOSED"

# Do a bit of Yocto legerdemain...select which inherit we 
# do based off of said families...  It's all the same recipe
# for the purposes of how things get done for an MPSoC or a Versal.
# We just use this HDF wrapper .bbclass to point things to 
# the right places.
INHERIT_CLASS = ""
INHERIT_CLASS:zynq = "dfx_dtg_zynq_full"
INHERIT_CLASS:zynqmp = "dfx_dtg_zynqmp_full"
INHERIT_CLASS:versal = "dfx_dtg_versal_full"
inherit ${INHERIT_CLASS} pha-hdf

# Baseline is not "compatible".  Let's add machine families here
# to the list of approved so we can actually BUILD this for 
# our target
COMPATIBLE_MACHINE:zynq = "zynq"
COMPATIBLE_MACHINE:zynqmp = "zynqmp"
COMPATIBLE_MACHINE:versal = "versal"

DEPENDS += "virtual/hdf"

SRC_URI += " \
    file://${TARGET_HDF_FILE} \
    "


