package usa.lafleur.cincospenguinos.assembler;

import usa.lafleur.cincospenguinos.model.OpCodes;

import java.util.HashMap;
import java.util.Map;

public class OpCodeResolutionService {
    private final Map<String, Byte> _opCodeMap;

    public OpCodeResolutionService() {
        _opCodeMap = new HashMap<>();
        _opCodeMap.put("noup", (byte) OpCodes.NO_OP);
        _opCodeMap.put("stoup", (byte) OpCodes.HALT);
    }

    public byte codeFor(String operation) {
        return (_opCodeMap.get(operation) << 4);
    }
}
