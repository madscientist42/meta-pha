inherit rubycore

ruby_do_compile() {
	for gem in ${RUBY_BUILD_GEMS}; do
		${RUBY_COMPILE_FLAGS} gem build $gem
	done
}


ruby_do_install() {
	for gem in ${RUBY_INSTALL_GEMS}; do
		gem install --ignore-dependencies --local --env-shebang --install-dir ${D}/${libdir}/ruby/gems/${RUBY_GEM_VERSION}/ $gem
	done

	# create symlink from the gems bin directory to /usr/bin
	if [ ! -d ${D}/${bindir} ]; then mkdir -p ${D}/${bindir}; fi
	for i in ${D}/${libdir}/ruby/gems/${RUBY_GEM_VERSION}/bin/*; do
		if [ -e "$i" ]; then
			b=`basename $i`
			ln -sf ../${libdir}/ruby/gems/${RUBY_GEM_VERSION}/bin/$b ${D}/${bindir}/$b
		fi
	done
}

EXPORT_FUNCTIONS do_compile do_install


