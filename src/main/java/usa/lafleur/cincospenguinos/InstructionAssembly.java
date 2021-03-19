package usa.lafleur.cincospenguinos;

public class InstructionAssembly {
    private byte opCode;
    private Byte firstRegister;
    private Byte secondRegister;
    private Byte immediate;
    private Byte address;

    public InstructionAssembly() {}

    public byte build() {
        byte constructedInstruction = (byte) (opCode << 4);

        if (immediate != null) {
            constructedInstruction |= immediate;
        } else if (address != null) {
            constructedInstruction |= address;
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
        firstRegister = null;
        secondRegister = null;
        immediate = null;
        address = null;
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

    public void setAddress(String addressString) {
        address = Byte.parseByte(addressString.replaceAll("#", ""));
    }
}
