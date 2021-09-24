package usa.lafleur.cincospenguinos.mini_java;

public enum Identifier {
    NULL, FALSE, TRUE, ACCESS_PUBLIC, INTEGER_LITERAL, THIS_LITERAL, IF_LITERAL, ELSE_LITERAL, OPEN_PAREN, CLOSE_PAREN;

    private static final String INTEGER_LITERAL_PATTERN = "^-?\\d+$";

    public static Identifier fromString(String potentialIdentifier) {
        switch (potentialIdentifier) {
            case "null":
                return Identifier.NULL;
            case "false":
                return Identifier.FALSE;
            case "true":
                return Identifier.TRUE;
            case "public":
                return Identifier.ACCESS_PUBLIC;
            case "this":
                return Identifier.THIS_LITERAL;
            case "if":
                return Identifier.IF_LITERAL;
            case "else":
                return Identifier.ELSE_LITERAL;
            case "(":
                return Identifier.OPEN_PAREN;
            case ")":
                return Identifier.CLOSE_PAREN;
        }

        if (potentialIdentifier.matches(INTEGER_LITERAL_PATTERN)) {
            return Identifier.INTEGER_LITERAL;
        }

        return null;
    }
}
