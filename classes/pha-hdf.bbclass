# We're going to force a few things.  In this metadata
# layer, we have several moving parts that want/need to 
# use the same .xsa for generation of assets.  This 
# .bbclass enforces a single source of authority for 
# naming of the files relative to a machine and where
# the file pathing points to (out of hdf-files off the
# toplevel of the metadata layer here...)
TARGET_HDF_PATH = "${TOPDIR}/../meta-pha/hdf-files"
FILESEXTRAPATHS:prepend = "${TARGET_HDF_PATH}:"

# Define out what the path to the file should be so we can 
# leverage the right behaviors in the recipe we're changing
# with this .bbclass.
TARGET_HDF_FILE = "undefined"
TARGET_HDF_FILE:pha-pynq-z2 = "pha-pynq-z2.xsa"