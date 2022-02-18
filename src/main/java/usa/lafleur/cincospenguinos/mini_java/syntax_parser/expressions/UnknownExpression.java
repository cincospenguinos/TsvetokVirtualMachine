package usa.lafleur.cincospenguinos.mini_java.syntax_parser.expressions;

import usa.lafleur.cincospenguinos.mini_java.lexer.TokenItem;

public class UnknownExpression extends Expression {
    private TokenItem _item;

    public UnknownExpression(TokenItem item) {
        _item = item;
    }

    @Override
    public String toString() {
        return _item.getValue();
    }

    public TokenItem getItem() {
        return _item;
    }
}
