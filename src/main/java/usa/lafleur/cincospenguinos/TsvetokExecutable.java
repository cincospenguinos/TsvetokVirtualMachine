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
        byte instruction = _rawBytes[index];
        return new Instruction(instruction);
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
