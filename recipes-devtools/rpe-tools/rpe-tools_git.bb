DESCRIPTION = "Portable, lightweight set of small, sharp tools for code dev"
HOMEPAGE = "https://github.com/madscientist42/rpe_tools"
SECTION = "devel/libs"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ab2b616c85c52a8640d3907ffcaf0469"

DEPENDS = "boost"
RDEPENDS_${PN} = "boost"

S = "${WORKDIR}/git"

SRC_URI = " \
    git://github.com/madscientist42/rpe_tools.git;protocol=https \
    "

SRCREV = "5d6879d6689f6235b9e0a8f5e242e4ee0dc3e2cc"

inherit cmake

PACKAGES = "${PN} ${PN}-dev ${PN}-dbg"

FILES_${PN} = " \
    /usr/lib/*.so \
    "
    
FILES_${PN}-dev = " \
    /usr/include/* \
    "
 
FILES_${PN}-dbg = " \
    /usr/src/debug/* \
    /usr/lib/.debug/* \
    "

# Turn on the SysFSGPIO class support, and turn Boost support off...
EXTRA_OECMAKE += " -DPROVIDE_SysFSGPIO=ON -DUSE_BOOST=OFF"
    
# Make it available for tooling...    
BBCLASSEXTEND += "native"


