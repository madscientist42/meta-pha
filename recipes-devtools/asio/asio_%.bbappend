# Just cripple the pkgconfig and force it to be WITHOUT Boost
# for now (Sigh...this should've worked and built, but it's COWPILING.)
PACKAGECONFIG := ""
EXTRA_OECONF += "--without-boost"
