package usa.lafleur.cincospenguinos;

import usa.lafleur.cincospenguinos.model.TsvetokExecutable;
import usa.lafleur.cincospenguinos.model.TsvetokMachine;
import usa.lafleur.cincospenguinos.model.instructions.OpCode;

import java.util.Scanner;

/**
 * Assembler for Tsvetok Virtual Machine. Converts human-readable assembly into
 * bytes.
 */
public class TsvetokAssembler {
    private static final String WHITESPACE_PATTERN = "\\s+";
    private static final InstructionMap INSTRUCTION_MAP = new InstructionMap();

    private String _rawInput;
    private byte[] _bytes;

    public TsvetokAssembler(String input) {
        _rawInput = input;
    }

    public TsvetokAssembler assemble() {
        _bytes = new byte[128];
        System.arraycopy(TsvetokExecutable.VALID_HEADER, 0, _bytes, 0, 6);
        int index = TsvetokMachine.EXECUTABLE_START_INDEX;
        Scanner scanner = new Scanner(_rawInput);

        while(scanner.hasNextLine()) {
            String[] pieces = scanner.nextLine().split(WHITESPACE_PATTERN);
            byte opcode = INSTRUCTION_MAP.opcodeFor(pieces[0]);
            byte instruction = (byte) (opcode << 4);

            switch(opcode) {
                case OpCode.MOVE_IMMEDIATE:
                    byte immediateValue = Byte.parseByte(pieces[1]);
                    instruction |= immediateValue;
                    break;
                case OpCode.NO_OP:
                    break;
                case OpCode.STORE:
                    byte address = Byte.parseByte(pieces[1].replaceAll("\\#", ""));
                    instruction |= address;
                    break;
                case OpCode.LOAD:
                    byte register = Byte.parseByte(pieces[1]);
                    instruction |= (register << 2);
                    break;
            }

            _bytes[index] = instruction;
        }

        scanner.close();

        return this;
    }

    public byte[] toByteArray() {
        return _bytes;
    }

    public TsvetokExecutable toExecutable() {
        return new TsvetokExecutable(_bytes);
    }
}
