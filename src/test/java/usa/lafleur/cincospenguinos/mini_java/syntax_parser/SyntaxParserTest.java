package usa.lafleur.cincospenguinos.mini_java.syntax_parser;

import org.junit.Ignore;
import org.junit.Test;
import usa.lafleur.cincospenguinos.mini_java.lexer.Lexer;
import usa.lafleur.cincospenguinos.mini_java.syntax_parser.expressions.ArithmeticExpression;
import usa.lafleur.cincospenguinos.mini_java.syntax_parser.expressions.Expression;
import usa.lafleur.cincospenguinos.mini_java.syntax_parser.expressions.PrimaryExpression;

import java.util.List;

import static org.junit.Assert.*;

public class SyntaxParserTest {
    @Test
    public void test_syntaxParserHandlesIntegerLiteralPrimaryExpression() {
        SyntaxParser parser = new SyntaxParser(new Lexer("4").tokenize());
        List<Expression> expressionList = parser.parse();

        assertNotNull(expressionList);
        assertEquals(1, expressionList.size());
        assertTrue(expressionList.get(0) instanceof PrimaryExpression);
    }

    @Test
    public void test_syntaxParserHandlesArithmeticExpression() {
        SyntaxParser parser = new SyntaxParser(new Lexer("4 + 3").tokenize());
        List<Expression> expressionList = parser.parse();

        assertNotNull(expressionList);
        assertEquals(1, expressionList.size());
        assertTrue(expressionList.get(0) instanceof ArithmeticExpression);
    }
}