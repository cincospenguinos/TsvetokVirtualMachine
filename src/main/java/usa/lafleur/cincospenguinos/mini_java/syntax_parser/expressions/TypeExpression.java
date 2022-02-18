package usa.lafleur.cincospenguinos.mini_java.syntax_parser.expressions;

import usa.lafleur.cincospenguinos.mini_java.lexer.TokenItem;

public class TypeExpression extends Expression {
    TokenItem _item;

    public TypeExpression(TokenItem typeItem) {
        _item = typeItem;
    }

    public String toString() {
        return "T";
    }
}
