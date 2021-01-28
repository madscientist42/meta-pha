include pha-base-image.bb

IMAGE_FEATURES += "x11-base"

IMAGE_INSTALL += " \
    packagegroup-core-x11 \
    packagegroup-enlightenment-de \
    "


