package usa.lafleur.cincospenguinos.assembler;

public class RegisterResolutionService {
    public RegisterResolutionService() {}


    public byte resolve(String registerString) {
        String register = registerString.replace("$", "");

        if (register.equals("ak")) {
            return 0x0;
        }

        return Byte.parseByte(register.replace("rej", ""));
    }
}
