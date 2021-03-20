package usa.lafleur.cincospenguinos.assembler;

import usa.lafleur.cincospenguinos.model.instructions.OpCode;

public class InstructionAssembly {
    private static final int[] OP_FLAG_INSTRUCTIONS = new int[] { OpCode.STORE_MEMORY, OpCode.LOAD_MEMORY,
            OpCode.MOVE_IMMEDIATE, OpCode.MOVE_REGISTER, OpCode.ADD_IMMEDIATE, OpCode.ADD_REGISTER,
            OpCode.MULTIPLY_REGISTER, OpCode.MULTIPLY_IMMEDIATE, OpCode.LOGICAL_AND, OpCode.LOGICAL_OR,
            OpCode.DIVIDE_REGISTER, OpCode.DIVIDE_IMMEDIATE,
    };
    private byte opCode;
    private boolean opFlag;
    private Byte firstRegister;
    private Byte secondRegister;
    private Byte immediate;

    public InstructionAssembly() {}

    public byte build() {
        byte constructedInstruction = (byte) (opCode << 4);

        if (isOpFlagInstruction()) {
            if (opFlag) {
                constructedInstruction |= 0b00010000;
            } else {
                constructedInstruction &= 0b11101111;
            }
        }

        if (immediate != null) {
            constructedInstruction |= immediate;
        }

        if (firstRegister != null) {
            constructedInstruction |= (firstRegister << 2);
        }

        if (secondRegister != null) {
            constructedInstruction |= secondRegister;
        }

        return constructedInstruction;
    }

    private boolean isOpFlagInstruction() {
        for (int opFlagInstruction : OP_FLAG_INSTRUCTIONS) {
            if (opFlagInstruction == opCode) {
                return true;
            }
        }

        return false;
    }

    public void clear() {
        opCode = 0;
        opFlag = false;
        firstRegister = null;
        secondRegister = null;
        immediate = null;
    }

    public void setOpCode(byte opCodeValue) {
        opCode = opCodeValue;
    }

    public void setFirstRegister(String registerString) {
        firstRegister = registerFor(registerString);
    }

    public void setSecondRegister(String registerString) {
        secondRegister = registerFor(registerString);
    }

    private byte registerFor(String str) {
        String register = str.replace("$", "");

        if (register.equals("ak")) {
            return (byte) 0;
        }

        if (register.equals("retr")) {
            return (byte) 3;
        }

        return Byte.parseByte(register.replace("rej", ""));
    }

    public void setImmediate(String value) {
        immediate = Byte.parseByte(value);
    }

    public void setOpFlag(boolean flag) {
        opFlag = flag;
    }
}
