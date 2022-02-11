package usa.lafleur.cincospenguinos.mini_java.syntax_parser.expressions;

import org.junit.Test;
import usa.lafleur.cincospenguinos.mini_java.lexer.Lexer;

import java.util.Stack;

import static org.junit.Assert.*;

public class ExpressionReducerTest {
    ExpressionReducer reducer = new ExpressionReducer();

    @Test
    public void test_ReducesIntoArithmeticExpression() {
        Stack<Expression> expressions = new Stack<>();
        new Lexer("1 + 1").tokenize().stream()
                .map(Expression::getForTokenItem)
                .forEach(expressions::push);

        assertEquals(3, expressions.size());
        expressions = reducer.reduce(expressions);
        assertEquals(1, expressions.size());
        assertTrue(expressions.get(0) instanceof ArithmeticExpression);
    }
}