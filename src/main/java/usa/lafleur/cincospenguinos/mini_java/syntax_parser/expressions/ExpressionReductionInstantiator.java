package usa.lafleur.cincospenguinos.mini_java.syntax_parser.expressions;

import java.util.List;

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
