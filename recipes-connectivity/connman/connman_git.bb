require connman.inc

SRC_URI  = " \
    git://git.kernel.org/pub/scm/network/connman/connman.git;protocol=https \
    file://connman \
    "

SRCREV = "603def293a8587f2982630e3a8431a668c77e0a4"

SRC_URI_append_libc-musl = " file://0002-resolve-musl-does-not-implement-res_ninit.patch"

RRECOMMENDS_${PN} = "connman-conf"
RCONFLICTS_${PN} = "networkmanager"

S = "${WORKDIR}/git"