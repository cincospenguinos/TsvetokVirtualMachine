package usa.lafleur.cincospenguinos.mini_java.syntax_parser.expressions;

import usa.lafleur.cincospenguinos.mini_java.syntax_parser.UnknownExpression;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

public class ExpressionReducer {
    private static final Pattern FLAT_ARITHMETIC_PATTERN = Pattern.compile("i[+*\\-/]i");
    private static final Pattern COMPOUND_ARITHMETIC_PATTERN = Pattern.compile("A[+*\\-/]i");

    class ExpressionStack {
        private Stack<Expression> _expressionStack;
        private ArrayList<Expression> _expressionsAsList;

        ExpressionStack(Stack<Expression> expressionStack) {
            _expressionStack = expressionStack;
            _expressionsAsList = new ArrayList<>(expressionStack);
        }

        public void replaceExpressions(int startIndex, int endIndex, Expression newExpression) {
            for (int i = endIndex - 1; i >= startIndex; i--) {
                _expressionsAsList.remove(i);
            }

            _expressionsAsList.add(startIndex, newExpression);

            _expressionStack = new Stack<>();
            _expressionsAsList.forEach(e -> _expressionStack.push(e));
        }

        public List<Expression> getSublist(int start, int end) {
            return new ArrayList<>(_expressionsAsList.subList(start, end));
        }

        public Stack<Expression> getStack() {
            return _expressionStack;
        }

        public String toString() {
            StringBuilder builder = new StringBuilder();
            _expressionsAsList.forEach(e -> builder.append(e.toString()));
            return builder.toString();
        }
    }

    public Stack<Expression> reduce(Stack<Expression> expressions) {
        ExpressionStack stack = new ExpressionStack(expressions);

        if (FLAT_ARITHMETIC_PATTERN.matcher(stack.toString()).find()) {
            int regionStart = FLAT_ARITHMETIC_PATTERN.matcher(stack.toString()).regionStart();
            int regionEnd = FLAT_ARITHMETIC_PATTERN.matcher(stack.toString()).regionEnd();

            List<Expression> relevantExpressions = stack.getSublist(regionStart, regionEnd);

            Expression newExpression = new ArithmeticExpression(relevantExpressions.get(0),
                    (UnknownExpression) relevantExpressions.get(1),
                    relevantExpressions.get(2)
            );

            stack.replaceExpressions(regionStart, regionEnd, newExpression);
            expressions = stack.getStack();
        }

        return expressions;
    }
}
