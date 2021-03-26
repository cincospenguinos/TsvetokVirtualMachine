package usa.lafleur.cincospenguinos.assembler.exceptions;

public class DuplicateLabelException extends RuntimeException {
    public DuplicateLabelException(String label) { super("\"" + label + "\" etree deja"); }
}
