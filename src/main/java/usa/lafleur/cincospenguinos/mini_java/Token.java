package usa.lafleur.cincospenguinos.mini_java;

public enum Token {
    NULL, FALSE, TRUE, ACCESS_PUBLIC, INTEGER_LITERAL, THIS_LITERAL, IF_LITERAL, ELSE_LITERAL, OPEN_PAREN,
    CLOSE_PAREN, WHILE, TYPE_INT, TYPE_BOOLEAN, TYPE_VOID, IDENTIFIER, OPERATION_ADD, OPERATION_SUBTRACT,
    OPERATION_MULTIPLY, OPERATION_DIVIDE, NEW, OPEN_BRACE, CLOSE_BRACE, SEMICOLON, OPERATION_NOT, OPERATION_ASSIGNMENT, OPERATION_EQUALITY, OPERATION_NOT_EQUALITY, OPEN_SQUARE_BRACE, CLOSE_SQUARE_BRACE, CLASS, STATIC;

    private static final String INTEGER_LITERAL_PATTERN = "^-?\\d+$";
    private static final String IDENTIFIER_PATTERN = "^[a-zA-Z]+[\\da-zA-Z]+$";

    public static Token fromString(String potentialToken) {
        switch (potentialToken) {
            case "null":
                return Token.NULL;
            case "false":
                return Token.FALSE;
            case "true":
                return Token.TRUE;
            case "public":
                return Token.ACCESS_PUBLIC;
            case "this":
                return Token.THIS_LITERAL;
            case "if":
                return Token.IF_LITERAL;
            case "else":
                return Token.ELSE_LITERAL;
            case "(":
                return Token.OPEN_PAREN;
            case ")":
                return Token.CLOSE_PAREN;
            case "while":
                return Token.WHILE;
            case "int":
                return Token.TYPE_INT;
            case "void":
                return Token.TYPE_VOID;
            case "boolean":
                return Token.TYPE_BOOLEAN;
            case "+":
                return Token.OPERATION_ADD;
            case "-":
                return Token.OPERATION_SUBTRACT;
            case "*":
                return Token.OPERATION_MULTIPLY;
            case "/":
                return Token.OPERATION_DIVIDE;
            case "new":
                return Token.NEW;
            case "{":
                return Token.OPEN_BRACE;
            case "}":
                return Token.CLOSE_BRACE;
            case ";":
                return Token.SEMICOLON;
            case "==":
                return Token.OPERATION_EQUALITY;
            case "!=":
                return Token.OPERATION_NOT_EQUALITY;
            case "=":
                return Token.OPERATION_ASSIGNMENT;
            case "!":
                return Token.OPERATION_NOT;
            case "[":
                return Token.OPEN_SQUARE_BRACE;
            case "]":
                return Token.CLOSE_SQUARE_BRACE;
            case "class":
                return Token.CLASS;
            case "static":
                return Token.STATIC;
        }

        if (potentialToken.matches(INTEGER_LITERAL_PATTERN)) {
            return Token.INTEGER_LITERAL;
        }

        if (potentialToken.matches(IDENTIFIER_PATTERN)) {
            return Token.IDENTIFIER;
        }

        throw new InvalidTokenException(potentialToken);
    }
}
