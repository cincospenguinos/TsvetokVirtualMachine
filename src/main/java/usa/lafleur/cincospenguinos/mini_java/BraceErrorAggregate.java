package usa.lafleur.cincospenguinos.mini_java;

import usa.lafleur.cincospenguinos.mini_java.lexer.Token;
import usa.lafleur.cincospenguinos.mini_java.lexer.TokenItem;

class BraceErrorAggregate {
    int _braceCount;
    Token _open;
    Token _close;
    SyntaxError _closeError;
    SyntaxError _mismatchError;

    BraceErrorAggregate(Token open, Token close, SyntaxError closeError, SyntaxError mismatchError) {
        _open = open;
        _close = close;
        _closeError = closeError;
        _mismatchError = mismatchError;
        _braceCount = 0;
    }

    public static BraceErrorAggregate getAggregateFor(String braceType) {
        if ("{".equals(braceType)) {
            return new BraceErrorAggregate(Token.OPEN_BRACE, Token.CLOSE_BRACE,
                    SyntaxError.CLOSE_BRACE_BEFORE_OPEN, SyntaxError.MISMATCHED_BRACES);
        } else if ("[".equals(braceType)) {
            return new BraceErrorAggregate(Token.OPEN_SQUARE_BRACE, Token.CLOSE_SQUARE_BRACE,
                    SyntaxError.CLOSE_SQUARE_BEFORE_OPEN, SyntaxError.MISMATCHED_BRACKETS);
        } else if ("(".equals(braceType)) {
            return new BraceErrorAggregate(Token.OPEN_PAREN, Token.CLOSE_PAREN,
                    SyntaxError.CLOSE_PAREN_BEFORE_OPEN, SyntaxError.MISMATCHED_PARENS);
        }

        throw new RuntimeException("No aggregate for \"" + braceType + "\"");
    }

    /**
     * Considers the token item provided, either creating a
     *
     * @param item - item to consier.
     * @return SyntaxErrorItem created from the provided token
     */
    SyntaxErrorItem consider(TokenItem item) {
        Token token = item.getToken();

        if (token == _open) {
            _braceCount++;
        } else if (token == _close) {
            if (_braceCount-- == 0) {
                return new SyntaxErrorItem(_closeError, "");
            }
        }

        return new NullSyntaxErrorItem();
    }

    /**
     * Indicate that the process of counting braces is complete.
     *
     * @return Error if one occurred.
     */
    SyntaxErrorItem complete() {
        if (_braceCount != 0) {
            return new SyntaxErrorItem(_mismatchError, "");
        }

        return new NullSyntaxErrorItem();
    }
}
