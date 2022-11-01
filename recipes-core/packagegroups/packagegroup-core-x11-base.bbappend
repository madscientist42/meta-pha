# Remove a few un-needed packages from the required packaging for the group...
# (We don't NEED Matchbox, etc. in our build- because we're using EFL/E! which
# can do as much or more than this stuff as evidenced by Tizen...)
RDEPENDS:${PN}:remove = "matchbox-terminal "
RDEPENDS:${PN}:remove = "matchbox-wm "
