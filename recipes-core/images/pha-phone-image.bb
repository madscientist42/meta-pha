# Currently derived from base.  We're likely to move to X11/Wayland
# when we get the core going.  Right now, we're going to try to bring
# up a VoLTE/VoWIFI base stack.  Worry about "*PHONE*" first and foremost
# so that we have a core to work with.
include pha-base-image.bb

# As a telecom core, we're going to bring in several CRUCIAL telecom
# stack items as internal services.  Doubango being an initial start.
IMAGE_INSTALL += " \
    doubango \
    "