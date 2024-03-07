# Inherit our hdf support expansion...that way we have a consistent
# place for the firmware recipe and the old guts that need this to
# do the right things for the init, etc.
inherit pha-hdf

# Declare out our specifications for the .xsa that belongs
# to our various machines in this layer...  The .bbclass specs
# this out for us for consistency sake.
HDF_BASE = "file://"
HDF_PATH = "${TARGET_HDF_PATH}/${TARGET_HDF_FILE}"

