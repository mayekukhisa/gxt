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

import com.github.ajalt.clikt.core.ProgramResult
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

internal class GxtCommandTest : BaseCommandTest() {
   override val classUnderTest = GxtCommand()

   @Test
   fun testVersion() {
      assertFailsWith<ProgramResult> { classUnderTest.parse(arrayOf("--version")) }
      assertEquals("1.0.0-snapshot", outStream.dump())
   }
}
