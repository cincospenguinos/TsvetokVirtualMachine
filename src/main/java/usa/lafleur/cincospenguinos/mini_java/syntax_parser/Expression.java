package usa.lafleur.cincospenguinos.mini_java.syntax_parser;

import usa.lafleur.cincospenguinos.mini_java.lexer.TokenItem;

public abstract class Expression {
    public static Expression getForTokenItem(TokenItem item) {
        switch(item.getToken()) {
            case INTEGER_LITERAL:
                return new PrimaryExpression(item);
            default:
                return new UnknownExpression(item);
        }
    }
}
