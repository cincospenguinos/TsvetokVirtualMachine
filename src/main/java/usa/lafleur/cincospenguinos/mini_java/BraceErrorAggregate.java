package usa.lafleur.cincospenguinos.mini_java;

import java.util.ArrayList;
import java.util.List;

class BraceErrorAggregate {
    int _braceCount;
    Token _open;
    Token _close;
    SyntaxError _closeError;
    SyntaxError _mismatchError;

    List<SyntaxErrorItem> _errorItems;

    BraceErrorAggregate(Token open, Token close, SyntaxError closeError, SyntaxError mismatchError) {
        _open = open;
        _close = close;
        _closeError = closeError;
        _mismatchError = mismatchError;
        _braceCount = 0;
        _errorItems = new ArrayList<>();
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

    SyntaxErrorItem complete() {
        if (_braceCount != 0) {
            return new SyntaxErrorItem(_mismatchError, "");
        }

        return new NullSyntaxErrorItem();
    }
}
