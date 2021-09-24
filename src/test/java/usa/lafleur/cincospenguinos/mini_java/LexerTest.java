package usa.lafleur.cincospenguinos.mini_java;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class LexerTest {
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

        Lexer lexer = new Lexer("public class MainClass { \n" +
                "public static void main(String[] args) {\n" +
                "}\n" +
                "}");
        List<TokenItem> tokenList = lexer.tokenize();

        assertEquals(correctTokenList.size(), tokenList.size());
        for (int i = 0; i < correctTokenList.size(); i++) {
            Token actual = tokenList.get(i).getToken();
            assertEquals(correctTokenList.get(i), actual);
        }
    }
}