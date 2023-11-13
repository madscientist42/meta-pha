# Extend out searches to our directory...make it FIRST in search.
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

# Declare out our specifications for the .xsa that belongs
# to our various machines in this layer...
HDF_BASE = "file://"

# PYNQ-Z2 for PHA purposes...which may vary from the original PYNQ project uses.
HDF_PATH:pha-pynq-z2 = "pha-pynq-z2.xsa"