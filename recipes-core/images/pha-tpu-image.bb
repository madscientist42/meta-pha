# Pretty much all PHA images derive from the base, forensics, or X11
# (In this case, X11...)
require pha-x11-image.bb

IMAGE_INSTALL += " \
    python3-tensorflow-lite \
    "