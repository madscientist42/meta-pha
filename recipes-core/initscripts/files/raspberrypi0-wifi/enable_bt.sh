# IF you're using a Zero-W, we've Bluetooth, but it loads up differently
# (It is provided as part of the RasPI stuff, but we need to CALL it 
# automatically in PHA-Linux because we expect it ON)

# This loads up firmware on the right uart and attaches hci0 to it.
/usr/bin/btuart

# But, it doesn't bring the link UP...
/usr/bin/hciconfig hci0 up
