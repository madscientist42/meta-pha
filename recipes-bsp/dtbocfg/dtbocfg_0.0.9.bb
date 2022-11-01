SUMMARY = "Dynamic Device Tree ConfigFS Kernel Module"
DESCRIPTION = "Dynamic Device Tree ConfigFS Kernel Module"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6e83d63de93384e6cce0fd3632041d91"
HOMEPAGE = "https://github.com/ikwzm/dtbocfg"

SRC_URI = " \
    git://github.com/ikwzm/dtbocfg.git;protocol=https;branch=master \
    "

SRCREV = "2d5593ba4fea35b75fce716a5d9538f984493cbd"

S = "${WORKDIR}/git"

inherit module

# Override do_install as there's no install target for the Makefile
# on this project.
do_install() {
	install -d ${D}${nonarch_base_libdir}/
	install -d ${D}${nonarch_base_libdir}/modules
	install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}
	install -m 0755 ${B}/${PN}.ko ${D}/lib/modules/${KERNEL_VERSION}/${MODULE_NAME}
}


