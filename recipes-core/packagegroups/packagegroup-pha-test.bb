DESCRIPTION = "Extended task to get System Test specific apps"
LICENSE = "MIT"
PR = "r1"

inherit packagegroup

TEST = "\
    hdparm \
    iperf \
    rt-tests \
    evtest \
    bc \
    memtester \
    "


RDEPENDS_${PN} = "\
    ${TEST} \
    "
