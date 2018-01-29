inherit rubycore

# Extend out requirements.  Need the bundler gem properly packaged, installed, etc.
DEPENDS += " \
    bundler-native \
"
RDEPENDS_${PN} += " \
    bundler \
"

bundler_do_install() {
	# Run the bundler call...this should bundle it all.  We *STILL* need to
	# declare RDEPENDS for any gems residing in the Bundler file's description
	# because this will only package OUR project we've "bundled".  This will 
	# require the respective Gems' metadata, obviously...
	${STAGING_BINDIR_NATIVE}/bundler install

	# Just like with ruby.bbclass, create symlink from the gems bin directory to /usr/bin
	if [ ! -d ${D}/${bindir} ]; then mkdir -p ${D}/${bindir}; fi
	for i in ${D}/${libdir}/ruby/gems/${RUBY_GEM_VERSION}/bin/*; do
		if [ -e "$i" ]; then
			b=`basename $i`
			ln -sf ../${libdir}/ruby/gems/${RUBY_GEM_VERSION}/bin/$b ${D}/${bindir}/$b
		fi
	done
}

EXPORT_FUNCTIONS do_install


