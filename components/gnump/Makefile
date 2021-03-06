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

PATH=$(dir $(CC)):$(USRBINDIR):$(GNUBIN)

COMPONENT_NAME=		gnump
COMPONENT_VERSION=      4.3.2
COMPONENT_PROJECT_URL=	http://gmplib.org/
COMPONENT_SRC_NAME=	gmp
COMPONENT_ARCHIVE_HASH= \
    sha256:7be3ad1641b99b17f6a8be6a976f1f954e997c41e919ad7e0c418fe848c13c97
COMPONENT_ARCHIVE_URL=  http://ftp.gnu.org/gnu/gmp/$(COMPONENT_ARCHIVE)
COMPONENT_BUGDB=	library/gnump
COMPONENT_ANITYA_ID=	1186

TPNO=			16955

# We need to build an extra static version of this library for
# GRUB/Wanboot support.

ifeq ($(MACH),i386)

WANBOOT_CONFIGURE = $(BUILD_DIR)/wanboot-amd64/.configured
WANBOOT_BUILD = $(BUILD_DIR)/wanboot-amd64/.built
WANBOOT_INSTALL = $(BUILD_DIR)/wanboot-amd64/.installed

CONFIGURE_32_and_64 += $(WANBOOT_CONFIGURE)
BUILD_32_and_64 += $(WANBOOT_BUILD)
INSTALL_32_and_64 += $(WANBOOT_INSTALL)

endif

CONFIGURE_TARGET=
BUILD_TARGET=
INSTALL_TARGET=
include $(WS_MAKE_RULES)/gnu-component.mk

MCS = mcs
STRIP = strip

PATCH_LEVEL = 0

CLEAN_PATHS += libgmp.pc libgmpxx.pc

CFLAGS.shared += -features=extinl,extensions
CFLAGS.shared += -xustr=ascii_utf16_ushort -xcsi
# -xthreadvar=%all: allow thread-local storage via __thread
#  qualifier for all types of variables (global, static and dynamic)
CFLAGS.shared += -xthreadvar=%all $(CPP_C99_EXTENDED_MATH)
CFLAGS.shared += $(studio_C99_ENABLE) $(CPP_XPG6MODE)
CFLAGS.shared += $(studio_PIC)

# Set the wanboot CFLAGS as needed for the wanboot compilation

CFLAGS.wanboot += -D_BOOT
CFLAGS.wanboot += -g
CFLAGS.wanboot += -falign-jumps=1
CFLAGS.wanboot += -falign-loops=1
CFLAGS.wanboot += -falign-functions
CFLAGS.wanboot += -mno-mmx
CFLAGS.wanboot += -mno-3dnow
CFLAGS.wanboot += -fno-dwarf2-cfi-asm
CFLAGS.wanboot += -fno-asynchronous-unwind-tables
CFLAGS.wanboot += -fno-common
CFLAGS.wanboot += -mcmodel=large
CFLAGS.wanboot += -fno-stack-protector
CFLAGS.wanboot += -mno-stack-arg-probe

CXXFLAGS.shared = -mt -features=anachronisms,except,rtti,export,extensions,nestedaccess,tmplife,tmplrefstatic
CXXFLAGS.shared += -instances=global -template=geninlinefuncs
CXXFLAGS.shared += -xbuiltin=%none -xinline=%auto -xlibmil
CXXFLAGS.shared += -xustr=ascii_utf16_ushort -xlibmopt
CXXFLAGS.shared += $(studio_cplusplus_C99_ENABLE) $(studio_PIC)
CXXFLAGS.shared += $(CPP_XPG5MODE)
# -Qoption ccfe -features=zla: allow VLA/ZLA in C++ (like in C99)
CXXFLAGS.shared += -Qoption ccfe -features=zla
CXXFLAGS.shared += -xF=%none -xthreadvar=%all
CPPFLAGS.shared += $(CPP_POSIX) $(CPP_LARGEFILES)
LDFLAGS += $(LD_Z_REDLOCSYM) $(LD_Z_RESCAN_NOW)

CXX.studio.32 += -std=sun03
CXX.studio.64 += -std=sun03

COMPONENT_TEST_TRANSFORMS += \
     '-e "/libtool/d"' \
     '-e "/-m32/d"' \
     '-e "/-m64/d"' \
     '-e "/DEPDIR=/d"' \
     '-e "/ld: warning/d"' \
     '-e "/creating /d"' \
     '-e "/gm4 /d"' \
     '-e "s/^make\[[0-9]\{1,\}\]/make/g"'

# assembler detection for GNU MP and GNU MPFR is done via MPN_PATH
# at ./configure time. GNU MPFR wants to know GNU MP's MPN_PATH.
MPN32_i386 = x86/pentium x86 generic
MPN64_i386 = x86_64/pentium4 x86_64 generic
MPN32_sparc = sparc32/v9 sparc32 generic
MPN64_sparc = sparc64 generic
MPN_32 = $(MPN32_$(MACH))
MPN_64 = $(MPN64_$(MACH))
GM4 = /usr/bin/gm4

CONFIGURE_ENV += LD="$(CC) $(CFLAGS) $(LDFLAGS)"
CONFIGURE_ENV += CXXLD="$(CXX) $(CC_BITS) $(CXXFLAGS) $(LDFLAGS)"
CONFIGURE_ENV += CPP="$(CC) $(CPPFLAGS) $(CFLAGS) -E"
CONFIGURE_ENV += CPP_FOR_BUILD="$(CC) $(CPPFLAGS) $(CFLAGS) -E"
CONFIGURE_ENV += CXXCPP="$(CXX) $(CC_BITS) $(CPPFLAGS) $(CXXFLAGS) -E"
CONFIGURE_ENV += INSTALL="$(INSTALL)"
CONFIGURE_ENV += CC_FOR_BUILD="$(CC) $(CPPFLAGS) $(CFLAGS)"
CONFIGURE_ENV += M4="$(GM4)"
CONFIGURE_ENV += SED="$(GSED)"
CONFIGURE_ENV += ABI="$(BITS)"
CONFIGURE_ENV += "MPN_PATH=$(MPN_$(BITS))"

CONFIGURE_OPTIONS += --includedir=$(USRINCDIR)/gmp
CONFIGURE_OPTIONS += --localstatedir=$(VARDIR)
CONFIGURE_OPTIONS.shared += --enable-shared
CONFIGURE_OPTIONS.shared += --disable-static
CONFIGURE_OPTIONS += --disable-libtool-lock
CONFIGURE_OPTIONS += --disable-alloca
CONFIGURE_OPTIONS.shared += --enable-cxx
CONFIGURE_OPTIONS += --enable-fft
CONFIGURE_OPTIONS += --enable-mpbsd
CONFIGURE_OPTIONS += --disable-fat
CONFIGURE_OPTIONS.shared += --with-pic

# Set the wanboot configure options as needed for wanboot

CONFIGURE_OPTIONS.wanboot += --disable-assert
CONFIGURE_OPTIONS.wanboot += --disable-cxx
CONFIGURE_OPTIONS.wanboot += --disable-nails
CONFIGURE_OPTIONS.wanboot += --disable-profiling
CONFIGURE_OPTIONS.wanboot += --disable-minithres
CONFIGURE_OPTIONS.wanboot += --disable-shared
CONFIGURE_OPTIONS.wanboot += --enable-static
CONFIGURE_OPTIONS.wanboot += --disable-libtool-lock
CONFIGURE_OPTIONS.wanboot += --without-readline
CONFIGURE_OPTIONS.wanboot += --without-pic

PROTOUSRSHAREHTMLDIR = $(PROTOUSRSHAREDIR)/doc/gmp/html

# libgmpxx.so.4 always gets built with unnecessary paths in RUNPATH/RPATH
COMPONENT_POST_INSTALL_ACTION.shared = \
    ( $(MKDIR) $(PROTOUSRSHAREHTMLDIR) ; \
      $(MKDIR) $(PROTOUSRSHAREMAN3DIR) ; \
      $(MKDIR) $(PROTOPKGCONFIGDIR) ; \
      $(MKDIR) $(PROTOPKGCONFIGDIR64) ; \
      $(ELFEDIT) -e "dyn:runpath $(USRLIBDIR)" \
	$(PROTOUSRLIBDIR)/libgmpxx.so.4.1.2 ; \
      $(ELFEDIT) -e "dyn:runpath $(USRLIBDIR64)" \
	$(PROTOUSRLIBDIR64)/libgmpxx.so.4.1.2 ; \
      $(ELFEDIT) -e "dyn:rpath $(USRLIBDIR)" \
	$(PROTOUSRLIBDIR)/libgmpxx.so.4.1.2 ; \
      $(ELFEDIT) -e "dyn:rpath $(USRLIBDIR64)" \
	$(PROTOUSRLIBDIR64)/libgmpxx.so.4.1.2 ; \
      $(GSED) -e "s/MACH64/$(MACH64)/g" \
	$(COMPONENT_DIR)/Solaris/libgmp-64.pc > $(COMPONENT_DIR)/libgmp.pc ; \
      $(GSED) -e "s/MACH64/$(MACH64)/g" \
	$(COMPONENT_DIR)/Solaris/libgmpxx-64.pc > \
	    $(COMPONENT_DIR)/libgmpxx.pc ; \
      $(MV) $(PROTOUSRINCDIR)/gmp.h $(PROTOUSRINCDIR)/gmp/ ; \
      $(MV) $(PROTOUSRINCDIR)/mp.h $(PROTOUSRINCDIR)/gmp/ ; \
      $(INSTALL) -m 0644 $(COMPONENT_DIR)/Solaris/index.html \
	$(PROTOUSRSHAREHTMLDIR)/ ; \
      $(INSTALL) -m 0644 $(COMPONENT_DIR)/Solaris/libgmp.pc \
	$(PROTOPKGCONFIGDIR) ; \
      $(INSTALL) -m 0644 $(COMPONENT_DIR)/Solaris/libgmpxx.pc \
	$(PROTOPKGCONFIGDIR) ; \
      $(INSTALL) -m 0644 $(COMPONENT_DIR)/libgmp.pc \
	$(PROTOPKGCONFIGDIR64) ; \
      $(INSTALL) -m 0644 $(COMPONENT_DIR)/libgmpxx.pc \
	$(PROTOPKGCONFIGDIR64) ; \
      $(INSTALL) -m 0644 $(COMPONENT_DIR)/Solaris/libgmp.3 \
	$(PROTOUSRSHAREMAN3DIR)/ )

COMPONENT_POST_INSTALL_ACTION.wanboot = \
      ( $(MKDIR) $(PROTOUSRLIBDIR)/grub/wanboot; \
	$(MV) $(PROTOUSRLIBDIR64)/libgmp.a $(PROTO_DIR)/usr/lib/grub/wanboot/libgmp.a; \
	$(STRIP) -x $(PROTOUSRLIBDIR)/grub/wanboot/libgmp.a; \
	$(MCS) -d $(PROTOUSRLIBDIR)/grub/wanboot/libgmp.a; \
	$(AR) ts $(PROTOUSRLIBDIR)/grub/wanboot/libgmp.a )

# Skipping this during automated tests since it hangs sometimes
$(SKIP_TEST_AT_TOP_LEVEL)

# common targets
configure:	$(CONFIGURE_32_and_64)

$(BUILD_DIR)/$(MACH32)/.configured: CPPFLAGS += $(CPPFLAGS.shared)
$(BUILD_DIR)/$(MACH32)/.configured: CFLAGS += $(CFLAGS.shared)
$(BUILD_DIR)/$(MACH32)/.configured: CXXFLAGS += $(CXXFLAGS.shared)
$(BUILD_DIR)/$(MACH32)/.configured: CONFIGURE_OPTIONS += $(CONFIGURE_OPTIONS.shared)
$(BUILD_DIR)/$(MACH64)/.configured: CPPFLAGS += $(CPPFLAGS.shared)
$(BUILD_DIR)/$(MACH64)/.configured: CFLAGS += $(CFLAGS.shared)
$(BUILD_DIR)/$(MACH64)/.configured: CXXFLAGS += $(CXXFLAGS.shared)
$(BUILD_DIR)/$(MACH64)/.configured: CONFIGURE_OPTIONS += $(CONFIGURE_OPTIONS.shared)
$(BUILD_DIR)/wanboot-amd64/.configured: BITS=64
$(BUILD_DIR)/wanboot-amd64/.configured: COMPILER=gcc
$(BUILD_DIR)/wanboot-amd64/.configured: CPPFLAGS += $(CPPFLAGS.wanboot)
$(BUILD_DIR)/wanboot-amd64/.configured: CFLAGS += $(CFLAGS.wanboot)
$(BUILD_DIR)/wanboot-amd64/.configured: CXXFLAGS += $(CXXFLAGS.wanboot)
$(BUILD_DIR)/wanboot-amd64/.configured: CONFIGURE_OPTIONS += $(CONFIGURE_OPTIONS.wanboot)

build: $(BUILD_32_and_64)

install: $(INSTALL_32_and_64)

$(BUILD_DIR)/$(MACH32)/.installed: COMPONENT_POST_INSTALL_ACTION = $(COMPONENT_POST_INSTALL_ACTION.shared)
$(BUILD_DIR)/$(MACH64)/.installed: COMPONENT_POST_INSTALL_ACTION = $(COMPONENT_POST_INSTALL_ACTION.shared)
$(BUILD_DIR)/wanboot-amd64/.installed: COMPONENT_POST_INSTALL_ACTION = $(COMPONENT_POST_INSTALL_ACTION.wanboot)

REQUIRED_PACKAGES += system/library/c++-runtime
