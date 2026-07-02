package it.trashwarecesena.trustalodesktopclient.model.test.otherdevices;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.trashwarecesena.trustalodesktopclient.model.otherdevices.InstructionSet;
import it.trashwarecesena.trustalodesktopclient.model.otherdevices.concreteness.InstructionSetImpl;
import it.trashwarecesena.trustalodesktopclient.model.test.TestConstants;

/**
 * A test over the construction and equality behaviours of the
 * {@link InstructionSetImpl} implementation.
 * 
 * @author Manuel Bonarrigo
 *
 */
@RunWith(JUnitPlatform.class)
@SuppressFBWarnings("NP_NULL_PARAM_DEREF_NONVIRTUAL")
public class TestInstructionSet {

    private final InstructionSet instructionSets;
    private final InstructionSet differentInstructionSet;
    private final InstructionSet sameInstructionSet;

    private final Executable nullParameter = () -> {
        new InstructionSetImpl(null);
    };
    private final Executable emptyParameter = () -> {
        new InstructionSetImpl(TestConstants.EMPTY_STRING);
    };
    private final Executable singleEmptyParameter = () -> {
        new InstructionSetImpl(TestConstants.SINGLE_SPACE_STRING);
    };
    private final Executable multiEmptyParameter = () -> {
        new InstructionSetImpl(TestConstants.MULTI_SPACE_STRING);
    };

    /**
     * Set up all the objects needed by the test.
     */
    public TestInstructionSet() {
        this.instructionSets = new InstructionSetImpl(TestConstants.A_STRING);
        this.differentInstructionSet = new InstructionSetImpl(TestConstants.A_DIFFERENT_STRING);
        this.sameInstructionSet = new InstructionSetImpl(TestConstants.THE_SAME_STRING);
    }

    /**
     * Tests over the constructor(s) ability to pursue only the legal initialization.
     */
    @Test
    public void constructionTest() {
        assertTrue(instructionSets.getName().equals(TestConstants.A_STRING));

        assertThrows(NullPointerException.class, nullParameter);
        assertThrows(IllegalArgumentException.class, emptyParameter);
        assertThrows(IllegalArgumentException.class, singleEmptyParameter);
        assertThrows(IllegalArgumentException.class, multiEmptyParameter);
    }

    /**
     * Testing of the equals() interactions.
     */
    @Test
    public void equalityTest() {
        assertTrue(instructionSets.equals(sameInstructionSet));
        assertTrue(sameInstructionSet.equals(instructionSets));
        assertFalse(instructionSets.equals(differentInstructionSet));
        assertFalse(sameInstructionSet.equals(differentInstructionSet));
    }
}
