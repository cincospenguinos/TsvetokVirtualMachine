package usa.lafleur.cincospenguinos.mini_java.syntax_parser;

import usa.lafleur.cincospenguinos.mini_java.lexer.TokenItem;

public class PrimaryExpression extends Expression {
    private TokenItem _item;

    public PrimaryExpression(TokenItem item) {
        _item = item;
    }
}
