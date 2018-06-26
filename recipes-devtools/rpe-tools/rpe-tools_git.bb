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

SRCREV = "a146e7346521f24550fc0b721037d59bd9e809be"

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


