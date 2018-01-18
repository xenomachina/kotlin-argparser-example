// This is free and unencumbered software released into the public domain.
//
// Anyone is free to copy, modify, publish, use, compile, sell, or
// distribute this software, either in source code form or as a compiled
// binary, for any purpose, commercial or non-commercial, and by any
// means.
//
// In jurisdictions that recognize copyright laws, the author or authors
// of this software dedicate any and all copyright interest in the
// software to the public domain. We make this dedication for the benefit
// of the public at large and to the detriment of our heirs and
// successors. We intend this dedication to be an overt act of
// relinquishment in perpetuity of all present and future rights to this
// software under copyright law.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
// EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
// MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
// IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
// OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
// ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
// OTHER DEALINGS IN THE SOFTWARE.
//
// For more information, please refer to <http://unlicense.org/>

package com.xenomachina.argparser.example

import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.SystemExitException
import com.xenomachina.argparser.default
import com.xenomachina.argparser.mainBody
import java.io.File
import java.lang.System

enum class OptimizationMode { GOOD, FAST, CHEAP }

/**
 * These are the arguments to our program. Each of the properties uses a delegate from an ArgParser, which maps it to
 * option(s) or positional argument(s).
 */
class ExampleArgs(parser: ArgParser) {
    val verbose by parser.flagging("-v", "--verbose",
            help = "enable verbose mode")

    val name by parser.storing("-N", "--name",
            help = "name of the widget").default("John Doe")

    val size by parser.storing("-s", "--size",
            help = "size of the plumbus") { toInt() }

    val includeDirs by parser.adding("-I",
            help = "directory to search for header files") { File(this) }

    val optimizeFor by parser.mapping(
            "--good" to OptimizationMode.GOOD,
            "--fast" to OptimizationMode.FAST,
            "--cheap" to OptimizationMode.CHEAP,
            help = "what to optimize for")

    val sources by parser.positionalList("SOURCE",
            help = "source filename",
            sizeRange = 1..Int.MAX_VALUE)

    val destination by parser.positional("DEST",
            help = "destination filename")
}

/**
 * In the main function of our program we call mainBody, (optionally) telling it the name of our program. mainBody will
 * handle any [SystemExitException] thrown by the [ArgParser] or its delegates. This includes displaying `--help` as
 * well as error messages, and exiting the process with an appropriate status code.
 */
fun main(args: Array<String>) = mainBody(System.getProperty("com.xenomachina.argparser.main")) {
        ExampleArgs(ArgParser(args)).run {
            println("""
                verbose =     $verbose
                name =        $name
                size =        $size
                includeDirs = $includeDirs
                optimizeFor = $optimizeFor
                sources =     $sources
                destination = $destination""".trimIndent())
        }
    }
