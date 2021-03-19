package usa.lafleur.cincospenguinos.assembler;

public class InstructionAssembly {
    private byte opCode;
    private boolean opFlag;
    private Byte firstRegister;
    private Byte secondRegister;
    private Byte immediate;

    public InstructionAssembly() {}

    public byte build() {
        byte constructedInstruction = (byte) (opCode << 4);

        if (opFlag) {
            constructedInstruction |= 0b00010000;
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
        firstRegister = Byte.parseByte(registerString.replaceAll("\\$rej", ""));
    }

    public void setSecondRegister(String registerString) {
        secondRegister = Byte.parseByte(registerString.replaceAll("\\$rej", ""));
    }

    public void setImmediate(String value) {
        immediate = Byte.parseByte(value);
    }

    public void setOpFlag(boolean flag) {
        opFlag = flag;
    }
}
