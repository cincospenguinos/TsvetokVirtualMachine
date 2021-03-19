package usa.lafleur.cincospenguinos;

import java.util.Arrays;

/**
 * Representation of a single TVM executable file.
 */
public class TsvetokExecutable {
    public static final byte[] VALID_HEADER = new byte[] { 0x54, 0x56, 0x4d, 97, 71, 12 };

    private byte[] _rawBytes;

    public TsvetokExecutable(byte[] bytes) {
        _rawBytes = bytes;

        if (_rawBytes.length < 128) {
            byte[] fullExecutable = new byte[128];
            System.arraycopy(_rawBytes, 0, fullExecutable, 0, _rawBytes.length);
            _rawBytes = fullExecutable;
        }
    }

    public Instruction getAt(int index) {
        if (index >= _rawBytes.length) {
            return new HaltInstruction((byte) 0);
        }

        byte instruction = _rawBytes[index];

        if ((instruction & 0xf0) == 0) {
            return new NoOpInstruction(instruction);
        }

        return new MoveImmediateInstruction(instruction);
    }

    public boolean isValid() {
        byte[] header = new byte[6];
        System.arraycopy(_rawBytes, 0, header, 0, 6);

        if (!Arrays.equals(header, VALID_HEADER)) {
            return false;
        }

        return _rawBytes.length <= 128;
    }
}
