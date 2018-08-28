FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

# FIXME -- The 'gnu' at the end is "wrong" and can break under the right conditions; it's done for now to 
# get things to a nearly working state for the build so that things aren't horribly broken on the target
# system to attempt bundler operations as desired.  It should also set the stage for cross-compiling gems
# PROPERLY within the current ruby.bbclass in our layer.
RUBY_TARGET := "${TARGET_ARCH}-${TARGET_OS}-gnu"


# This snippet sets aside support for target native and "cross-compilation" - The whole thing is a hot-mess because
# *RUBY* gets really confused on it's build setup when you cross-compile it.  None of it's config variables are even
# remotely right for target operations and it doesn't work right on the ruby-native if you want to "cross-compile" 
# a native gem (It grabs the cross-compile environment for the ruby-native build, which is...heh...WRONG again.
# 
# So, we'll save off a copy of the original cross-build rbconfig.rb for OUR target in packaging to be preserved as a 
# indicator for someone of how we built, the fixed rbconfig.rb for the target so it will work right, and
# then package up that copy of the cross-build rbconfig.rb to pour on as a cross-compile switch-over for ruby-native
# so we have SOME means of making the native extension gems build right.
do_install_append_class-target() {
	# Copy off the originally generated rbconfig.rb for later cross-compile use...
	cp ${D}/${libdir}/ruby/${PV}/${RUBY_TARGET}/rbconfig.rb ${D}/${libdir}/ruby/${PV}/${RUBY_TARGET}/rbconfig-${RUBY_TARGET}.rb

	# Clean up a few sins on the saved off rbconfig.rb so it won't break on us when we run a gem build...
	sed -i -e "s,recipe-sysroot=,recipe-sysroot,g" ${D}/${libdir}/ruby/${PV}/${RUBY_TARGET}/rbconfig-${RUBY_TARGET}.rb
	sed -i -e "s,recipe-sysroot-native=,recipe-sysroot-native,g" ${D}/${libdir}/ruby/${PV}/${RUBY_TARGET}/rbconfig-${RUBY_TARGET}.rb

	# Now, clean up the rbconfig so it can work "right" in the large on the TARGET system...there's quite a lot of fubars...
	sed -i -e "s,-E /home/frank/Yocto/oe-build/build/tmp/hosttools/,,g" ${D}/${libdir}/ruby/${PV}/${RUBY_TARGET}/rbconfig.rb
	sed -i -e "s,/home/frank/Yocto/oe-build/build/tmp/hosttools/,,g" ${D}/${libdir}/ruby/${PV}/${RUBY_TARGET}/rbconfig.rb
	sed -i -e "s,--sysroot=${RECIPE_SYSROOT},,g" ${D}/${libdir}/ruby/${PV}/${RUBY_TARGET}/rbconfig.rb
	sed -i -e "s,--sysroot=${RECIPE_SYSROOT_NATIVE},,g" ${D}/${libdir}/ruby/${PV}/${RUBY_TARGET}/rbconfig.rb
	sed -i -e "s,${WORKDIR}=,,g" ${D}/${libdir}/ruby/${PV}/${RUBY_TARGET}/rbconfig.rb
	sed -i -e "s,-fdebug-prefix-map=${RECIPE_SYSROOT}=,,g" ${D}/${libdir}/ruby/${PV}/${RUBY_TARGET}/rbconfig.rb
	sed -i -e "s,-fdebug-prefix-map=${RECIPE_SYSROOT_NATIVE}=,,g" ${D}/${libdir}/ruby/${PV}/${RUBY_TARGET}/rbconfig.rb
	sed -i -e "s,-fdebug-prefix-map=${WORKDIR}/recipe-sysroot=,,g" ${D}/${libdir}/ruby/${PV}/${RUBY_TARGET}/rbconfig.rb
	sed -i -e "s,-fdebug-prefix-map=${WORKDIR}/recipe-native-sysroot=,,g" ${D}/${libdir}/ruby/${PV}/${RUBY_TARGET}/rbconfig.rb
	sed -i -e "s,${TARGET_PREFIX},,g" ${D}/${libdir}/ruby/${PV}/${RUBY_TARGET}/rbconfig.rb
	sed -i -e "s,${PV}-${PR},${PV},g" ${D}/${libdir}/ruby/${PV}/${RUBY_TARGET}/rbconfig.rb
}


