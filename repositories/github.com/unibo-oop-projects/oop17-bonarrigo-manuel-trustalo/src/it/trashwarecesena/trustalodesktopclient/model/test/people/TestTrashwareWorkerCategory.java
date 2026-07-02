package it.trashwarecesena.trustalodesktopclient.model.test.people;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.trashwarecesena.trustalodesktopclient.model.people.TrashwareWorkerCategory;
import it.trashwarecesena.trustalodesktopclient.model.people.concreteness.TrashwareWorkerCategoryImpl;
import it.trashwarecesena.trustalodesktopclient.model.test.TestConstants;

/**
 * A test over the construction and equality behaviours of the
 * {@link TrashwareWorkerCategoryImpl} implementation.
 * 
 * @author Manuel Bonarrigo
 *
 */
@RunWith(JUnitPlatform.class)
@SuppressFBWarnings("NP_NULL_PARAM_DEREF_NONVIRTUAL")
public class TestTrashwareWorkerCategory {

    private final TrashwareWorkerCategory trashwareWorkerCategory;
    private final TrashwareWorkerCategory differentTrashwareWorkerCategory;
    private final TrashwareWorkerCategory sameTrashwareWorkerCategory;

    private final Executable nullParameter = () -> {
        new TrashwareWorkerCategoryImpl(null);
    };
    private final Executable emptyParameter = () -> {
        new TrashwareWorkerCategoryImpl(TestConstants.EMPTY_STRING);
    };
    private final Executable singleEmptyParameter = () -> {
        new TrashwareWorkerCategoryImpl(TestConstants.SINGLE_SPACE_STRING);
    };
    private final Executable multiEmptyParameter = () -> {
        new TrashwareWorkerCategoryImpl(TestConstants.MULTI_SPACE_STRING);
    };

    /**
     * Set up all the objects needed by the test.
     */
    public TestTrashwareWorkerCategory() {
        this.trashwareWorkerCategory = new TrashwareWorkerCategoryImpl(TestConstants.A_STRING);
        this.differentTrashwareWorkerCategory = new TrashwareWorkerCategoryImpl(TestConstants.A_DIFFERENT_STRING);
        this.sameTrashwareWorkerCategory = new TrashwareWorkerCategoryImpl(TestConstants.THE_SAME_STRING);
    }

    /**
     * Tests over the constructor(s) ability to pursue only the legal initialization.
     */
    @Test
    public void constructionTest() {
        assertTrue(trashwareWorkerCategory.getName().equals(TestConstants.A_STRING));

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
        assertTrue(trashwareWorkerCategory.equals(sameTrashwareWorkerCategory));
        assertTrue(sameTrashwareWorkerCategory.equals(trashwareWorkerCategory));
        assertFalse(trashwareWorkerCategory.equals(differentTrashwareWorkerCategory));
        assertFalse(sameTrashwareWorkerCategory.equals(differentTrashwareWorkerCategory));
    }
}
