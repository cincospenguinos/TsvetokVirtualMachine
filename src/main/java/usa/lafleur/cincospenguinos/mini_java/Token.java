package usa.lafleur.cincospenguinos.mini_java;

public enum Token {
    NULL, FALSE, TRUE, ACCESS_PUBLIC, INTEGER_LITERAL, THIS_LITERAL, IF_LITERAL, ELSE_LITERAL, OPEN_PAREN, CLOSE_PAREN, WHILE, TYPE_INT, TYPE_BOOLEAN, TYPE_VOID, IDENTIFIER;

    private static final String INTEGER_LITERAL_PATTERN = "^-?\\d+$";
    private static final String IDENTIFIER_PATTERN = "^[a-zA-Z]+[\\da-zA-Z]+$";

    public static Token fromString(String potentialIdentifier) {
        switch (potentialIdentifier) {
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
        }

        if (potentialIdentifier.matches(INTEGER_LITERAL_PATTERN)) {
            return Token.INTEGER_LITERAL;
        }

        if (potentialIdentifier.matches(IDENTIFIER_PATTERN)) {
            return Token.IDENTIFIER;
        }

        return null;
    }
}
