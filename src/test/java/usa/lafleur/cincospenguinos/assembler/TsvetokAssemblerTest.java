package usa.lafleur.cincospenguinos.assembler;

import org.junit.Test;
import usa.lafleur.cincospenguinos.assembler.exceptions.LabelDoesNotExistException;
import usa.lafleur.cincospenguinos.machine.TsvetokExecutable;
import usa.lafleur.cincospenguinos.machine.instructions.TsvetokInstruction;

import java.util.List;

import static org.junit.Assert.*;

public class TsvetokAssemblerTest {
    private final TsvetokAssembler assembler = new TsvetokAssembler();

    @Test
    public void test_handlesRegisters() {
        TsvetokInstruction instruction = assembler.createInstruction("nensou $tnp0 $arj2");
        byte registers = instruction.toBytes().getB();
        assertEquals(0x60, registers & 0xf0);
        assertEquals(0xa, registers & 0x0f);
    }

    @Test
    public void test_handlesImmediates() {
        TsvetokInstruction instruction = assembler.createInstruction("adf 127");
        byte immediate = instruction.toBytes().getB();
        assertEquals(0x7f, immediate);
    }

    @Test
    public void test_handlesNoOp() {
        TsvetokInstruction instruction = assembler.createInstruction("noup");
        byte right = instruction.toBytes().getB();
        assertEquals(0x00, right);
    }

    @Test
    public void test_handlesCommentsInLine() {
        try {
            assembler.createInstruction("adf 1 #comment");
        } catch (RuntimeException e) {
            fail("Comments need to be handled");
        }
    }

    @Test
    public void test_assembleRespondsWithInstructions() {
        String sourceCode = "# okay we are going to make a program\n" +
                "boujf 127\n" +
                "adf 1";
        List<TsvetokInstruction> list = assembler.assemble(sourceCode).getInstructions();
        assertEquals(2, list.size());
    }

    @Test
    public void test_assembleHandlesLabels() {
        String sourceCode = ".main\n\tboujf -10\n.loop\n\tstoup";
        TsvetokExecutable executable = assembler.assemble(sourceCode);
        assertEquals(0, executable.getLabelSymbolTable().positionFor("main"));
        assertEquals(1, executable.getLabelSymbolTable().positionFor("loop"));
    }

    @Test(expected = LabelDoesNotExistException.class)
    public void test_throwsExceptionWhenLabelDoesNotExist() {
        String sourceCode = ".main\n\tboujf -10\n\tjnp mian";
        assembler.assemble(sourceCode);
    }
}