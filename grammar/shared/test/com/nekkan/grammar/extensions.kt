@file:JvmName("Extensions")

package com.nekkan.grammar

import org.antlr.v4.kotlinruntime.CharStreams
import kotlin.jvm.JvmName

inline fun input(value: String) = CharStreams.fromString(value)
