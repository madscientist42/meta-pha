# Helper recipe that takes the place of Xilinx' platform-init
# recipe.  It takes the peeled off stuff from the .xsa that
# device-tree (with PHA Linux's .bbappend...that is...) generates
# for the user for a U-Boot platform init source file set so
# that U-Boot can make an SPL for the target hardware without using
# any proprietary code.  There's a potential problem with using 
# their bootloader on licensing terms and you didn't need any
# features past FPGA load in U-Boot (Recent addition) or in 
# the OS boot stages (Better, really, as you can now do Mender
# or Ostree/Aktualizer/etc. systems and the FW lives in UPGRADES.)

# Start with it being a participant of the virtual/platform-init
# provider set...
inherit xilinx-platform-init
PROVIDES += "virtual/xilinx-platform-init"

# We depend on Xilinx' device-tree recipe running since we're
# relying on an extended feature of it to provide us with a
# consistently "correct" version of these files out of the 
# PL's .xsa file.
DEPENDS = "device-tree"

# It's all GPL v2.0 or later...
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

# Belongs to the machine bringing it in...
PACKAGE_ARCH = "${MACHINE_ARCH}"

# Only Xilinx SoC families may apply...
COMPATIBLE_MACHINE = "$^"
COMPATIBLE_MACHINE:zynq = ".*"
COMPATIBLE_MACHINE:zynqmp = ".*"

# We don't have a SRC_URI- this is..."dynamically generated"... 
# and this recipe is using the info to fetch out the content 
# from the MACHINE's deploy directory structure because that's 
# currently a deployed item...  Don't put anything in for it in 
# this recipe if you're adding to, fixing, etc. as it will 
# break things.  The files MAY NOT exist at parse time...depends 
# on shared state, etc. as to whether they do.  So, we cribbed 
# from their original recipe, making it a smidge more intelligent.

# This is an installer engine.  No configure, compile, etc.
do_configure[noexec] = "1"
do_compile[noexec] = "1"

# Source is going to be this seemingly crazy location
# as it's a deployed item we're bundling in for build.
S = "${DEPLOY_DIR}/image/${MACHINE}/platform_init_files"

# Just simply pour the files into the target directory
# defined in our inherit above.
do_install() {
	install -d ${D}${PLATFORM_INIT_DIR}
	for srcfile in ${PLATFORM_INIT_FILES}; do
		install -m 0644 ${S}/$srcfile ${D}${PLATFORM_INIT_DIR}/
	done
}

SYSROOT_DIRS += "${PLATFORM_INIT_DIR}"
FILES:${PN} += "${PLATFORM_INIT_DIR}"
