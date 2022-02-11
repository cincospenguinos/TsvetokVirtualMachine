package usa.lafleur.cincospenguinos.mini_java.syntax_parser.expressions;

import usa.lafleur.cincospenguinos.mini_java.lexer.TokenItem;

public class PrimaryExpression extends Expression {
    private TokenItem _item;

    public PrimaryExpression(TokenItem item) {
        _item = item;
    }

    public String toString() {
        switch(_item.getToken()) {
            case INTEGER_LITERAL:
                return "i";
            case IDENTIFIER:
                return "v";
        }

        throw new RuntimeException("No string for " + _item);
    }
}
