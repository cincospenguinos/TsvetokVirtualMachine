package usa.lafleur.cincospenguinos.mini_java.syntax_parser;

import usa.lafleur.cincospenguinos.mini_java.lexer.TokenItem;

public class UnknownExpression extends Expression {
    private TokenItem _item;

    public UnknownExpression(TokenItem item) {
        _item = item;
    }

    public TokenItem getItem() {
        return _item;
    }
}
