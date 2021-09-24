package usa.lafleur.cincospenguinos.mini_java;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TokenizerTest {
    @Test
    public void test_respondsWithListOfTokens() {
        Token[] correctTokenOrder = {
            Token.ACCESS_PUBLIC, Token.CLASS, Token.IDENTIFIER, Token.OPEN_BRACE,
            Token.ACCESS_PUBLIC, Token.STATIC, Token.TYPE_VOID, Token.IDENTIFIER,
            Token.OPEN_PAREN, Token.IDENTIFIER, Token.OPEN_SQUARE_BRACE, Token.CLOSE_SQUARE_BRACE,
            Token.IDENTIFIER, Token.CLOSE_PAREN, Token.OPEN_BRACE, Token.CLOSE_BRACE,
            Token.CLOSE_BRACE,
        };
        List<Token> correctTokenList = Arrays.asList(correctTokenOrder);

        Tokenizer tokenizer = new Tokenizer("public class MainClass { \n" +
                "public static void main(String[] args) {\n" +
                "}\n" +
                "}");
        List<Token> tokenList = tokenizer.tokenize();
        assertEquals(correctTokenList, tokenList);
    }
}