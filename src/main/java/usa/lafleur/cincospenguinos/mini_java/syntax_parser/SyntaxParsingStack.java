package usa.lafleur.cincospenguinos.mini_java.syntax_parser;

import usa.lafleur.cincospenguinos.mini_java.lexer.TokenItem;
import usa.lafleur.cincospenguinos.mini_java.syntax_parser.expressions.Expression;
import usa.lafleur.cincospenguinos.mini_java.syntax_parser.expressions.ExpressionReducer;

import java.util.List;

class SyntaxParsingStack {
    private ExpressionAggregate expressions;
    private ExpressionReducer reducer;

    public SyntaxParsingStack() {
        expressions = new ExpressionAggregate();
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
