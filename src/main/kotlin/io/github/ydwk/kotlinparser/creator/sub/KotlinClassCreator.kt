package io.github.ydwk.kotlinparser.creator.sub

import io.github.ydwk.kotlinparser.modifiy.KotlinModifier

interface KotlinClassCreator {

    /**
     * Edit the new class.
     *
     * @return The current [KotlinClassCreator] instance
     */
    val modifiy : KotlinModifier

    /**
     * Creates the kotlin class.
     *
     * @return The kotlin class
     */
    fun print() : StringBuilder
}