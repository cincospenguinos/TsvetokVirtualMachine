package usa.lafleur.cincospenguinos.mini_java.syntax_parser;

import usa.lafleur.cincospenguinos.mini_java.lexer.TokenItem;
import usa.lafleur.cincospenguinos.mini_java.syntax_parser.expressions.Expression;
import usa.lafleur.cincospenguinos.mini_java.syntax_parser.expressions.ExpressionReducer;

import java.util.Arrays;
import java.util.List;

class SyntaxParsingStack {
    private static final Expression[] EXPRESSION_TYPE_ARRAY = new Expression[]{};

    private ExpressionStack expressions;
    private ExpressionReducer reducer;

    public SyntaxParsingStack() {
        expressions = new ExpressionStack();
        reducer = new ExpressionReducer();
    }

    public void pushTokenAndReduce(TokenItem item) {
        Expression expression = Expression.getForTokenItem(item);
        expressions.add(expression);
        reducer.reduce(expressions);
    }

    public List<Expression> toExpressionList() {
        return expressions.getList();
    }
}
