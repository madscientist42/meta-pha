DESCRIPTION = "Metasploit offensive security pen test tool suite"
HOMEPAGE = "https://github.com/rapid7/metasploit-framework.git"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=c361d35cf6b6de1a0cb4fccf49ba2a01"

DEPENDS = "ruby ruby-native bundler bundler-native openssl-native openssl ca-certificates ca-certificates-native"

S = "${WORKDIR}/git"

SRC_URI = "\
	git://github.com/rapid7/metasploit-framework.git;protocol=https;branch=master \
	"

SRCREV = "70be536b367b951d6bb690f4692e8ac19b73c29c"

do_compile() {
	export SSL_CERT_FILE="${RECIPE_SYSROOT_NATIVE}/etc/ssl/certs/ca-certificates.crt"
	${RECIPE_SYSROOT_NATIVE}/usr/bin/bundler install --path=${D}/usr --standalone --binstubs
}
