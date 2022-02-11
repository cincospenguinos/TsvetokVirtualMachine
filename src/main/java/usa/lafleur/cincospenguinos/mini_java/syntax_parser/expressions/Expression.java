package usa.lafleur.cincospenguinos.mini_java.syntax_parser.expressions;

import usa.lafleur.cincospenguinos.mini_java.lexer.TokenItem;
import usa.lafleur.cincospenguinos.mini_java.syntax_parser.UnknownExpression;

public abstract class Expression {
    public static Expression getForTokenItem(TokenItem item) {
        switch(item.getToken()) {
            case INTEGER_LITERAL:
            case IDENTIFIER:
            case TRUE:
            case FALSE:
            case THIS_LITERAL:
            case NULL:
                return new PrimaryExpression(item);
            default:
                return new UnknownExpression(item);
        }
    }
}
