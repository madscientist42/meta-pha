require connman.inc

SRC_URI  = " \
    git://git.kernel.org/pub/scm/network/connman/connman.git;protocol=https \
    file://connman \
    "

SRCREV = "0ef47336dc92b101c6d87ed3a9fff4db36096007"
PV = "git+${SRCPV}"

SRC_URI:append:libc-musl = " file://0002-resolve-musl-does-not-implement-res_ninit.patch"

RRECOMMENDS:${PN} = "connman-conf"
RCONFLICTS:${PN} = "networkmanager"

S = "${WORKDIR}/git"