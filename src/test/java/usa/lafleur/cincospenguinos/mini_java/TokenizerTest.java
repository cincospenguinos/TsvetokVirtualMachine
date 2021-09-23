package usa.lafleur.cincospenguinos.mini_java;

import org.junit.Test;

import static org.junit.Assert.*;

public class TokenizerTest {
    private Tokenizer tokenizer = new Tokenizer();

    @Test
    public void test_respondsWithNullIdentifier() {
        Identifier identifier = tokenizer.identifierFor("null");
        assertEquals(Identifier.NULL, identifier);
    }

    @Test
    public void test_respondsWithFalseIdentifier() {
        Identifier identifier = tokenizer.identifierFor("false");
        assertEquals(Identifier.FALSE, identifier);
    }

    @Test
    public void test_respondsWithTrueIdentifier() {
        Identifier identifier = tokenizer.identifierFor("true");
        assertEquals(Identifier.TRUE, identifier);
    }

    @Test
    public void test_respondsWithPublicIdentifier() {
        Identifier identifier = tokenizer.identifierFor("public");
        assertEquals(Identifier.ACCESS_PUBLIC, identifier);
    }

    @Test
    public void test_respondsWithIntegerLiteral() {
        assertEquals(Identifier.INTEGER_LITERAL, tokenizer.identifierFor("1"));
        assertEquals(Identifier.INTEGER_LITERAL, tokenizer.identifierFor("0"));
        assertEquals(Identifier.INTEGER_LITERAL, tokenizer.identifierFor("100"));
        assertEquals(Identifier.INTEGER_LITERAL, tokenizer.identifierFor("01"));
        assertEquals(Identifier.INTEGER_LITERAL, tokenizer.identifierFor("-10"));
        assertEquals(Identifier.INTEGER_LITERAL, tokenizer.identifierFor("-0001"));
    }
}