package io.github.ydwk.kotlinparser

import io.github.ydwk.kotlinparser.creator.KotlinCreator

interface IKotlinParser {

    val createKotlin : KotlinCreator

    val parseKotlin : KotlinParser
}