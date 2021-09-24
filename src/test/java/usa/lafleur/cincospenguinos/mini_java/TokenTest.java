package usa.lafleur.cincospenguinos.mini_java;

import org.junit.Test;

import static org.junit.Assert.*;

public class TokenTest {
    @Test
    public void test_respondsWithNullIdentifier() {
        Token token = Token.fromString("null");
        assertEquals(Token.NULL, token);
    }

    @Test
    public void test_respondsWithFalseIdentifier() {
        Token token = Token.fromString("false");
        assertEquals(Token.FALSE, token);
    }

    @Test
    public void test_respondsWithTrueIdentifier() {
        Token token = Token.fromString("true");
        assertEquals(Token.TRUE, token);
    }

    @Test
    public void test_respondsWithPublicIdentifier() {
        Token token = Token.fromString("public");
        assertEquals(Token.ACCESS_PUBLIC, token);
    }

    @Test
    public void test_respondsWithIntegerLiteral() {
        assertEquals(Token.INTEGER_LITERAL, Token.fromString("1"));
        assertEquals(Token.INTEGER_LITERAL, Token.fromString("0"));
        assertEquals(Token.INTEGER_LITERAL, Token.fromString("100"));
        assertEquals(Token.INTEGER_LITERAL, Token.fromString("01"));
        assertEquals(Token.INTEGER_LITERAL, Token.fromString("-10"));
        assertEquals(Token.INTEGER_LITERAL, Token.fromString("-0001"));
    }

    @Test
    public void test_respondsWithThisLiteral() {
        assertEquals(Token.THIS_LITERAL, Token.fromString("this"));
    }

    @Test
    public void test_respondsWithIfLiteral() {
        assertEquals(Token.IF_LITERAL, Token.fromString("if"));
    }

    @Test
    public void test_respondsWithElseLiteral() {
        assertEquals(Token.ELSE_LITERAL, Token.fromString("else"));
    }

    @Test
    public void test_respondsWithOpenParenLiteral() {
        assertEquals(Token.OPEN_PAREN, Token.fromString("("));
    }

    @Test
    public void test_respondsWithCloseParenLiteral() {
        assertEquals(Token.CLOSE_PAREN, Token.fromString(")"));
    }

    @Test
    public void test_respondsWithWhileLiteral() {
        assertEquals(Token.WHILE, Token.fromString("while"));
    }

    @Test
    public void test_respondsWithTypeInt() {
        assertEquals(Token.TYPE_INT, Token.fromString("int"));
    }

    @Test
    public void test_respondsWithTypeBoolean() {
        assertEquals(Token.TYPE_BOOLEAN, Token.fromString("boolean"));
    }

    @Test
    public void test_respondsWithTypeVoid() {
        assertEquals(Token.TYPE_VOID, Token.fromString("void"));
    }

    @Test
    public void test_respondsWithIdentifier() {
        assertEquals(Token.IDENTIFIER, Token.fromString("asdf"));
        assertEquals(Token.IDENTIFIER, Token.fromString("foo1"));
        assertEquals(Token.IDENTIFIER, Token.fromString("SomeClassName"));
        assertEquals(Token.IDENTIFIER, Token.fromString("someMethodName"));
    }

    @Test
    public void test_respondsWithOperations() {
        assertEquals(Token.OPERATION_ADD, Token.fromString("+"));
        assertEquals(Token.OPERATION_SUBTRACT, Token.fromString("-"));
        assertEquals(Token.OPERATION_MULTIPLY, Token.fromString("*"));
        assertEquals(Token.OPERATION_DIVIDE, Token.fromString("/"));
    }

    @Test
    public void test_respondsWithNewKeyword() {
        assertEquals(Token.NEW, Token.fromString("new"));
    }
}