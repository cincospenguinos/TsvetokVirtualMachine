package usa.lafleur.cincospenguinos.model;

import usa.lafleur.cincospenguinos.model.instructions.HaltInstruction;
import usa.lafleur.cincospenguinos.model.instructions.Instruction;

import java.util.Arrays;

/**
 * Representation of a single TVM executable file.
 */
public class TsvetokExecutable {
    public static final byte[] VALID_HEADER = new byte[] { 0x54, 0x56, 0x4d, 97, 71, 12 };
    private static final int MAX_EXECUTABLE_SIZE = 0xffff;

    private byte[] _rawBytes;

    public TsvetokExecutable(byte[] bytes) {
        _rawBytes = bytes;

        if (_rawBytes.length < MAX_EXECUTABLE_SIZE) {
            byte[] fullExecutable = new byte[MAX_EXECUTABLE_SIZE];
            System.arraycopy(_rawBytes, 0, fullExecutable, 0, _rawBytes.length);
            _rawBytes = fullExecutable;
        }
    }

    public Instruction getAt(int index) {
        if (index >= _rawBytes.length) {
            return new HaltInstruction((byte) 0);
        }

        byte instruction = _rawBytes[index];

        return Instruction.instructionFor(instruction);
    }

    public boolean isValid() {
        byte[] header = new byte[6];
        System.arraycopy(_rawBytes, 0, header, 0, 6);

        if (!Arrays.equals(header, VALID_HEADER)) {
            return false;
        }

        return _rawBytes.length <= MAX_EXECUTABLE_SIZE;
    }
}
