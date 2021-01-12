@file:Suppress("nothing_to_inline")

package com.nekkan.grammar

import com.nekkan.grammar.generated.EthoxnLexer
import com.nekkan.grammar.generated.EthoxnParser
import org.antlr.v4.kotlinruntime.CharStream
import org.antlr.v4.kotlinruntime.CharStreams
import org.antlr.v4.kotlinruntime.CommonTokenStream

inline fun input(value: String) = CharStreams.fromString(value)

inline fun lexer(input: CharStream) = EthoxnLexer(input)

inline fun parser(lexer: EthoxnLexer) = EthoxnParser(CommonTokenStream(lexer))