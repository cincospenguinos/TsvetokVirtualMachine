package usa.lafleur.cincospenguinos.mini_java.syntax_parser;

import usa.lafleur.cincospenguinos.mini_java.syntax_parser.expressions.Expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ExpressionStack {
    private Stack<Expression> _expressionStack;
    private ArrayList<Expression> _expressionsAsList;

    public ExpressionStack(Stack<Expression> expressionStack) {
        _expressionStack = expressionStack;
        _expressionsAsList = new ArrayList<>(expressionStack);
    }

    public void replaceExpressions(int startIndex, int endIndex, Expression newExpression) {
        if (endIndex > startIndex) {
            _expressionsAsList.subList(startIndex, endIndex).clear();
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