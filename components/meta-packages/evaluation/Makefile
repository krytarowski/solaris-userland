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
include ../../../make-rules/shared-macros.mk

COMPONENT_NAME=		evaluation
COMPONENT_VERSION=	1.0
COMPONENT_SRC=
COMPONENT_BUGDB=	gate-stuff/userland

ifneq ($(BUILD_TYPE), evaluation)
PUBLISH_STAMP=
endif

# Override default settings in prep.mk so we don't delete the entire directory.
CLEAN_PATHS=	$(BUILD_DIR)
CLOBBER_PATHS=	$(BUILD_DIR)

BUILD_STYLE= pkg
include $(WS_MAKE_RULES)/common.mk
