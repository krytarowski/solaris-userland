#
# CDDL HEADER START
#
# The contents of this file are subject to the terms of the
# Common Development and Distribution License (the "License").
# You may not use this file except in compliance with the License.
#
# You can obtain a copy of the license at usr/src/OPENSOLARIS.LICENSE
# or http://www.opensolaris.org/os/licensing.
# See the License for the specific language governing permissions
# and limitations under the License.
#
# When distributing Covered Code, include this CDDL HEADER in each
# file and include the License file at usr/src/OPENSOLARIS.LICENSE.
# If applicable, add the following below this CDDL HEADER, with the
# fields enclosed by brackets "[]" replaced with your own identifying
# information: Portions Copyright [yyyy] [name of copyright owner]
#
# CDDL HEADER END
#

#
# Copyright (c) 2011, 2017, Oracle and/or its affiliates. All rights reserved.
#
BUILD_BITS= 64_and_32
include ../../make-rules/shared-macros.mk

COMPONENT_NAME=         ilmbase
COMPONENT_VERSION=      2.2.0
COMPONENT_PROJECT_URL=  http://www.openexr.com/
COMPONENT_ARCHIVE_HASH= \
    sha256:ecf815b60695555c1fbc73679e84c7c9902f4e8faa6e8000d2f905b8b86cedc7
COMPONENT_ARCHIVE_URL=  http://download.savannah.nongnu.org/releases/openexr/$(COMPONENT_ARCHIVE)
COMPONENT_BUGDB=	library/openexr
COMPONENT_ANITYA_ID=	13285

TPNO=			32620

include $(WS_MAKE_RULES)/common.mk

PATCH_LEVEL = 0

# there seems to be no other way to enable large files support
CXXFLAGS += $(CPP_LARGEFILES)
# it seems that --with-pic doesn't get PIC flags to the C++ compiler
CXXFLAGS += $(CC_PIC)
CXXFLAGS += -std=sun03
# libtools seems to be forcing -nolibs, so we have to add back libc, libm,
# libCstd and libCrun. The last two are needed because with Studio 12.2
# (and beyond), there is a bug in the CC driver that is not correctly using
# the '-library=Cstd,Crun' we are passing to it.
LIBS += -lc -lm -lCstd -lCrun

CONFIGURE_ENV += LIBS="$(LIBS)"

CONFIGURE_OPTIONS  +=           --disable-static
CONFIGURE_OPTIONS  +=           --enable-threading
CONFIGURE_OPTIONS  +=           --enable-posix-sem
CONFIGURE_OPTIONS  +=           --with-pic
CONFIGURE_OPTIONS  +=           --enable-large-stack

COMPONENT_TEST_MASTER =	$(COMPONENT_TEST_RESULTS_DIR)/results-all.master
# Strip compilation lines from test output
COMPONENT_TEST_TRANSFORMS += \
	'-e "s|^.*$(CC).*$$|XXX_CC_XXX|g" ' \
	'-e "s|^.*$(CXX).*$$|XXX_CC_XXX|g" ' \
	'-e "s|^.*source=.*libtool=no.*$$|XXX_CC_XXX|g" ' \
	'-e "s|^.*DEPDIR=.deps.*$$|XXX_CC_XXX|g" ' \
	'-e "s|^config.status: .*$$|XXX_CC_XXX|g" ' \
	'-e "s|^.*matrices took .* clocks.*$$|XXX_CC_XXX|g" ' \
	'-e "s|^.*speed up.*$$|XXX_CC_XXX|g" ' \
	'-e "/is up to date/d"' \
	'-e "/Nothing to be done/d"' \
	'-e "/Leaving directory/d"' \
	'-e "/Entering directory/d"' \
	'-e "/^XXX_CC_XXX$$/d" '

REQUIRED_PACKAGES += system/library/c++-runtime
REQUIRED_PACKAGES += system/library/math
