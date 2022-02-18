package usa.lafleur.cincospenguinos.mini_java.syntax_parser.expressions;

import usa.lafleur.cincospenguinos.mini_java.syntax_parser.ExpressionAggregate;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionReducer {
    private static final Pattern FLAT_ARITHMETIC_PATTERN = Pattern.compile("[iv][+*\\-/][iv]");
    private static final Pattern COMPOUND_ARITHMETIC_PATTERN = Pattern.compile("A[+*\\-/][iv]");
    private static final Pattern FLAT_ASSIGNMENT_PATTERN = Pattern.compile("Tv=i");

    private List<Pattern> _patterns;

    public ExpressionReducer() {
        _patterns = new LinkedList<>();
        _patterns.add(FLAT_ARITHMETIC_PATTERN);
        _patterns.add(COMPOUND_ARITHMETIC_PATTERN);
        _patterns.add(FLAT_ASSIGNMENT_PATTERN);
    }

    public void reduce(ExpressionAggregate expressionAggregate) {
        for (Pattern p : _patterns) {
            Matcher matcher = p.matcher(expressionAggregate.toString());

            if (matcher.find()) {
                List<Expression> relevantExpressions = expressionAggregate.getSublist(matcher.start(), matcher.end());
                Expression newExpression = null;

                if (p.equals(FLAT_ARITHMETIC_PATTERN) || p.equals(COMPOUND_ARITHMETIC_PATTERN)) {
                    newExpression = new ArithmeticExpression(relevantExpressions.get(0),
                            (UnknownExpression) relevantExpressions.get(1),
                            relevantExpressions.get(2)
                    );
                } else if (p.equals(FLAT_ASSIGNMENT_PATTERN)) {
                    newExpression = new AssignmentExpression((TypeExpression) relevantExpressions.get(0),
                            relevantExpressions.get(1), relevantExpressions.get(3));
                }

                if (newExpression != null) {
                    expressionAggregate.replaceExpressions(matcher.start(), matcher.end(), newExpression);
                }
            }
        }
    }
}
