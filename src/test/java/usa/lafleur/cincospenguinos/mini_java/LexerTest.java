package usa.lafleur.cincospenguinos.mini_java;

import org.junit.Test;
import usa.lafleur.cincospenguinos.mini_java.lexer.Lexer;
import usa.lafleur.cincospenguinos.mini_java.lexer.Token;
import usa.lafleur.cincospenguinos.mini_java.lexer.TokenItem;

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

        Lexer lexer = new Lexer("public class MainClass{ \n" +
                "public static void main(String[] args){\n" +
                "}\n" +
                "}");
        List<TokenItem> tokenList = lexer.tokenize();
        assertTokensEqual(correctTokenList, tokenList);
    }

    @Test
    public void test_respondsWithVariableDeclaration() {
        Token[] correctTokenOrder = { Token.TYPE_INT, Token.IDENTIFIER, Token.SEMICOLON };
        List<Token> correctTokenList = Arrays.asList(correctTokenOrder);
        Lexer lexer = new Lexer("int i;");
        List<TokenItem> tokenList = lexer.tokenize();
        assertTokensEqual(correctTokenList, tokenList);
    }

    private void assertTokensEqual(List<Token> expected, List<TokenItem> actual) {
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            Token actualToken = actual.get(i).getToken();
            assertEquals(expected.get(i), actualToken);
        }
    }
}