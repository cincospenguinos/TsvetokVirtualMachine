package usa.lafleur.cincospenguinos.mini_java;

import org.junit.Test;
import usa.lafleur.cincospenguinos.mini_java.lexer.*;

import java.util.List;

import static org.junit.Assert.*;

public class FirstPassErrorCheckTest {

    @Test
    public void test_handlesMismatchedBracesError() {
        List<TokenItem> tokenStream = new Lexer("{{}").tokenize();
        FirstPassErrorCheck analyzer = new FirstPassErrorCheck(tokenStream);
        List<SyntaxErrorItem> errors = analyzer.syntaxErrors();
        assertEquals(1, errors.size());
        assertEquals(SyntaxError.MISMATCHED_BRACES, errors.get(0).getReason());
    }

    @Test
    public void test_handlesMismatchedSquareBracketsError() {
        List<TokenItem> tokenStream = new Lexer("[[]").tokenize();
        FirstPassErrorCheck analyzer = new FirstPassErrorCheck(tokenStream);
        List<SyntaxErrorItem> errors = analyzer.syntaxErrors();
        assertEquals(1, errors.size());
        assertEquals(SyntaxError.MISMATCHED_BRACKETS, errors.get(0).getReason());
    }

    @Test
    public void test_handlesMismatchedParenthesis() {
        List<TokenItem> tokenStream = new Lexer("(()").tokenize();
        FirstPassErrorCheck analyzer = new FirstPassErrorCheck(tokenStream);
        List<SyntaxErrorItem> errors = analyzer.syntaxErrors();
        assertEquals(1, errors.size());
        assertEquals(SyntaxError.MISMATCHED_PARENS, errors.get(0).getReason());
    }

    @Test
    public void test_handlesCloseBeforeOpen() {
        FirstPassErrorCheck analyzer = new FirstPassErrorCheck(new Lexer(")(").tokenize());
        List<SyntaxErrorItem> errors = analyzer.syntaxErrors();
        assertEquals(1, errors.size());
        assertEquals(SyntaxError.CLOSE_PAREN_BEFORE_OPEN, errors.get(0).getReason());

        analyzer = new FirstPassErrorCheck(new Lexer("][").tokenize());
        errors = analyzer.syntaxErrors();
        assertEquals(1, errors.size());
        assertEquals(SyntaxError.CLOSE_SQUARE_BEFORE_OPEN, errors.get(0).getReason());

        analyzer = new FirstPassErrorCheck(new Lexer("}{").tokenize());
        errors = analyzer.syntaxErrors();
        assertEquals(1, errors.size());
        assertEquals(SyntaxError.CLOSE_BRACE_BEFORE_OPEN, errors.get(0).getReason());
    }
}