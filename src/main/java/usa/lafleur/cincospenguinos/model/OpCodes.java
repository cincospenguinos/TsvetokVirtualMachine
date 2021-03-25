package usa.lafleur.cincospenguinos.model;

public class OpCodes {
    public static final int NO_OP = 0;
    public static final int MEMORY = 1;
    public static final int ADD_REGISTERS = 2;
    public static final int ADD_IMMEDIATE = 3;
    public static final int MULTIPLY_REGISTERS = 4;
    public static final int MULTIPLY_IMMEDIATE = 5;
    public static final int DIVIDE_REGISTERS = 6;
    public static final int DIVIDE_IMMEDIATE = 7;
    public static final int JUMP_UNCONDITIONAL = 8;
    public static final int JUMP_ON_ZERO = 9;
    public static final int JUMP_ON_NON_ZERO = 10;
    public static final int RETURN = 11;
    public static final int PUSH_STACK = 12;
    public static final int POP_STACK = 13;
    public static final int SYSTEM_CALL = 14;
    public static final int HALT = 15;
}
