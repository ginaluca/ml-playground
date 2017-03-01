# works with BSD sed
/\/\*.*\*\//d
/\/\*/,/\*\// d
s/[ ]*\/\/.*$//
s/"[^"]*"/ %STRING_LITERAL% /g
s/!=/ %NOT_EQUAL% /g
s/<=/ %LESS_EQUAL% /g
s/</ %LESS% /g
s/>=/ %GREATER_EQUAL% /g
s/>/ %GREATER% /g
s/==/ %EQUAL% /g
s/=/ %ASSIGNMENT% /g
s/;/ %SEMICOLON% /g
s/,/ %COMMA% /g
s/[?]/ %QUESTION% /g
s/[:]/ %COLON% /g
s/[.]/ %DOT% /g
s/\[/ %OPEN_SQUARE% /g
s/\]/ %CLOSE_SQUARE% /g
s/\(/ %OPEN_PAREN% /g
s/\)/ %CLOSE_PAREN% /g
s/\{/ %OPEN_BRACE% /g
s/\}/ %CLOSE_BRACE% /g
s/[^[:alnum:]%_]/ /g
s/[ ]+/ /g
s/^ //g
