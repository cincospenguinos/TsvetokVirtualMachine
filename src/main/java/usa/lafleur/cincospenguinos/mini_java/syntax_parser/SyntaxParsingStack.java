package usa.lafleur.cincospenguinos.mini_java.syntax_parser;

import usa.lafleur.cincospenguinos.mini_java.lexer.Lexer;
import usa.lafleur.cincospenguinos.mini_java.lexer.TokenItem;
import usa.lafleur.cincospenguinos.mini_java.syntax_parser.expressions.Expression;
import usa.lafleur.cincospenguinos.mini_java.syntax_parser.expressions.ExpressionReducer;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class SyntaxParsingStack {
    private static final Expression[] EXPRESSION_TYPE_ARRAY = new Expression[]{};

    private Stack<Expression> expressions;
    private ExpressionReducer reducer;

    public SyntaxParsingStack() {
        expressions = new Stack<>();
        reducer = new ExpressionReducer();
    }

    public void pushTokenAndReduce(TokenItem item) {
        Expression expression = Expression.getForTokenItem(item);
        expressions.push(expression);
        expressions = reducer.reduce(expressions);
    }

    public List<Expression> toExpressionList() {
        return Arrays.asList(expressions.toArray(EXPRESSION_TYPE_ARRAY));
    }
}
