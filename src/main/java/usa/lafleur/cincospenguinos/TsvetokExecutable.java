package usa.lafleur.cincospenguinos;

import java.util.Arrays;

public class TsvetokExecutable {
    public static final byte[] VALID_HEADER = new byte[] { 0x54, 0x56, 0x4d, 97, 71, 12 };

    private byte[] _rawBytes;

    public TsvetokExecutable(byte[] bytes) {
        _rawBytes = bytes;
    }

    public boolean isValid() {
        byte[] header = new byte[6];
        System.arraycopy(_rawBytes, 0, header, 0, 6);

        return Arrays.equals(header, VALID_HEADER);
    }
}
