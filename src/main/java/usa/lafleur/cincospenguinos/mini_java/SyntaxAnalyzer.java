package usa.lafleur.cincospenguinos.mini_java;

import java.util.ArrayList;
import java.util.List;

public class SyntaxAnalyzer {
    private final List<TokenItem> _tokenStream;

    public SyntaxAnalyzer(List<TokenItem> tokenStream) {
        _tokenStream = tokenStream;
    }

    public List<SyntaxErrorItem> syntaxErrors() {
        List<SyntaxErrorItem> items = new ArrayList<>();

        int braces = 0;
        int squareBrackets = 0;
        int parens = 0;

        for (TokenItem item : _tokenStream) {
            switch(item.getToken()) {
                case OPEN_BRACE:
                    braces++;
                    break;
                case CLOSE_BRACE:
                    if (braces == 0) {
                        items.add(new SyntaxErrorItem(SyntaxError.CLOSE_BRACE_BEFORE_OPEN, ""));
                    }

                    braces--;
                    break;
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

        if (braces != 0) {
            items.add(new SyntaxErrorItem(SyntaxError.MISMATCHED_BRACES, ""));
        }

        if (squareBrackets != 0) {
            items.add(new SyntaxErrorItem(SyntaxError.MISMATCHED_BRACKETS, ""));
        }

        if (parens != 0) {
            items.add(new SyntaxErrorItem(SyntaxError.MISMATCHED_PARENS, ""));
        }

        return items;
    }
}
