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

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
   kotlin("jvm") version "1.6.21"
   application
}

group = "com.github.mayekukhisa.gxt"
version = "1.0.0-snapshot"

repositories {
   mavenCentral()
}

dependencies {
   implementation("com.github.ajalt.clikt:clikt:3.5.0")

   testImplementation(kotlin("test"))
   testImplementation(kotlin("test-junit5"))
}

tasks.test {
   useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
   kotlinOptions.jvmTarget = "11"
}

application {
   mainClass.set("${group}.MainKt")
}
