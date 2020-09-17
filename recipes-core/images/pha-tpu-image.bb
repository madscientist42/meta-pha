# Pretty much all PHA images derive from the base...
require pha-base-image.bb

IMAGE_INSTALL += " \
    google-apex \
    tensorflow-lite \
    tensorflow-lite-examples \
    "