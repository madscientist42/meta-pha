# Unfortunately, the original pypi bbclass in the repos is broken.  They've changed
# content access methodology two years ago.  We've had cached content in the Yocto
# sources caches that hid the fact that this has happened.  Move to a new version
# or make a new package- it's BROKEN.  This at least haphazardly fixes the problem.

def pypi_package(d):
    bpn = d.getVar('BPN')
    if bpn.startswith('python-'):
        return bpn[7:]
    elif bpn.startswith('python3-'):
        return bpn[8:]
    return bpn

PYPI_PACKAGE ?= "${@pypi_package(d)}"
PYPI_PACKAGE_EXT ?= "tar.gz"

def pypi_src_uri(d):
    # Bring in requests...  We're going to need it to get the actual URI
    # for the package...
    import os
    package = d.getVar('PYPI_PACKAGE')
    package_ext = d.getVar('PYPI_PACKAGE_EXT')
    pv = d.getVar('PV')
    # NASTY, UGLY **HACK** ALERT!! - Use a response fetch from wget to get the official response from
    # PyPI's simple interface and then crack it apart from there to get the actual URL...
    cmd = os.popen('wget -q -O - https://pypi.org/simple/%s/ | awk \'BEGIN{FS="[\\"#]"} /%s-%s.%s/ {print $2}\'' % (package, package, pv, package_ext))
    actual_uri = cmd.read()
    cmd.close()
    # Check for a blind spot.  We're grabbing a redirector URI.  This means the app server won't hand us anything
    # useful for a result that we can pick off if version or name is wrong.  So flag this and DIE hard...
    if not actual_uri :
        bb.fatal("pypi-new : Unable to find %s-%s.%s on PyPI." % (package, pv, package_ext))
    return actual_uri

PYPI_SRC_URI ?= "${@pypi_src_uri(d)}"

HOMEPAGE ?= "https://pypi.python.org/pypi/${PYPI_PACKAGE}/"
SECTION = "devel/python"
SRC_URI += "${PYPI_SRC_URI}"
S = "${WORKDIR}/${PYPI_PACKAGE}-${PV}"

UPSTREAM_CHECK_URI ?= "https://pypi.python.org/pypi/${PYPI_PACKAGE}/"
UPSTREAM_CHECK_REGEX ?= "/${PYPI_PACKAGE}/(?P<pver>(\d+[\.\-_]*)+)"
