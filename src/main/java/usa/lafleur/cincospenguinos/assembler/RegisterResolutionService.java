package usa.lafleur.cincospenguinos.assembler;

public class RegisterResolutionService {
    public RegisterResolutionService() {}


    public byte resolve(String registerString) {
        String register = registerString.replace("$", "");

        if (register.equals("ak")) {
            return 0x0;
        }

        if (register.contains("tnp")) {
            byte registerByte = Byte.parseByte(register.replace("tnp", ""));
            return (byte) (registerByte + 0x6);
        }

        return Byte.parseByte(register.replace("rej", ""));
    }
}
