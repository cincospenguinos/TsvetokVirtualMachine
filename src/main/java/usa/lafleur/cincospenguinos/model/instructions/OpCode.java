package usa.lafleur.cincospenguinos.model.instructions;

/**
 * Enumerated type for operation codes. As a class because enums are the worst in java.
 */
public class OpCode {
    public static final int NO_OP = 0;
    public static final int SYSTEM_CALL = 1;
    public static final int LOAD_MEMORY = 2;
    public static final int STORE_MEMORY = 3;
    public static final int ADD_REGISTER = 4;
    public static final int ADD_IMMEDIATE = 5;
    public static final int MULTIPLY_REGISTER = 6;
    public static final int MULTIPLY_IMMEDIATE = 7;
    public static final int MOVE_REGISTER = 8;
    public static final int MOVE_IMMEDIATE = 9;
    public static final int LOGICAL_AND = 10;
    public static final int LOGICAL_OR = 11;
    public static final int DIVIDE_REGISTER = 12;
    public static final int DIVIDE_IMMEDIATE = 13;
    public static final int JUMP_ON_ZERO = 14;
    public static final int HALT = 15;
}
