package usa.lafleur.cincospenguinos.mini_java;

public enum Identifier {
    NULL, FALSE, TRUE, ACCESS_PUBLIC, INTEGER_LITERAL;

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
        }

        if (potentialIdentifier.matches(INTEGER_LITERAL_PATTERN)) {
            return Identifier.INTEGER_LITERAL;
        }

        return null;
    }
}
