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
# Copyright (c) 2015, 2016, Oracle and/or its affiliates. All rights reserved.
#

COMPILER =	gcc

include ../../../make-rules/shared-macros.mk

COMPONENT_NAME=         jpeg
COMPONENT_VERSION=      6.0.2
HUMAN_VERSION=          v6b
COMPONENT_PROJECT_URL=  https://sourceforge.net/projects/libjpeg/
COMPONENT_SRC=          jpeg-6b
COMPONENT_SRC_NAME=     jpegsrc.$(HUMAN_VERSION)
COMPONENT_ARCHIVE=      $(COMPONENT_SRC_NAME).tar.gz
COMPONENT_ARCHIVE_HASH= \
    sha256:75c3ec241e9996504fe02a9ed4d12f16b74ade713972f3db9e65ce95cd27e35d
COMPONENT_ARCHIVE_URL=  $(COMPONENT_PROJECT_URL)files/libjpeg/6b/$(COMPONENT_ARCHIVE)
COMPONENT_BUGDB=	library/libjpeg

TPNO=			9298

include $(WS_MAKE_RULES)/prep.mk
include $(WS_MAKE_RULES)/configure.mk
include $(WS_MAKE_RULES)/ips.mk

LDFLAGS +=		$(CC_BITS)

CONFIGURE_OPTIONS +=	--prefix=$(PROTO_DIR)/usr
CONFIGURE_OPTIONS +=	--enable-shared
CONFIGURE_OPTIONS +=	LDFLAGS="$(LDFLAGS)"

COMPONENT_INSTALL_ARGS +=	INSTALL_DATA="ginstall -D"
COMPONENT_INSTALL_ARGS +=	INSTALL_LIB="ginstall -D"
COMPONENT_INSTALL_ARGS +=	INSTALL_PROGRAM="ginstall -D"

COMPONENT_TEST_MASTER = $(COMPONENT_TEST_RESULTS_DIR)/results-all.master

ASLR_MODE =	$(ASLR_ENABLE)

configure:	$(CONFIGURE_32_and_64)

build:          $(BUILD_32_and_64)

install:        $(INSTALL_32_and_64)

test:           $(TEST_32_and_64)

system-test:    $(SYSTEM_TESTS_NOT_IMPLEMENTED)

REQUIRED_PACKAGES += shell/ksh93
REQUIRED_PACKAGES += system/library