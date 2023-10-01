require pha-base-image.bb

# Add CUPS, a few other things...
IMAGE_INSTALL += " \
    cups \
    cups-filters \
    "