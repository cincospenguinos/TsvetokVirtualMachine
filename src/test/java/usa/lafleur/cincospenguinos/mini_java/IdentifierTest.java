package usa.lafleur.cincospenguinos.mini_java;

import org.junit.Test;

import static org.junit.Assert.*;

public class IdentifierTest {
    @Test
    public void test_respondsWithNullIdentifier() {
        Identifier identifier = Identifier.fromString("null");
        assertEquals(Identifier.NULL, identifier);
    }

    @Test
    public void test_respondsWithFalseIdentifier() {
        Identifier identifier = Identifier.fromString("false");
        assertEquals(Identifier.FALSE, identifier);
    }

    @Test
    public void test_respondsWithTrueIdentifier() {
        Identifier identifier = Identifier.fromString("true");
        assertEquals(Identifier.TRUE, identifier);
    }

    @Test
    public void test_respondsWithPublicIdentifier() {
        Identifier identifier = Identifier.fromString("public");
        assertEquals(Identifier.ACCESS_PUBLIC, identifier);
    }

    @Test
    public void test_respondsWithIntegerLiteral() {
        assertEquals(Identifier.INTEGER_LITERAL, Identifier.fromString("1"));
        assertEquals(Identifier.INTEGER_LITERAL, Identifier.fromString("0"));
        assertEquals(Identifier.INTEGER_LITERAL, Identifier.fromString("100"));
        assertEquals(Identifier.INTEGER_LITERAL, Identifier.fromString("01"));
        assertEquals(Identifier.INTEGER_LITERAL, Identifier.fromString("-10"));
        assertEquals(Identifier.INTEGER_LITERAL, Identifier.fromString("-0001"));
    }
}