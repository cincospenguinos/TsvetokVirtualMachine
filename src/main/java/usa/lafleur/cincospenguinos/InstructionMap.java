package usa.lafleur.cincospenguinos;

import usa.lafleur.cincospenguinos.model.instructions.OpCode;

import java.util.HashMap;
import java.util.Map;

public class InstructionMap {
    private Map<String, Byte> _opcodes;

    public InstructionMap() {
        _opcodes = new HashMap<>();

        _opcodes.put("noup", (byte) OpCode.NO_OP);
        _opcodes.put("ld", (byte) OpCode.LOAD);
        _opcodes.put("stou", (byte) OpCode.STORE);
        _opcodes.put("joump", (byte) OpCode.JUMP);
        _opcodes.put("bouj", (byte) OpCode.MOVE_IMMEDIATE);
        _opcodes.put("ad", (byte) OpCode.ADD);
        _opcodes.put("nult", (byte) OpCode.MULTIPLY);
        _opcodes.put("dif", (byte) OpCode.DIVIDE);
        _opcodes.put("et", (byte) OpCode.BITWISE_AND);
        _opcodes.put("our", (byte) OpCode.BITWISE_OR);
        _opcodes.put("toug", (byte) OpCode.TOGGLE_SIGN);
        _opcodes.put("sis", (byte) OpCode.SYSTEM_CALL);
        _opcodes.put("stoup", (byte) OpCode.HALT);
    }

    public byte opcodeFor(String operation) {
        Byte opcode = _opcodes.get(operation);

        if (opcode == null) {
            throw new RuntimeException("\"" + operation + "\" is not a valid operation");
        }

        return opcode;
    }
}
