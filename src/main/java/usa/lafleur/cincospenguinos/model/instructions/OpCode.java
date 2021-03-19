package usa.lafleur.cincospenguinos.model.instructions;

/**
 * Enumerated type for operation codes. As a class because enums are the worst in java.
 */
public class OpCode {
    public static final int NO_OP = 0;
    public static final int MOVE_IMMEDIATE = 4;
    public static final int HALT = 15;
}
