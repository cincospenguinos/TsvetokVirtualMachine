package usa.lafleur.cincospenguinos.mini_java;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    private final String _program;

    public Tokenizer(String program) {
        _program = program;
    }

    public List<TokenItem> tokenize() {
        List<TokenItem> tokens = new ArrayList<>();
        String[] chunkedProgram = _program
                .replaceAll("\\(", " ( ")
                .replaceAll("\\)", " ) ")
                .replaceAll("\\[", " [ ")
                .replaceAll("]", " ] ")
                .split("\\s+");

        for (String str: chunkedProgram) {
            Token token = Token.fromString(str);
            tokens.add(new TokenItem(token, str));
        }

        return tokens;
    }
}
