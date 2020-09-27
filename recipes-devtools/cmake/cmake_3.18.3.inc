# Handle version specific stuff here...
LIC_FILES_CHKSUM = " \
    file://Copyright.txt;md5=622747147b46f22e1953876a7cba3323 \
    file://Source/cmake.h;md5=4494dee184212fc89c469c3acd555a14;beginline=1;endline=3 \
    "

SRC_URI[md5sum] = "d87c668d17cda91dbf41c52e0ffc821f"
SRC_URI[sha256sum] = "2c89f4e30af4914fd6fb5d00f863629812ada848eee4e2d29ec7e456d7fa32e5"

SRC_URI += " \
    file://0002-cmake-Prevent-the-detection-of-Qt5.patch \
    file://0003-cmake-support-OpenEmbedded-Qt4-tool-binary-names.patch \
    file://0004-Fail-silently-if-system-Qt-installation-is-broken.patch \
    "