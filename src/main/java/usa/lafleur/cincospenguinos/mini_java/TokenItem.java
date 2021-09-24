package usa.lafleur.cincospenguinos.mini_java;

import java.util.Objects;

public class TokenItem {
    private final Token _token;
    private final String _value;

    public TokenItem(Token token, String value) {
        _token = token;
        _value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TokenItem)) return false;
        TokenItem tokenItem = (TokenItem) o;
        return _token == tokenItem._token && _value.equals(tokenItem._value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_token, _value);
    }

    public Token getToken() {
        return _token;
    }

    public String getValue() {
        return _value;
    }
}
