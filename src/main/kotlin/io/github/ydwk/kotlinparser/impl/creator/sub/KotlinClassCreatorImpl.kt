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
package io.github.ydwk.kotlinparser.impl.creator.sub

import io.github.ydwk.kotlinparser.creator.sub.FunctionCreator
import io.github.ydwk.kotlinparser.creator.sub.KotlinClassCreator
import io.github.ydwk.kotlinparser.creator.sub.KotlinType
import io.github.ydwk.kotlinparser.impl.modifiy.KotlinModifierImpl
import io.github.ydwk.kotlinparser.modifiy.KotlinModifier
import java.io.File

class KotlinClassCreatorImpl(
    private val packageName: String,
    private val imports: Set<String>,
    private val type: KotlinType,
    private val name: String,
    private val functions: List<FunctionCreator>
) : KotlinClassCreator {
    override val modifiy: KotlinModifier
        get() = KotlinModifierImpl(classAsFile())

    override fun print(): StringBuilder {
        val builder = StringBuilder()
        builder.appendLine("package $packageName")
        builder.appendLine()

        imports.forEach {
            builder.appendLine("import $it")
        }

        builder.appendLine()
        builder.appendLine("class $name {")
        functions.forEach {
            builder.appendLine(it.print())
        }
        builder.appendLine("}")
        return builder
    }

    private fun classAsFile() : File {
        val file = File("$packageName/$name.kt")
        file.createNewFile()
        file.writeText(print().toString())
        return file
    }
}
