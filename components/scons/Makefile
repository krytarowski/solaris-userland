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
# Copyright (c) 2015, Oracle and/or its affiliates. All rights reserved.
#
include ../../make-rules/shared-macros.mk

COMPONENT_NAME=		scons
COMPONENT_VERSION=	2.3.4
COMPONENT_SRC=		$(COMPONENT_NAME)-$(COMPONENT_VERSION)
COMPONENT_PROJECT_URL=	http://www.scons.org/
COMPONENT_ARCHIVE=	$(COMPONENT_SRC).tar.gz
COMPONENT_ARCHIVE_HASH=	\
    sha256:4b57d72066fed3b6ff65a7dd9f73633c9b1c09f87520e9b3aae84b3e4864b441
COMPONENT_ARCHIVE_URL=	http://sourceforge.net/projects/$(COMPONENT_NAME)/files/$(COMPONENT_NAME)/$(COMPONENT_VERSION)/$(COMPONENT_ARCHIVE)/download
COMPONENT_BUGDB=	utility/scons

TPNO=			22207

# Per Q1.1.4 of the SCons FAQ, SCons is written to work with any Python
# version >= 2.4 and < 3.0. Versions 3.0 and above are not supported at
# this time.
PYTHON_VERSIONS =	2.7

include $(WS_MAKE_RULES)/prep.mk
include $(WS_MAKE_RULES)/setup.py.mk
include $(WS_MAKE_RULES)/ips.mk

# pkgdepend doesn't like the first line of a Python script to be:
# '#!/usr/bin/env python' so turn it into '#!/usr/bin/python$(PYTHON_VERSION)'
COMPONENT_POST_INSTALL_ACTION += \
	$(GSED) -i -e 's?env python?python$(PYTHON_VERSION)?' \
		$(PROTOUSRBINDIR)/scons-2.3.4;
COMPONENT_POST_INSTALL_ACTION += \
	$(GSED) -i -e 's?env python?python$(PYTHON_VERSION)?' \
		$(PROTOUSRBINDIR)/scons-time-2.3.4;
COMPONENT_POST_INSTALL_ACTION += \
	$(GSED) -i -e 's?env python?python$(PYTHON_VERSION)?' \
		$(PROTOUSRBINDIR)/sconsign-2.3.4;

PKG_PROTO_DIRS +=	$(PROTO_DIR)/$(PYTHON_VENDOR_PACKAGES)

ASLR_MODE = $(ASLR_ENABLE)

build:		$(BUILD_NO_ARCH)

install:	$(INSTALL_NO_ARCH)

test:		$(NO_TESTS)

REQUIRED_PACKAGES += library/python/setuptools-27
REQUIRED_PACKAGES += runtime/python-27