package usa.lafleur.cincospenguinos.assembler;

import usa.lafleur.cincospenguinos.model.instructions.OpCode;

import java.util.HashMap;
import java.util.Map;

public class InstructionMap {
    private Map<String, Byte> _opcodes;

    public InstructionMap() {
        _opcodes = new HashMap<>();

        _opcodes.put("noup", (byte) OpCode.NO_OP);
        _opcodes.put("sis", (byte) OpCode.SYSTEM_CALL);
        _opcodes.put("nens", (byte) OpCode.ACCESS_MEMORY);
        _opcodes.put("nensou", (byte) OpCode.ACCESS_MEMORY);
        _opcodes.put("bouj", (byte) OpCode.MOVE);
        _opcodes.put("ad", (byte) OpCode.ADD);
        _opcodes.put("nult", (byte) OpCode.MULTIPLY);
        _opcodes.put("dif", (byte) OpCode.DIVIDE);
        _opcodes.put("loujet", (byte) OpCode.LOGICAL_AND);
        _opcodes.put("loujour", (byte) OpCode.LOGICAL_OR);
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
