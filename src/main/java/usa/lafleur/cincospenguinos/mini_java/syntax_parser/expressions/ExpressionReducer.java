package usa.lafleur.cincospenguinos.mini_java.syntax_parser.expressions;

import usa.lafleur.cincospenguinos.mini_java.syntax_parser.ExpressionAggregate;
import usa.lafleur.cincospenguinos.mini_java.syntax_parser.UnknownExpression;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionReducer {
    private static final Pattern FLAT_ARITHMETIC_PATTERN = Pattern.compile("[iv][+*\\-/][iv]");
    private static final Pattern COMPOUND_ARITHMETIC_PATTERN = Pattern.compile("A[+*\\-/][iv]");

    public void reduce(ExpressionAggregate expressionAggregate) {
        Matcher matcher = FLAT_ARITHMETIC_PATTERN.matcher(expressionAggregate.toString());
        if (matcher.find()) {
            List<Expression> relevantExpressions = expressionAggregate.getSublist(matcher.start(), matcher.end());

            Expression newExpression = new ArithmeticExpression(relevantExpressions.get(0),
                    (UnknownExpression) relevantExpressions.get(1),
                    relevantExpressions.get(2)
            );

            expressionAggregate.replaceExpressions(matcher.start(), matcher.end(), newExpression);
        }

        matcher = COMPOUND_ARITHMETIC_PATTERN.matcher(expressionAggregate.toString());
        if (matcher.find()) {
            List<Expression> relevantExpressions = expressionAggregate.getSublist(matcher.start(), matcher.end());

            Expression newExpression = new ArithmeticExpression(relevantExpressions.get(0),
                    (UnknownExpression) relevantExpressions.get(1),
                    relevantExpressions.get(2)
            );

            expressionAggregate.replaceExpressions(matcher.start(), matcher.end(), newExpression);
        }
    }
}
