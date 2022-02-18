package usa.lafleur.cincospenguinos.mini_java.syntax_parser.expressions;

import usa.lafleur.cincospenguinos.mini_java.lexer.TokenItem;

public class ArithmeticExpression extends Expression {
    private Expression _left, _right;
    private TokenItem _operationItem;

    public ArithmeticExpression(Expression left, UnknownExpression operation, Expression right) {
        _left = left;
        _operationItem = operation.getItem();
        _right = right;
    }

    public String toString() {
        return "A";
    }
}
