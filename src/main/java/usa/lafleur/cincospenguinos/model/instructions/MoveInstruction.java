package usa.lafleur.cincospenguinos.model.instructions;

public class MoveInstruction extends Instruction {
    public MoveInstruction(byte raw) {
        super(raw);
    }

    public void execute(byte[] memory, byte[] registerArray) {
        if (opFlagSet()) {

        } else {
            registerArray[secondRegister()] = registerArray[firstRegister()];
        }
    }

    private boolean opFlagSet() {
        byte rawByte = getRawByte();
        rawByte &= 0b00010000;
        rawByte = (byte) (rawByte >> 4);

        return rawByte == (byte) 0x01;
    }

    private int firstRegister() {
        return (getRawByte() & 0b00001100) >> 2;
    }

    private int secondRegister() {
        return (getRawByte() & 0b00000011);
    }
}
