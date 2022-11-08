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
package io.github.ydwk.kotlinparser.creator

import io.github.ydwk.kotlinparser.creator.sub.FunctionCreator
import io.github.ydwk.kotlinparser.creator.sub.KotlinClassCreator
import io.github.ydwk.kotlinparser.creator.sub.KotlinType
import io.github.ydwk.kotlinparser.modifiy.KotlinModifier

interface KotlinCreator {

    /**
     * Adds import to the kotlin file.
     *
     * @param import The import to add
     * @return The current [KotlinCreator] instance
     */
    fun addImport(import: String): KotlinCreator

    /**
     * Set the type of the class.
     *
     * @param type The type of the class
     * @return The current [KotlinCreator] instance
     */
    fun type(type: KotlinType): KotlinCreator

    /**
     * Sets the class name.
     *
     * @param name The name of the class
     * @return The current [KotlinCreator] instance
     */
    fun name(name: String): KotlinCreator

    /**
     * Adds function to the kotlin file.
     *
     * @param function The function to add
     * @return The current [KotlinCreator] instance
     */
    fun addFunction(function: FunctionCreator): KotlinCreator

    /**
     * Creates the kotlin file.
     *
     * @return Prints the kotlin file to the specified output
     */
    fun create(): KotlinClassCreator
}
