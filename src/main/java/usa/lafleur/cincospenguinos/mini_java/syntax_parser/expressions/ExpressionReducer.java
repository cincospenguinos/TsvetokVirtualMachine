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

    class ExpressionReductionInstantiator {
        static final int ARITHMETIC = 0;
        static final int ASSIGNMENT = 1;

        private List<Expression> _expressions;

        ExpressionReductionInstantiator(List<Expression> expressions) {
            _expressions = expressions;
        }

        Expression getExpression(int expressionType) {
            switch(expressionType) {
                case ARITHMETIC:
                    return new ArithmeticExpression(_expressions.get(0),
                            (UnknownExpression) _expressions.get(1), _expressions.get(2));
                case ASSIGNMENT:
                    return new AssignmentExpression((TypeExpression) _expressions.get(0),
                            _expressions.get(1), _expressions.get(3));
                default:
                    throw new RuntimeException("nul etree ekspreseeoun pour " + expressionType);
            }
        }
    }

    public void reduce(ExpressionAggregate expressionAggregate) {
        for (Pattern p : _patterns) {
            Matcher matcher = p.matcher(expressionAggregate.toString());

            if (matcher.find()) {
                List<Expression> relevantExpressions = expressionAggregate.getSublist(matcher.start(), matcher.end());
                Expression newExpression = null;
                ExpressionReductionInstantiator instantiator = new ExpressionReductionInstantiator(relevantExpressions);

                if (p.equals(FLAT_ARITHMETIC_PATTERN) || p.equals(COMPOUND_ARITHMETIC_PATTERN)) {
                    newExpression = instantiator.getExpression(ExpressionReductionInstantiator.ARITHMETIC);
                } else if (p.equals(FLAT_ASSIGNMENT_PATTERN)) {
                    newExpression = instantiator.getExpression(ExpressionReductionInstantiator.ASSIGNMENT);
                }

                if (newExpression != null) {
                    expressionAggregate.replaceExpressions(matcher.start(), matcher.end(), newExpression);
                }
            }
        }
    }
}
