package usa.lafleur.cincospenguinos.mini_java.syntax_parser.expressions;

import usa.lafleur.cincospenguinos.mini_java.syntax_parser.ExpressionAggregate;
import usa.lafleur.cincospenguinos.mini_java.syntax_parser.UnknownExpression;

import java.util.List;
import java.util.regex.Pattern;

public class ExpressionReducer {
    private static final Pattern FLAT_ARITHMETIC_PATTERN = Pattern.compile("^([iv])[+*\\-/]([iv])$");

    public void reduce(ExpressionAggregate expressionAggregate) {
        if (FLAT_ARITHMETIC_PATTERN.matcher(expressionAggregate.toString()).find()) {
            int regionStart = FLAT_ARITHMETIC_PATTERN.matcher(expressionAggregate.toString()).regionStart();
            int regionEnd = FLAT_ARITHMETIC_PATTERN.matcher(expressionAggregate.toString()).regionEnd();

            List<Expression> relevantExpressions = expressionAggregate.getSublist(regionStart, regionEnd);

            Expression newExpression = new ArithmeticExpression(relevantExpressions.get(0),
                    (UnknownExpression) relevantExpressions.get(1),
                    relevantExpressions.get(2)
            );

            expressionAggregate.replaceExpressions(regionStart, regionEnd, newExpression);
        }
    }
}
