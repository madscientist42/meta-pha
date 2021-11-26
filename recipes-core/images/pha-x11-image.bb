include pha-base-image.bb

IMAGE_FEATURES = "x11-base splash debug-tweaks tools-profile"


# Use our custom "core" X11 packagegroup that sets up EFL/Enlightenment as core to
# our basic X11 build.
IMAGE_INSTALL += " \
    packagegroup-enlightenment-de \
    "


