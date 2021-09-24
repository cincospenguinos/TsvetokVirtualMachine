package usa.lafleur.cincospenguinos.mini_java;

public enum Token {
    NULL, FALSE, TRUE, ACCESS_PUBLIC, INTEGER_LITERAL, THIS_LITERAL, IF_LITERAL, ELSE_LITERAL, OPEN_PAREN, CLOSE_PAREN;

    private static final String INTEGER_LITERAL_PATTERN = "^-?\\d+$";

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
        }

        if (potentialIdentifier.matches(INTEGER_LITERAL_PATTERN)) {
            return Token.INTEGER_LITERAL;
        }

        return null;
    }
}
