require connman.inc

SRC_URI  = " \
    git://git.kernel.org/pub/scm/network/connman/connman.git;protocol=https \
    file://connman \
    "

SRCREV = "1ee420ace2b8edb0d4025f469aaa3d00d220dc98"

SRC_URI:append:libc-musl = " file://0002-resolve-musl-does-not-implement-res_ninit.patch"

RRECOMMENDS:${PN} = "connman-conf"
RCONFLICTS:${PN} = "networkmanager"

S = "${WORKDIR}/git"