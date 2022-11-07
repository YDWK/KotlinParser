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
package io.github.ydwk.kotlinparser.creator.sub

class FunctionCreator {
    /**
     * Gets the parameters of the function.
     *
     * @return The parameters of the function
     */
    private val parameters: List<Pair<String, Any>> = emptyList()

    /**
     * Adds a function parameter.
     *
     * @param name The name of the parameter
     * @param type The type of the parameter
     * @return The current [FunctionCreator] instance
     */
    fun addParameter(name: String, type: Any): FunctionCreator {
        parameters.plus(Pair(name, type))
        return this
    }
}
