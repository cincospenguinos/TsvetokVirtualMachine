package usa.lafleur.cincospenguinos.mini_java.syntax_parser.expressions;

import usa.lafleur.cincospenguinos.mini_java.syntax_parser.ExpressionStack;
import usa.lafleur.cincospenguinos.mini_java.syntax_parser.UnknownExpression;

import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

public class ExpressionReducer {
    private static final Pattern FLAT_ARITHMETIC_PATTERN = Pattern.compile("i[+*\\-/]i");
    private static final Pattern COMPOUND_ARITHMETIC_PATTERN = Pattern.compile("A[+*\\-/]i");

    public void reduce(ExpressionStack expressionStack) {
        if (FLAT_ARITHMETIC_PATTERN.matcher(expressionStack.toString()).find()) {
            int regionStart = FLAT_ARITHMETIC_PATTERN.matcher(expressionStack.toString()).regionStart();
            int regionEnd = FLAT_ARITHMETIC_PATTERN.matcher(expressionStack.toString()).regionEnd();

            List<Expression> relevantExpressions = expressionStack.getSublist(regionStart, regionEnd);

            Expression newExpression = new ArithmeticExpression(relevantExpressions.get(0),
                    (UnknownExpression) relevantExpressions.get(1),
                    relevantExpressions.get(2)
            );

            expressionStack.replaceExpressions(regionStart, regionEnd, newExpression);
        }
    }
}
