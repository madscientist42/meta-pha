# Handle frags...  Frags are additions of behavior in a patch file.  Where it's being
# taken to.  We don't care about subtractions or any of the rest in the diff
# output.  That's all context and nothing more.  We only care about where it's
# going to.  "is not set" or similar equals "=n" in this syntax, so we're transforming
# that to the other.
/+CONFIG/ {
    # Config fragment.  Process this line accordingly...  Strip the +, find out if
    # we need more transforms and then print after done.
    gsub(/\+/, "")
    gsub(/ is not set/, "=n")
    print $0
}