package usa.lafleur.cincospenguinos.assembler;

public class FlagResolutionService {
    public byte flagBitsFor(String operation) {
        if (operation.contains("nens")) {
            return (byte) (operation.contains("ou") ? 0x8 : 0x0);
        }

        if (operation.contains("bouj")) {
            return (byte) (operation.contains("f") ? 0xc : 0x4);
        }

        return 0x0;
    }
}
