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
import io.github.ydwk.kotlinparser.creator.sub.KotlinType
import io.github.ydwk.kotlinparser.modifiy.KotlinModifier

class KotlinCreatorImpl(private val packageName: String) : KotlinCreator {
    private val imports = mutableSetOf<String>()
    private val functions = mutableListOf<FunctionCreator>()
    private var type: KotlinType = KotlinType.CLASS
    private var name: String = ""

    override fun addImport(import: String): KotlinCreator {
        imports.add(import)
        return this
    }

    override fun type(type: KotlinType): KotlinCreator {
        this.type = type
        return this
    }

    override fun name(name: String): KotlinCreator {
        this.name = name
        return this
    }

    override fun addFunction(function: FunctionCreator): KotlinCreator {
        functions.add(function)
        return this
    }

    override fun create(): KotlinModifier {
        val builder = StringBuilder()
        builder.append("package $packageName")
        builder.append("\r")

        imports.forEach { k -> builder.append("import $k") }

        builder.append("\r")

        builder.append("$type $name {")
        builder.append("\r")

        return TODO("Not yet implemented")
    }
}
