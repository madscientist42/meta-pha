DESCRIPTION = "Portable, lightweight set of small, sharp tools for code dev"
HOMEPAGE = "https://github.com/madscientist42/rpe_tools"
SECTION = "devel/libs"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ab2b616c85c52a8640d3907ffcaf0469"

S = "${WORKDIR}/git"

SRC_URI = " \
    git://github.com/madscientist42/rpe_tools.git;protocol=https \
    "

SRCREV = "7f498ecaaaa5549cacb1c10ba6fe0333f5ee9e44"

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

# Turn on the SysFSGPIO class support, and turn Boost support off.
# This makes for smallest footprint on things.  Boost is a gem, but 
# it adds a bit of bloat we're not needing the support out of right
# now...
EXTRA_OECMAKE += " -DPROVIDE_SysFSGPIO=ON -DUSE_BOOST=OFF -DUSE_TINYTHREAD=off"
    
# Make it available for tooling...    
BBCLASSEXTEND += "native"


