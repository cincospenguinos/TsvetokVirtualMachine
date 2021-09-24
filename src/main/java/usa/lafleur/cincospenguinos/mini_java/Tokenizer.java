package usa.lafleur.cincospenguinos.mini_java;

public class Tokenizer {
    private static final String INTEGER_LITERAL_PATTERN = "^-?\\d+$";

    public Tokenizer() {}

    public Token identifierFor(String uninterruptedChunk) {
        return Token.fromString(uninterruptedChunk);
    }
}
