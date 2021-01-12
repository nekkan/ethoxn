@file:JvmName("Extensions")

package com.nekkan.grammar

import org.antlr.v4.kotlinruntime.CharStreams

inline fun input(value: String) = CharStreams.fromString(value)
