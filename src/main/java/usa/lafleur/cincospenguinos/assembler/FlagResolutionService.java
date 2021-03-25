package usa.lafleur.cincospenguinos.assembler;

public class FlagResolutionService {
    public byte flagBitsFor(String operation) {
        if (operation.equals("nens")) {
            return 0x0;
        }

        if (operation.equals("nensou")) {
            return 0b00001000;
        }

        return 0x0;
    }
}
