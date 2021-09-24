package usa.lafleur.cincospenguinos.mini_java;

import org.junit.Test;
import usa.lafleur.cincospenguinos.mini_java.lexer.Token;
import usa.lafleur.cincospenguinos.mini_java.lexer.TokenItem;

import static org.junit.Assert.*;

public class TokenItemTest {
    @Test
    public void test_equalityMatchesTokenAndValue() {
        assertEquals(new TokenItem(Token.OPERATION_ADD, "+"),new TokenItem(Token.OPERATION_ADD, "+"));
        assertEquals(new TokenItem(Token.IDENTIFIER, "foo"), new TokenItem(Token.IDENTIFIER, "foo"));
        assertNotEquals(new TokenItem(Token.IDENTIFIER, "foo"), new TokenItem(Token.IDENTIFIER, "bar"));
    }
}