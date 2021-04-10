package usa.lafleur.cincospenguinos.assembler.exceptions;

public class LabelDoesNotExistException extends RuntimeException {
    public LabelDoesNotExistException(String label) {
        super("\"" + label + "\" nul etree ouna label");
    }
}
