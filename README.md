# kotlin-argparser-example

[![Build Status](https://travis-ci.org/xenomachina/kotlin-argparser-example.svg?branch=master)](https://travis-ci.org/xenomachina/kotlin-argparser-example)
[![License: Unlicense](https://img.shields.io/badge/license-Unlicense-blue.svg)](http://unlicense.org/)

This repo contains a simple example application that demonstrates how to use
the [kotlin-argparser](https://github.com/xenomachina/kotlin-argparser)
library.

To build it, execute the gradle task `installDist`:

    $ ./gradlew installDist

The wrapper scripts will be created in `build/install/kotlin-argparser-example/bin/`:

    $ cd build/install/kotlin-argparser-example/bin

Execute `kotlin-argparser-example` to try it out. For example, to display the
help message:

    $ ./kotlin-argparser-example --help

Or to use the application:

    $ ./kotlin-argparser-example --fast --size 5 here there everywhere
    verbose =     false
    name =        John Doe
    size =        5
    includeDirs = []
    optimizeFor = FAST
    sources =     [here, there]
    destination = everywhere
