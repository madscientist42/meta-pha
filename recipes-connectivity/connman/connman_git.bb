require connman.inc

SRC_URI  = " \
    git://git.kernel.org/pub/scm/network/connman/connman.git;protocol=https \
    file://connman \
    "

SRCREV = "1ee420ace2b8edb0d4025f469aaa3d00d220dc98"

SRC_URI_append_libc-musl = " file://0002-resolve-musl-does-not-implement-res_ninit.patch"

RRECOMMENDS_${PN} = "connman-conf"
RCONFLICTS_${PN} = "networkmanager"

S = "${WORKDIR}/git"