package usa.lafleur.cincospenguinos.mini_java.lexer;

public class SyntaxErrorItem {
    private SyntaxError _reason;
    private String _surroundingInfo;

    public SyntaxErrorItem(SyntaxError reason, String surroundingInfo) {
        _reason = reason;
        _surroundingInfo = surroundingInfo;
    }

    public SyntaxError getReason() {
        return _reason;
    }
}
