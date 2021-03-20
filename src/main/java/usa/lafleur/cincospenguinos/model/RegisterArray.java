package usa.lafleur.cincospenguinos.model;

/**
 * Abstraction of an array of registers.
 */
public class RegisterArray {
    public static final byte OVERFLOW_FLAG = 0b00000010;
    public static final byte ZERO_FLAG = 0b00000100;

    public static final int ACCUMULATOR_INDEX = 0;
    public static final int FLAGS_INDEX = 4;

    public static final short DEFAULT_STARTING_PROGRAM_COUNTER = 6;

    private byte[] _array;
    private short _programCounter;

    public RegisterArray() {
        _array = new byte[6];
        _programCounter = DEFAULT_STARTING_PROGRAM_COUNTER;
    }

    public RegisterArray(byte[] array) {
        _array = array;
        _programCounter = DEFAULT_STARTING_PROGRAM_COUNTER;
    }

    public byte accumulator() {
        return getRegister(ACCUMULATOR_INDEX);
    }

    public short programCounter() {
        return _programCounter;
    }

    public byte getRegister(int index) {
        if (index == FLAGS_INDEX) {
            throw new RuntimeException("Cannot read flag register!");
        }

        return _array[index];
    }

    public void accumulator(byte value) {
        setRegister(ACCUMULATOR_INDEX, value);
    }

    public void programCounter(short address) {
        _programCounter = address;
    }

    public void setRegister(int index, byte value) {
        if (index == FLAGS_INDEX) {
            throw new RuntimeException("Cannot write to flag register!");
        }

        _array[index] = value;
    }

    // TODO: Test this, homie
    public void clearFlag(byte flagValue) {
        byte mask = (byte) (flagValue ^ 0xff);
        _array[FLAGS_INDEX] &= mask;
    }

    public void setFlag(byte flagValue) {
        _array[FLAGS_INDEX] |= flagValue;
    }

    public boolean isSet(byte flag) {
        return (_array[FLAGS_INDEX] & flag) != 0;
    }
}
