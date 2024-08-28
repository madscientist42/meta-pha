DESCRIPTION = "Portable, lightweight set of small, sharp tools for code dev"
HOMEPAGE = "https://github.com/madscientist42/rpe_tools"
SECTION = "devel/libs"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ab2b616c85c52a8640d3907ffcaf0469"

S = "${WORKDIR}/git"

SRC_URI = " \
    git://github.com/madscientist42/rpe_tools.git;protocol=https;branch=master \
    "

# Right now, doing this the, "right," way is fully broken on several
# fronts (Notice to upstream eventually going up...)- so when you do
# things it either doesn't do them right (blank) or if it does, it
# screws up four ways from Sunday when you use devtool modify.  This
# makes for messy filenames on packaging, but it fully works all the
# same.  Will make a clean workaround shortly for this to use the
# short form hashing.
SRCREV = "639a0c73903e93a557d4084de9d58539a2c885b8"
PV = "git+${SRCREV}"

inherit cmake

PACKAGES = "${PN} ${PN}-dev ${PN}-dbg"

FILES:${PN} = " \
    /usr/lib/*.so \
    "

FILES:${PN}-dev = " \
    /usr/include/* \
    "

FILES:${PN}-dbg = " \
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


