package usa.lafleur.cincospenguinos.mini_java.exceptions;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException(String potentialToken) {
        super("\"" + potentialToken + "\" nul etree buna toukan");
    }
}
