package usa.lafleur.cincospenguinos.model;

public class RegisterArray {
    private final byte[] _registers;
    private boolean _overflowFlag;
    private boolean _zeroFlag;

    public RegisterArray() {
        _registers = new byte[16];
    }

    public RegisterArray(byte[] registers) {
        _registers = registers;
        _overflowFlag = false;
        _zeroFlag = false;
    }

    public byte getValueOf(int index) {
        return _registers[index];
    }

    public void setValueOf(int index, byte value) {
        _registers[index] = value;
    }

    public boolean isOverflowFlagSet() {
        return _overflowFlag;
    }

    public void setOverflowFlag(boolean value) {
        _overflowFlag = value;
    }

    public void setZeroFlag(boolean value) {
        _zeroFlag = value;
    }

    public boolean isZeroFlagSet() {
        return _zeroFlag;
    }
}
