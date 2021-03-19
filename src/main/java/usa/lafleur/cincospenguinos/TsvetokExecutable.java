package usa.lafleur.cincospenguinos;

public class TsvetokExecutable {
    public static final byte TSVETOK_T = 0x54;
    public static final byte TSVETOK_V = 0x56;
    public static final byte TSVETOK_M = 0x4d;

    private byte[] _rawBytes;

    public TsvetokExecutable(byte[] bytes) {
        _rawBytes = bytes;
    }

    public boolean isValid() {
        if (_rawBytes[0] != TSVETOK_T) {
            return false;
        }

        if (_rawBytes[1] != TSVETOK_V) {
            return false;
        }

        if (_rawBytes[2] != TSVETOK_M) {
            return false;
        }

        if (_rawBytes[3] != 97) {
            return false;
        }

        if (_rawBytes[4] != 71) {
            return false;
        }

        if (_rawBytes[5] != 12) {
            return false;
        }

        return true;
    }
}
