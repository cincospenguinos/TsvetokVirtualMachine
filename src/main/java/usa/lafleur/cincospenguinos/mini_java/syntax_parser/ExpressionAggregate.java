package usa.lafleur.cincospenguinos.mini_java.syntax_parser;

import usa.lafleur.cincospenguinos.mini_java.syntax_parser.expressions.Expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ExpressionAggregate {
    private Stack<Expression> _expressionStack;
    private ArrayList<Expression> _expressionsAsList;

    public ExpressionAggregate() {
        _expressionStack = new Stack<>();
        _expressionsAsList = new ArrayList<>();
    }

    public void replaceExpressions(int startIndex, int endIndex, Expression newExpression) {
        if (endIndex > startIndex) {
            _expressionsAsList.subList(startIndex, endIndex).clear();
        }

        _expressionsAsList.add(startIndex, newExpression);

        _expressionStack = new Stack<>();
        _expressionsAsList.forEach(e -> _expressionStack.push(e));
    }

    public void add(Expression expression) {
        _expressionStack.push(expression);
        _expressionsAsList.add(expression);
    }

    public List<Expression> getSublist(int start, int end) {
        return new ArrayList<>(_expressionsAsList.subList(start, end));
    }

    public Stack<Expression> getStack() {
        return _expressionStack;
    }

    public int size() {
        return _expressionsAsList.size();
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        _expressionsAsList.forEach(e -> builder.append(e.toString()));
        return builder.toString();
    }

    public List<Expression> getList() {
        return _expressionsAsList;
    }
}