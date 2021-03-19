package usa.lafleur.cincospenguinos.model.instructions;

/**
 * Enumerated type for operation codes. As a class because enums are the worst in java.
 */
public class OpCode {
    public static final int NO_OP = 0;
    public static final int ACCESS_MEMORY = 1;
    public static final int MOVE = 2;
    public static final int ADD = 3;
    public static final int MULTIPLY = 4;
    public static final int DIVIDE = 5;
    public static final int SIGN_TOGGLE = 6;
    public static final int SYSTEM_CALL = 7;
}
