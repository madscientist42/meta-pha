# Extend the search path to this directory...
FILESEXTRAPATHS:append = ":${THISDIR}/files"

# Provide for custom .xsa files from FPGA builds intended to be on specific
# machine targets here...
HDF_BASE:pha-zynq-z2 = "file://"
HDF_PATH:pha-zynq-z2 = "pha-pynq-z2.xsa"