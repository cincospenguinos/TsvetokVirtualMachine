package usa.lafleur.cincospenguinos.model;

/**
 * Abstraction of an array of registers.
 */
public class RegisterArray {
    public static final byte OVERFLOW_FLAG = 0b00000010;
    public static final byte ZERO_FLAG = 0b00000100;

    public static final int ACCUMULATOR_INDEX = 0;
    public static final int FLAGS_INDEX = 4;


    private byte[] _array;

    public RegisterArray() {
        _array = new byte[5];
    }

    public RegisterArray(byte[] array) {
        _array = array;
    }

    public byte accumulator() {
        return getRegister(ACCUMULATOR_INDEX);
    }

    public byte getRegister(int index) {
        if (index == FLAGS_INDEX) {
            throw new RuntimeException("Cannot read flag register!");
        }

        return _array[index];
    }

    public void setRegister(int index, byte value) {
        if (index == FLAGS_INDEX) {
            throw new RuntimeException("Cannot write to flag register!");
        }

        _array[index] = value;
    }

    public void setFlag(byte flagValue) {
        _array[FLAGS_INDEX] |= flagValue;
    }

    public boolean isSet(byte flag) {
        return (_array[FLAGS_INDEX] & flag) != 0;
    }
}
