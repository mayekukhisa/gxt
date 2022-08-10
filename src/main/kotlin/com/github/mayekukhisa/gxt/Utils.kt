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

import org.apache.commons.io.IOUtils
import java.io.InputStream

/**
 * Provides static utility functions for convenience.
 */
object Utils {

   /**
    * Blocks the calling thread until the process terminates.
    */
   fun Process.await() {
      val status = waitFor()
      if (status != 0) {
         System.err.println(toString(errorStream))
         GxtCommand.exit(status)
      }
   }

   /**
    * Gets the contents of an InputStream as a string.
    *
    * @param input The InputStream to read from.
    * @return The requested string.
    */
   fun toString(input: InputStream) = IOUtils.toString(input, Charsets.UTF_8).trim()
}
