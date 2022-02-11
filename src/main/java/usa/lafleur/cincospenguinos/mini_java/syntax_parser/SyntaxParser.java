package usa.lafleur.cincospenguinos.mini_java.syntax_parser;

import usa.lafleur.cincospenguinos.mini_java.lexer.Token;
import usa.lafleur.cincospenguinos.mini_java.lexer.TokenItem;

import java.util.*;

public class SyntaxParser {
    private Queue<TokenItem> _tokenItems;

    SyntaxParser(List<TokenItem> programTokens) {
        _tokenItems = new LinkedList<>(programTokens);
    }

    public List<Expression> parse() {
        SyntaxParsingStack stack = new SyntaxParsingStack();

        while (!_tokenItems.isEmpty()) {
            TokenItem item = _tokenItems.remove();
            stack.pushTokenAndReduce(item);
        }

        return stack.toExpressionList();
    }
}
