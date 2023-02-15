DESCRIPTION = "Enhanced version of dd for forensics and security, initially developed by DoD Computer Forensics Lab (DCFL)"
HOMEPAGE = "https://github.com/resurrecting-open-source-projects/dcfldd"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = " \
    git://github.com/resurrecting-open-source-projects/dcfldd.git;protocol=https;branch=master \
    "
SRCREV = "0fac473d25391bf70ea5bb80dd9f3ac873178f89"

S = "${WORKDIR}/git"

inherit autotools