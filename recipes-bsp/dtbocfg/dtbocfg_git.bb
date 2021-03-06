SUMMARY = "Dynamic Device Tree ConfigFS Kernel Module"
DESCRIPTION = "Dynamic Device Tree ConfigFS Kernel Module"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6e83d63de93384e6cce0fd3632041d91"
HOMEPAGE = "https://github.com/ikwzm/dtbocfg"

SRC_URI = " \
    git://github.com/ikwzm/dtbocfg.git;protocol=https \
    file://makefile-fixes.patch \
    "

SRCREV = "106b9fbf1d470f4fa1232d61b8a52d67cb8c4574"

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


