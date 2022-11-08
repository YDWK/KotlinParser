/*
 * Copyright 2022 YDWK inc.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
package io.github.ydwk.kotlinparser

import io.github.ydwk.kotlinparser.creator.sub.KotlinType
import kotlin.test.assertEquals
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test

class ClassCreatorTest {

    @Test
    @Order(1)
    fun testClassCreator() {
        val classCreator =
            KotlinParser()
                .createKotlin("io.github.ydwk.kotlinparser")
                .name("Sample")
                .type(KotlinType.CLASS)
                .create()
                .print()

        assertEquals(
            """
                package io.github.ydwk.kotlinparser

                class Sample {}
                
            """.trimIndent(),
            classCreator.toString())
    }
}
