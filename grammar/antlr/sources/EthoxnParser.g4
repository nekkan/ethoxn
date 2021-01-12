parser grammar EthoxnParser;

options {
    tokenVocab=EthoxnLexer;
}

// expression
expr: INTEGER
    | DECIMAL
    | IDENTIFIER
    | stringExpr
    | booleanExpr
    ;

stringExpr: STRING;

booleanExpr: TRUE
            | FALSE
            ;

// file
program: usages? decl* EOF;

pkg: PACKAGE value=IDENTIFIER;

usages: usageDirective*;

usageDirective: USE module=STRING SEMICOLON;

// type definitions
nameType: name=IDENTIFIER;

typeDefinition: nameType
            | functionType
            | arrayType
            ;

arrayType: LEFT_BRACKET type=typeDefinition RIGHT_BRACKET;

parameter: name=nameType COLON type=typeDefinition;

variableType: LET MUTABLE? WHITESPACE name=nameType (COLON type=nameType)? ASSIGN value=expr;

functionType: name=nameType LEFT_PARENTHESIS (parameter (COMMA parameter)*)? RIGHT_PARENTHESIS COLON returnType=typeDefinition;

externalFunctionType: EXTERNAL functionType;

// declarations
classDecl: CLASS name=nameType LEFT_CURLY RIGHT_CURLY;

functionDecl: (functionType | externalFunctionType) SEMICOLON;

variableDecl: variableType SEMICOLON;

decl: classDecl
    | functionDecl
    | variableDecl
    ;

// statements
returnStmt: RETURN value=expr? SEMICOLON;

stmt: decl
    | returnStmt WHITESPACE*;

