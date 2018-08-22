# Everything but the kitchen sink with regards to dev-tools, poured into the 
# target environment...

# Grab the baseline...
require pha-base-image.bb

IMAGE_INSTALL += " \
	packagegroup-core-buildessential \
	packagegroup-core-sdk \
	packagegroup-core-standalone-sdk-target \
	packagegroup-core-tools-debug \
	packagegroup-core-tools-profile \
	packagegroup-core-tools-testapps \
	packagegroup-sdk-target \
	kernel-devsrc \
	glibc-dev \
	sudo \
	go \
	packagegroup-go-sdk-target \
	go-helloworld \
	"
