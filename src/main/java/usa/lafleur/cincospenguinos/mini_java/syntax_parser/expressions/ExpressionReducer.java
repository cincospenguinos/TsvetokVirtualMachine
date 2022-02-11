package usa.lafleur.cincospenguinos.mini_java.syntax_parser.expressions;

import java.util.Stack;

public class ExpressionReducer {
    private static final String ARITHMETIC_EXPRESSION_PATTERN = "^I_LIT[+*-/]I_LIT$";

    public Stack<Expression> reduce(Stack<Expression> expressions) {
        if (expressions.size() == 3) {
            StringBuilder builder = new StringBuilder();
            expressions.forEach(e -> builder.append(e.toString()));

            if (builder.toString().matches(ARITHMETIC_EXPRESSION_PATTERN)) {
                Expression[] arithmeticExpressionArray = new Expression[] {
                        expressions.pop(),
                        expressions.pop(),
                        expressions.pop()
                };

                expressions.push(new ArithmeticExpression(
                        arithmeticExpressionArray[0],
                        arithmeticExpressionArray[1],
                        arithmeticExpressionArray[2]
                ));
            }
        }

        return expressions;
    }
}
