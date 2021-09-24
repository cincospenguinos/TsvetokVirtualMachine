package usa.lafleur.cincospenguinos.mini_java;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SyntaxAnalyzer {
    private final List<TokenItem> _tokenStream;

    public SyntaxAnalyzer(List<TokenItem> tokenStream) {
        _tokenStream = tokenStream;
    }

    public List<SyntaxErrorItem> syntaxErrors() {
        List<SyntaxErrorItem> items = new ArrayList<>();

        BraceErrorAggregate aggregate = new BraceErrorAggregate(Token.OPEN_BRACE, Token.CLOSE_BRACE,
                SyntaxError.CLOSE_BRACE_BEFORE_OPEN, SyntaxError.MISMATCHED_BRACES);

        int squareBrackets = 0;
        int parens = 0;

        for (TokenItem item : _tokenStream) {
            items.add(aggregate.consider(item));

            switch(item.getToken()) {
                case OPEN_SQUARE_BRACE:
                    squareBrackets++;
                    break;
                case CLOSE_SQUARE_BRACE:
                    if (squareBrackets == 0) {
                        items.add(new SyntaxErrorItem(SyntaxError.CLOSE_SQUARE_BEFORE_OPEN, ""));
                    }

                    squareBrackets--;
                    break;
                case OPEN_PAREN:
                    parens++;
                    break;
                case CLOSE_PAREN:
                    if (parens == 0) {
                        items.add(new SyntaxErrorItem(SyntaxError.CLOSE_PAREN_BEFORE_OPEN, ""));
                    }

                    parens--;
                    break;
            }
        }

        items.add(aggregate.complete());

        if (squareBrackets != 0) {
            items.add(new SyntaxErrorItem(SyntaxError.MISMATCHED_BRACKETS, ""));
        }

        if (parens != 0) {
            items.add(new SyntaxErrorItem(SyntaxError.MISMATCHED_PARENS, ""));
        }

        return items
                .stream()
                .filter(e -> !(e instanceof NullSyntaxErrorItem))
                .collect(Collectors.toList());
    }
}
