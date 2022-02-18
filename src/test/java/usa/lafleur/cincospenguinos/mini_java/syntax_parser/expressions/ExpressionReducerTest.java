package usa.lafleur.cincospenguinos.mini_java.syntax_parser.expressions;

import org.junit.Test;
import usa.lafleur.cincospenguinos.mini_java.lexer.Lexer;
import usa.lafleur.cincospenguinos.mini_java.syntax_parser.ExpressionAggregate;

import static org.junit.Assert.*;

public class ExpressionReducerTest {
    ExpressionReducer reducer = new ExpressionReducer();

    @Test
    public void test_reducesIntoArithmeticExpression() {
        ExpressionAggregate stack = new ExpressionAggregate();
        new Lexer("1 + 1").tokenize().stream()
                .map(Expression::getForTokenItem)
                .forEach(stack::add);

        assertEquals(3, stack.size());
        reducer.reduce(stack);
        assertEquals(1, stack.size());
        assertTrue(stack.getStack().peek() instanceof ArithmeticExpression);
    }

    @Test
    public void test_reducesIntoArithmeticExpressionWithIdentifier() {
        ExpressionAggregate stack = new ExpressionAggregate();
        new Lexer("j + 1").tokenize().stream()
                .map(Expression::getForTokenItem)
                .forEach(stack::add);

        assertEquals(3, stack.size());
        reducer.reduce(stack);
        assertEquals(1, stack.size());
        assertTrue(stack.getStack().peek() instanceof ArithmeticExpression);
    }

    @Test
    public void test_reducesMultipleArithmeticExpressionsIntoOne() {
        ExpressionAggregate stack = new ExpressionAggregate();
        new Lexer("1 + 2 * 3").tokenize().stream()
                .map(Expression::getForTokenItem)
                .forEach(stack::add);

        assertEquals(5, stack.size());
        reducer.reduce(stack);
        assertEquals(1, stack.size());
    }

    @Test
    public void test_reducesAssignmentOperator() {
        ExpressionAggregate stack = new ExpressionAggregate();
        new Lexer("int variable = -1").tokenize().stream()
                .map(Expression::getForTokenItem)
                .forEach(stack::add);

        assertEquals(4, stack.size());
        reducer.reduce(stack);
        assertEquals(1, stack.size());
    }
}