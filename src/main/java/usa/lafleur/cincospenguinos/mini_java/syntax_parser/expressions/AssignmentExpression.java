package usa.lafleur.cincospenguinos.mini_java.syntax_parser.expressions;

import usa.lafleur.cincospenguinos.mini_java.lexer.TokenItem;

public class AssignmentExpression extends Expression {
    private Expression _type;
    private Expression _identifier;
    private Expression _value;

    public AssignmentExpression(TypeExpression type, Expression identifier, Expression value) {
        _type = type;
        _identifier = identifier;
        _value = value;
    }

    public String toString() {
        return "S";
    }
}
