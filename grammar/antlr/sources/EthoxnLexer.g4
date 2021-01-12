lexer grammar EthoxnLexer;

WHITESPACE: ([\t] | NEW_LINE)+ -> channel(HIDDEN);
NEW_LINE: ([\r\n] | [\n])+;

SEMICOLON: ';'+;
COLON: ':';
COMMA: ',';
DOT: '.';

ARROW_RIGHT: '->';
ARROW_LEFT: '<-';

LEFT_CURLY: '{';
RIGHT_CURLY: '}';

LEFT_BRACKET: '[';
RIGHT_BRACKET: ']';

LEFT_PARENTHESIS: '(';
RIGHT_PARENTHESIS: ')';

PLUS: '+';
MINUS: '-';
DIVIDE: '/';
REMAINDER: '%';
ASTERISK: '*';

NEGATION: '!';
ASSIGN: '=';

GREATER: '>';
LESS: '<';
EQUALS: '==';
NOT_EQUALS: '!=';
GREATER_EQUALS: '>=';
LESS_EQUAL: '<=';
OR: '||';

CLASS: 'class';
RETURN: 'return';
FUNCTION: 'func';
LET: 'let';
IF: 'if';
ELSE: 'else';
MUTABLE: 'mut';
TRUE: 'true';
FALSE: 'false';
PACKAGE: 'pkg';
USE: 'use';
EXTERNAL: 'extern';
IDENTIFIER: [a-zA-Z_][a-zA-Z0-9_]+;

INTEGER: [0-9]+;
DECIMAL: INTEGER'.'INTEGER;

STRING : '"' (~["\r\n\\]
    | '\\' ~[\r\n])* '"'
    | '\'' (~["\r\n\\]
    | '\\' ~[\r\n])* '\''
    ;