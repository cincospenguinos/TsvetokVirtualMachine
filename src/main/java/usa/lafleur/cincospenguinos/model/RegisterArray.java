package usa.lafleur.cincospenguinos.model;

public class RegisterArray {
    private final byte[] _registers;

    public RegisterArray() {
        _registers = new byte[16];
    }

    public RegisterArray(byte[] registers) {
        _registers = registers;
    }

    public byte getValueOf(int index) {
        return _registers[index];
    }

    public void setValueOf(int index, byte value) {
        _registers[index] = value;
    }
}
