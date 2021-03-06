# Getting started with the Userland Consolidation

## Getting Started
This README provides a very brief overview of the gate, how to retrieve
a copy, and how to build it.  Detailed documentation about the Userland
gate can be found in the 'doc' directory.

## Overview
The Userland consolidation maintains a project at

     https://github.com/oracle/solaris-userland

That repo contains build recipes, patches, IPS manifests, etc. necessary
to download, prep, build, test, package and publish open source software.
The build infrastructure is similar to that of the SFW consolidation in
that it makes use of hierarchical Makefiles which provide dependency and
recipe information for building the components.  In order to build the
contents of the Userland gate, you need to clone it.  Since you are
reading this, you probably already have.

## Getting the Bits
As mentioned, the gate is stored in a Mercurial repository.  In order to
build or develop in the gate, you will need to clone it.  You can do so
with the following command

    $ git clone https://github.com/oracle/solaris-userland.git /scratch/clone

This will create a replica of the various pieces that are checked into the
source code management system, but it does not retrieve the community
source archives associated with the gate content.  To download the
community source associated with your cloned workspace, you will need to
execute the following:

    $ cd /scratch/clone/components
    $ gmake download

This will use GNU make and the downloading tool in the gate to walk through
all of the component directories downloading and validating the community
source archives from the gate machine or their canonical source repository.

There are two variation to this that you may find interesting.  First, you
can cause gmake(1) to perform it's work in parallel by adding '-j (jobs)'
to the command line.  Second, if you are only interested in working on a
particular component, you can change directories to that component's
directory and use 'gmake download' from that to only get it's source
archive.

## Building the Bits.
You can build individual components or the contents of the entire gate.

### Component build
If you are only working on a single component, you can just build it using
following:

Setup the workspace for building components

    $ cd (your-workspace)/components ; gmake setup

Build the individual component

    $ cd (component-dir) ; gmake publish

### Complete Top Down build
Complete top down builds are also possible by simply running

    $ cd (your-workspace)/components
    $ gmake publish

The 'publish' target will build each component and publish it to the
workspace IPS repo.

Tools to help facilitate build zone creation will be integrated
shortly.  If the zone you create to build your workspace in does not have
networking enabled, you can pre-download any community source archives into
your workspace from the global with:

    $ cd (your-workspace)/components
    $ gmake download

- You can add parallelism to your builds by adding '-j (jobs)' to your gmake
  command line arguments.
- The gate should only incrementally build what it needs to based on what has
  changed since you last built it.
  
# Copyright
Copyright (c) 2010, 2017, Oracle and/or its affiliates. All rights reserved.
