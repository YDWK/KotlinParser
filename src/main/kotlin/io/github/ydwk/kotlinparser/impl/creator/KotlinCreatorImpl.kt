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
package io.github.ydwk.kotlinparser.impl.creator

import io.github.ydwk.kotlinparser.creator.KotlinCreator
import io.github.ydwk.kotlinparser.creator.sub.FunctionCreator
import io.github.ydwk.kotlinparser.creator.sub.KotlinClassCreator
import io.github.ydwk.kotlinparser.creator.sub.KotlinType
import io.github.ydwk.kotlinparser.impl.creator.sub.KotlinClassCreatorImpl

class KotlinCreatorImpl(private val packageName: String) : KotlinCreator {
    private val imports = mutableSetOf<String>()
    private val functions = mutableListOf<FunctionCreator>()
    private var type: KotlinType = KotlinType.CLASS
    private var name: String = ""
    private var directory: String = ""

    override fun addImport(import: String): KotlinCreator {
        imports.add(import)
        return this
    }

    override fun setType(type: KotlinType): KotlinCreator {
        this.type = type
        return this
    }

    override fun setName(name: String): KotlinCreator {
        this.name = name
        return this
    }

    override fun addFunction(function: FunctionCreator): KotlinCreator {
        functions.add(function)
        return this
    }

    override fun setDirectory(directory: String): KotlinCreator {
        this.directory = directory
        return this
    }

    override fun create(): KotlinClassCreator {
        return KotlinClassCreatorImpl(packageName, imports, type, name, functions, directory)
    }
}
