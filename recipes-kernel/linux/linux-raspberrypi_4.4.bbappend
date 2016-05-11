# We want *MY* defconfig...we use THIS means to inject our search path because it's
# *NOT* an extra path in the normal sense- we want it to find our defconfig and ONLY
# our defconfig.
FILESEXTRAPATHS_prepend := "${THISDIR}/${P}:"

# Let's add one patch to the kernel proper- we want to splice in the USB dongle code
# as it currently is so I can have an ARM variant of this twisted beast...
