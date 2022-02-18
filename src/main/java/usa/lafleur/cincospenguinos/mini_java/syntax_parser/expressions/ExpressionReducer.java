package usa.lafleur.cincospenguinos.mini_java.syntax_parser.expressions;

import usa.lafleur.cincospenguinos.mini_java.syntax_parser.ExpressionAggregate;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionReducer {
    private static final Pattern FLAT_ARITHMETIC_PATTERN = Pattern.compile("[iv][+*\\-/][iv]");
    private static final Pattern COMPOUND_ARITHMETIC_PATTERN = Pattern.compile("A[+*\\-/][iv]");
    private static final Pattern FLAT_ASSIGNMENT_PATTERN = Pattern.compile("Tv=i");

    private List<Pattern> _patterns;
    private Map<Pattern, Integer> _patternMap;

    public ExpressionReducer() {
        _patterns = new LinkedList<>();
        _patterns.add(FLAT_ARITHMETIC_PATTERN);
        _patterns.add(COMPOUND_ARITHMETIC_PATTERN);
        _patterns.add(FLAT_ASSIGNMENT_PATTERN);

        _patternMap = new HashMap<>();
        _patternMap.put(FLAT_ARITHMETIC_PATTERN, ExpressionReductionInstantiator.ARITHMETIC);
        _patternMap.put(COMPOUND_ARITHMETIC_PATTERN, ExpressionReductionInstantiator.ARITHMETIC);
        _patternMap.put(FLAT_ASSIGNMENT_PATTERN, ExpressionReductionInstantiator.ASSIGNMENT);
    }

    public void reduce(ExpressionAggregate expressionAggregate) {
        for (Pattern patternToTest : _patterns) {
            Matcher matcher = patternToTest.matcher(expressionAggregate.toString());

            if (matcher.find()) {
                int instantiationType = _patternMap.get(patternToTest);
                List<Expression> relevantExpressions = expressionAggregate.getSublist(matcher.start(), matcher.end());
                Expression newExpression = new ExpressionReductionInstantiator(relevantExpressions).getExpression(instantiationType);
                expressionAggregate.replaceExpressions(matcher.start(), matcher.end(), newExpression);
            }
        }
    }
}
