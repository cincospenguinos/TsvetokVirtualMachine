package usa.lafleur.cincospenguinos.machine;

public class RandomAccessMemory {
    private static final int MAXIMUM_MEMORY_AMOUNT = 2 << 16;
    private final byte[] _memory;

    public RandomAccessMemory() {
        _memory = new byte[MAXIMUM_MEMORY_AMOUNT];
    }

    public RandomAccessMemory(byte[] initialMemory) {
        _memory = new byte[MAXIMUM_MEMORY_AMOUNT];
        System.arraycopy(initialMemory, 0, _memory, 0, initialMemory.length);
    }

    public byte readAt(byte upper, byte lower) {
        int index = (upper << 8) | lower;
        return _memory[index];
    }

    public void writeTo(byte upper, byte lower, byte value) {
        int index = (upper << 8) | lower;
        _memory[index] = value;
    }
}
