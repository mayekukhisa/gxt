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
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

internal class CommitCommandTest : BaseCommandTest() {
   override val classUnderTest = CommitCommand()

   @Test
   override fun testInvalidArgs() {
      assertFailsWith<ProgramResult> { classUnderTest.parse(arrayOf("--invalid-option")) }
   }

   @Test
   fun testTimestamps() {
      val commitDate = with(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSSSS Z")) {
         ZonedDateTime.parse("2022-08-10 12:22:35.107224714 +0300", this)
            .withZoneSameInstant(ZoneId.systemDefault())
            .format(this)
      }

      with(classUnderTest) {
         assertEquals("Wed Aug 10 2022 12:22:35.107224714 +0300".formatTimestamp(), commitDate)
         assertEquals("Wed 10 Aug 2022 12:22:35.107224714 +0300".formatTimestamp(), commitDate)
         assertEquals("10 Aug Wed 2022 12:22:35.107224714 +0300".formatTimestamp(), commitDate)
         assertEquals("10 Aug 2022 12:22:35.107224714 +0300".formatTimestamp(), commitDate)
         assertEquals("08/10/2022 12:22:35.107224714 +0300".formatTimestamp(), commitDate)

         assertFailsWith<ProgramResult> { "08-10-2022 12:22:35.107224714 +0300".formatTimestamp() }
         assertFailsWith<ProgramResult> {
            "Wed Aug 2022 12:22:35.107224714 +0300".formatTimestamp()
         }
         assertFailsWith<ProgramResult> {
            "10 Wed Aug 2022 12:22:35.107224714 +0300".formatTimestamp()
         }
      }
   }
}
