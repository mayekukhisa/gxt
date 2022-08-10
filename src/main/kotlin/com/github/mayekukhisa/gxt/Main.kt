/*
 * Copyright (c) 2022 Mayeku Khisa
 *
 * Licensed under the MIT License.
 * You may not use this file except in compliance with the License as
 * appearing in the LICENSE file included in the root of this source tree.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.mayekukhisa.gxt

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.ProgramResult
import com.github.ajalt.clikt.core.context
import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.multiple
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.mayekukhisa.gxt.Utils.await

class GxtCommand : CliktCommand(
   help = "A wrapper around Git that adds convenient actions to commonly used commands",
   printHelpOnEmptyArgs = true,
) {
   private val version by option(help = "Report tool version and exit").flag()

   override fun run() {
      if (version) {
         echo(VERSION)
         exit()
      }
   }

   companion object {
      const val VERSION = "1.0.0-snapshot"

      /**
       * Terminates the currently running process.
       *
       * @param status The exit code.
       * @throws ProgramResult
       */
      fun exit(status: Int = 0) {
         throw ProgramResult(status)
      }
   }
}

/**
 * The base class for creating Gxt subcommands.
 *
 * @param help The description of the subcommand for the help output.
 * @param argsName The metavar for arguments to the subcommand.
 * @param argsHelp The description of the arguments to the subcommand for the help output.
 */
abstract class BaseSubcommand(
   help: String,
   argsName: String,
   argsHelp: String,
) : CliktCommand(
   help = help,
   treatUnknownOptionsAsArgs = true,
) {

   init {
      context {
         allowInterspersedArgs = false
      }
   }

   val args by argument(name = argsName, help = argsHelp).multiple()
}

class CommitCommand : BaseSubcommand(
   help = "Record changes to the repository",
   argsName = "COMMIT_ARGS",
   argsHelp = "Options and arguments for 'git commit'",
) {
   private val date by option(
      "-d",
      "--date",
      metavar = "DATE",
      help = "Override author and committer dates for commit",
   )

   override fun run() {
      val commitArgs = args.toMutableList()
      val processBuilder = ProcessBuilder().apply {
         redirectInput(ProcessBuilder.Redirect.INHERIT)
         redirectOutput(ProcessBuilder.Redirect.INHERIT)
      }

      // Set author and committer dates
      if (date != null) {
         val timestamp = date!!.formatTimestamp()
         processBuilder.environment()["GIT_COMMITTER_DATE"] = timestamp
         commitArgs += arrayOf("--date", timestamp)
      }

      // Execute 'git commit' command
      with(processBuilder) {
         command("git", "commit", *commitArgs.toTypedArray())
         start()
      }.await()
   }

   /**
    * Returns an absolute timestamp from a date string.
    */
   fun String.formatTimestamp(): String {
      // Execute GNU 'date' command
      val process = Runtime.getRuntime().exec(arrayOf("date", "-d", this, "+%Y-%m-%d %T.%N %z"))
      process.await()
      return Utils.toString(process.inputStream)
   }
}

fun main(args: Array<String>) {
   GxtCommand()
      .subcommands(CommitCommand())
      .main(args)
}
