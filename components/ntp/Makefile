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
# Copyright (c) 2010, 2017, Oracle and/or its affiliates. All rights reserved.
#
BUILD_BITS= 64
include ../../make-rules/shared-macros.mk

COMPONENT_NAME=		ntp
COMPONENT_VERSION=	4.2.8
COMPONENT_PATCH_VERSION=	9
COMPONENT_RC_VERSION=	1
HUMAN_VERSION=		$(COMPONENT_VERSION)p$(COMPONENT_PATCH_VERSION)
IPS_COMPONENT_VERSION=	$(COMPONENT_VERSION).$(COMPONENT_PATCH_VERSION)
COMPONENT_PROJECT_URL=	http://www.ntp.org/
COMPONENT_SRC_NAME=	ntp
COMPONENT_SRC=		$(COMPONENT_SRC_NAME)-$(HUMAN_VERSION)
COMPONENT_ARCHIVE_HASH=	 \
    sha256:b724287778e1bac625b447327c9851eedef020517a3545625e9f652a90f30b72
COMPONENT_ARCHIVE_URL=	http://archive.ntp.org/ntp4/ntp-4.2/$(COMPONENT_ARCHIVE)
COMPONENT_BUGDB= service/ntp
COMPONENT_ANITYA_ID=	9025

# NTP tests log results in separate files. Instead of geting the output
# of the build and test and deleting the extra lines, it is easier to just
# cat together all of the logs. The test TEST_PROGNAME3 tests the system
# log facility, so we have to get rid of the output because the pid is
# different every time.
#
# In addition, we append the config.h file so we know if the configuration
# ever changes. We get rid of the version number which changes every time
# we upgrade and the system string and endian info which is different between
# intel and SPARC.

COMPONENT_TEST_CREATE_TRANSFORMS = \
        @if [ -e $(COMPONENT_TEST_MASTER) ]; \
        then \
		print "\#!/bin/sh" > $(COMPONENT_TEST_TRANSFORM_CMD); \
		print '$(FIND) $(BUILD_DIR_64) -name test-\*.log | sort |\\' \
		    >> $(COMPONENT_TEST_TRANSFORM_CMD); \
		print ' xargs cat  | grep -v TEST_PROGNAME3 | \\' \
		    >> $(COMPONENT_TEST_TRANSFORM_CMD); \
		print 'sed -e "s/4.2..p[0-9]*//" \\' \
		    >> $(COMPONENT_TEST_TRANSFORM_CMD); \
		print '> $(COMPONENT_TEST_SNAPSHOT)' \
		    >> $(COMPONENT_TEST_TRANSFORM_CMD); \
		print 'cat $(BUILD_DIR_64)/config.h | \\' \
		    >> $(COMPONENT_TEST_TRANSFORM_CMD); \
		print 'grep -v ENDIAN | grep -v STR_SYSTEM | \\' \
		    >> $(COMPONENT_TEST_TRANSFORM_CMD); \
		print 'grep -v VERSION | grep -v PACKAGE_STRING \\' \
		    >> $(COMPONENT_TEST_TRANSFORM_CMD); \
		print '>> $(COMPONENT_TEST_SNAPSHOT)' \
		    >> $(COMPONENT_TEST_TRANSFORM_CMD); \
	else \
		print 'Cannot find $(COMPONENT_TEST_MASTER)'; \
		exit 2; \
	fi

TPNO=			20866

PATCH_LEVEL = 0

TEST_TARGET= build $(TEST_64)
include $(WS_MAKE_RULES)/common.mk

CFLAGS +=	 -D_XOPEN_SOURCE=600 -D__EXTENSIONS__

studio_C99MODE= $(studio_C99_ENABLE)
CFLAGS.studio.i386 =	-preserve_argvalues=complete

CONFIGURE_BINDIR.64 = $(USRSBINDIR)

CONFIGURE_OPTIONS +=	--with-binsubdir=sbin
CONFIGURE_OPTIONS +=	--libexecdir=$(USRLIBDIR)/inet
CONFIGURE_OPTIONS +=	--sysconfdir=$(ETCDIR)/inet
CONFIGURE_OPTIONS +=	--enable-all-clocks
CONFIGURE_OPTIONS +=	--enable-debugging
CONFIGURE_OPTIONS +=	--enable-debug-timing
CONFIGURE_OPTIONS +=	--disable-optional-args
CONFIGURE_OPTIONS +=	--enable-parse-clocks
CONFIGURE_OPTIONS +=	--enable-ignore-dns-errors
CONFIGURE_OPTIONS +=	--without-ntpsnmpd
CONFIGURE_OPTIONS +=	--with-openssl-libdir=$(LIBDIR)/$(MACH64)
CONFIGURE_OPTIONS +=	--disable-problem-tests

REQUIRED_PACKAGES += library/libedit
REQUIRED_PACKAGES += library/security/openssl
REQUIRED_PACKAGES += $(PERL_PKG)
REQUIRED_PACKAGES += service/network/dns/mdns
REQUIRED_PACKAGES += shell/ksh93
REQUIRED_PACKAGES += system/core-os
REQUIRED_PACKAGES += system/library/math
REQUIRED_PACKAGES += system/library/security/crypto
