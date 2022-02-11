package usa.lafleur.cincospenguinos.mini_java.syntax_parser;

import usa.lafleur.cincospenguinos.mini_java.lexer.TokenItem;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class SyntaxParsingStack {
    private static final Expression[] EXPRESSION_TYPE_ARRAY = new Expression[]{};

    private Stack<Expression> expressions;

    public SyntaxParsingStack() {
        expressions = new Stack<>();
    }

    public void pushTokenAndReduce(TokenItem item) {
        Expression expression = Expression.getForTokenItem(item);

        if (!(expression instanceof UnknownExpression)) {
            expressions.push(expression);
        }
    }

    public List<Expression> toExpressionList() {
        return Arrays.asList(expressions.toArray(EXPRESSION_TYPE_ARRAY));
    }
}
