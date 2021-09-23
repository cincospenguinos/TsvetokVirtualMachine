package usa.lafleur.cincospenguinos.mini_java;

public class Tokenizer {
    private static final String INTEGER_LITERAL_PATTERN = "^-?\\d+$";

    public Tokenizer() {}

    public Identifier identifierFor(String uninterruptedChunk) {
        switch (uninterruptedChunk) {
            case "null":
                return Identifier.NULL;
            case "false":
                return Identifier.FALSE;
            case "true":
                return Identifier.TRUE;
            case "public":
                return Identifier.ACCESS_PUBLIC;
        }

        if (uninterruptedChunk.matches(INTEGER_LITERAL_PATTERN)) {
            return Identifier.INTEGER_LITERAL;
        }

        return null;
    }
}
