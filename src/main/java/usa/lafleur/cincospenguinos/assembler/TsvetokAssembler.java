package usa.lafleur.cincospenguinos.assembler;

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
        InstructionAssembly instructionAssembly = new InstructionAssembly();

        while(scanner.hasNextLine()) {
            String[] pieces = scanner.nextLine().split(WHITESPACE_PATTERN);
            byte opcode = INSTRUCTION_MAP.opcodeFor(pieces[0]);
            instructionAssembly.setOpCode(opcode);

            switch(opcode) {
                case OpCode.SYSTEM_CALL:
                    instructionAssembly.setImmediate(pieces[1]);
                    break;
                case OpCode.DIVIDE:
                    instructionAssembly.setFirstRegister(pieces[1]);
                    instructionAssembly.setSecondRegister(pieces[2]);
                    break;
            }

            _bytes[index] = instructionAssembly.build();
            instructionAssembly.clear();
            index += 1;
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
