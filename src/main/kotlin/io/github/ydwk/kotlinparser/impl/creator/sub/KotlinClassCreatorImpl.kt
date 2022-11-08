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
    private val functions: List<FunctionCreator>,
    private val directory: String
) : KotlinClassCreator {
    override val modifiy: KotlinModifier
        get() = KotlinModifierImpl(classAsFile())

    override fun print(): StringBuilder {
        val builder = StringBuilder()
        builder.appendLine("package $packageName")
        builder.appendLine()

        if (imports.isNotEmpty()) {
            imports.forEach { builder.appendLine("import $it") }
            builder.appendLine()
        }

        when (type) {
            KotlinType.CLASS -> builder.append("class $name")
            KotlinType.OBJECT -> builder.append("object $name")
            KotlinType.INTERFACE -> builder.append("interface $name")
            KotlinType.ENUM -> builder.append("enum class $name")
            KotlinType.ANNOTATION -> builder.append("annotation class $name")
        }

        if (functions.isNotEmpty()) {
            builder.appendLine(" {")
            functions.forEach { builder.append(it.print()) }
            builder.appendLine("}")
        } else {
            builder.appendLine(" {" + "}")
        }

        if (directory != "") {
            val file = File("$directory/$name.kt")
            if (!file.exists()) {
                file.createNewFile()
            } else {
                file.delete()
                file.createNewFile()
            }
            file.writeText(builder.toString())
        }

        return builder
    }

    private fun classAsFile(): File {
        val file = File("src/main/kotlin/$packageName")
        file.createNewFile()
        file.writeText(print().toString())
        return file
    }
}
