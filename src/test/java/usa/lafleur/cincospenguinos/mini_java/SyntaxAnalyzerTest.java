package usa.lafleur.cincospenguinos.mini_java;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SyntaxAnalyzerTest {

    @Test
    public void test_handlesMismatchedBracesError() {
        List<TokenItem> tokenStream = new Lexer("{{}").tokenize();
        SyntaxAnalyzer analyzer = new SyntaxAnalyzer(tokenStream);
        List<SyntaxErrorItem> errors = analyzer.syntaxErrors();
        assertEquals(1, errors.size());
        assertEquals(SyntaxError.MISMATCHED_BRACES, errors.get(0).getReason());
    }

    @Test
    public void test_handlesMismatchedSquareBracketsError() {
        List<TokenItem> tokenStream = new Lexer("[[]").tokenize();
        SyntaxAnalyzer analyzer = new SyntaxAnalyzer(tokenStream);
        List<SyntaxErrorItem> errors = analyzer.syntaxErrors();
        assertEquals(1, errors.size());
        assertEquals(SyntaxError.MISMATCHED_BRACKETS, errors.get(0).getReason());
    }

    @Test
    public void test_handlesMismatchedParenthesis() {
        List<TokenItem> tokenStream = new Lexer("(()").tokenize();
        SyntaxAnalyzer analyzer = new SyntaxAnalyzer(tokenStream);
        List<SyntaxErrorItem> errors = analyzer.syntaxErrors();
        assertEquals(1, errors.size());
        assertEquals(SyntaxError.MISMATCHED_PARENS, errors.get(0).getReason());
    }

    @Test
    public void test_handlesCloseBeforeOpen() {
        SyntaxAnalyzer analyzer = new SyntaxAnalyzer(new Lexer(")(").tokenize());
        List<SyntaxErrorItem> errors = analyzer.syntaxErrors();
        assertEquals(1, errors.size());
        assertEquals(SyntaxError.CLOSE_PAREN_BEFORE_OPEN, errors.get(0).getReason());

        analyzer = new SyntaxAnalyzer(new Lexer("][").tokenize());
        errors = analyzer.syntaxErrors();
        assertEquals(1, errors.size());
        assertEquals(SyntaxError.CLOSE_SQUARE_BEFORE_OPEN, errors.get(0).getReason());

        analyzer = new SyntaxAnalyzer(new Lexer("}{").tokenize());
        errors = analyzer.syntaxErrors();
        assertEquals(1, errors.size());
        assertEquals(SyntaxError.CLOSE_BRACE_BEFORE_OPEN, errors.get(0).getReason());
    }
}