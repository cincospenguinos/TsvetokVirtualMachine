package usa.lafleur.cincospenguinos.model.instructions;

/**
 * Enumerated type for operation codes. As a class because enums are the worst in java.
 */
public class OpCode {
    public static final int NO_OP = 0;
    public static final int LOAD = 1;
    public static final int STORE = 2;
    public static final int JUMP = 3;
    public static final int MOVE_IMMEDIATE = 4;
    public static final int ADD = 8;
    public static final int MULTIPLY = 9;
    public static final int DIVIDE = 10;
    public static final int BITWISE_AND = 11;
    public static final int BITWISE_OR = 12;
    public static final int TOGGLE_SIGN = 13;
    public static final int SYSTEM_CALL = 14;
    public static final int HALT = 15;
}
