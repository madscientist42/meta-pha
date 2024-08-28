# Handle adding a git short-hash to the PV since SRCPV doesn't
# cleanly work.  Derive it from SRCREV silently and append to the
# PV if we're inherited from...  This way we have a discriminator
# that makes sense against packaging, etc.
#
# Note: This only works (and is only needed for...) with Git hashes.
#       It also calls for being after the LAST time you dink with
#       the contents of PV as it needs to be appended THEN.
#
#       (It has an Okie guarantee...if it breaks, you get both pieces.)
PV .= "+${@d.getVar('SRCREV', expand=True)[:8]}"