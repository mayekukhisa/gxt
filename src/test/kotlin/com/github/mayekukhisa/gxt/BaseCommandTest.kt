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
import com.github.ajalt.clikt.core.NoSuchOption
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertFailsWith

internal abstract class BaseCommandTest {
   private val stderr = System.err
   private val stdout = System.out

   private val errStream = ByteArrayOutputStream()
   val outStream = ByteArrayOutputStream()

   abstract val classUnderTest: CliktCommand

   @BeforeTest
   fun setUP() {
      System.setErr(PrintStream(errStream))
      System.setOut(PrintStream(outStream))
   }

   @AfterTest
   fun tearDown() {
      System.setErr(stderr)
      System.setOut(stdout)
   }

   @Test
   open fun testInvalidArgs() {
      assertFailsWith<NoSuchOption> { classUnderTest.parse(arrayOf("--invalid-option")) }
   }

   /**
    * Converts the buffer's contents into a string and clears the buffer.
    *
    * @return The string decoded from the buffer's contents.
    */
   fun ByteArrayOutputStream.dump(): String = with(this) { toString().trim() }.also { reset() }
}
