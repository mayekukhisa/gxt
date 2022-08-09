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
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option

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

fun main(args: Array<String>) {
   GxtCommand()
      .main(args)
}
