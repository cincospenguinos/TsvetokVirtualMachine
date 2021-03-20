package usa.lafleur.cincospenguinos.model.instructions;

/**
 * Enumerated type for operation codes. As a class because enums are the worst in java.
 */
public class OpCode {
    public static final int NO_OP = 0;
    public static final int SYSTEM_CALL = 1;
    public static final int ACCESS_MEMORY = 2;
    public static final int ADD = 4;
    public static final int MULTIPLY = 6;
    public static final int MOVE = 8;
    public static final int LOGICAL_AND = 10;
    public static final int LOGICAL_OR = 11;
    public static final int DIVIDE = 12;
    public static final int JUMP_ON_ZERO = 14;
    public static final int HALT = 15;
}
