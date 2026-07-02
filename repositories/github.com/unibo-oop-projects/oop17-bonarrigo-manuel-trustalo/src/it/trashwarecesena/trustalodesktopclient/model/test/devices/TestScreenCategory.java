package it.trashwarecesena.trustalodesktopclient.model.test.devices;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.trashwarecesena.trustalodesktopclient.model.devices.ScreenCategory;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.ScreenCategoryImpl;
import it.trashwarecesena.trustalodesktopclient.model.test.TestConstants;

/**
 * A test over the construction and equality behaviours of the
 * {@link ScreenCategoryImpl} implementation.
 * 
 * @author Manuel Bonarrigo
 *
 */
@RunWith(JUnitPlatform.class)
@SuppressFBWarnings("NP_NULL_PARAM_DEREF_NONVIRTUAL")
public class TestScreenCategory {

    private final ScreenCategory category;
    private final ScreenCategory differentCategory;
    private final ScreenCategory sameCategory;

    private final Executable nullParameter = () -> {
        new ScreenCategoryImpl(null);
    };
    private final Executable emptyParameter = () -> {
        new ScreenCategoryImpl(TestConstants.EMPTY_STRING);
    };
    private final Executable singleEmptyParameter = () -> {
        new ScreenCategoryImpl(TestConstants.SINGLE_SPACE_STRING);
    };
    private final Executable multiEmptyParameter = () -> {
        new ScreenCategoryImpl(TestConstants.MULTI_SPACE_STRING);
    };

    /**
     * Set up all the objects needed by the test.
     */
    public TestScreenCategory() {
        this.category = new ScreenCategoryImpl(TestConstants.A_STRING);
        this.differentCategory = new ScreenCategoryImpl(TestConstants.A_DIFFERENT_STRING);
        this.sameCategory = new ScreenCategoryImpl(TestConstants.THE_SAME_STRING);
    }

    /**
     * Tests over the constructor(s) ability to pursue only the legal initialization.
     */
    @Test
    public void constructionTest() {
        assertTrue(category.getName().equals(TestConstants.A_STRING));

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
        assertTrue(category.equals(sameCategory));
        assertTrue(sameCategory.equals(category));
        assertFalse(category.equals(differentCategory));
        assertFalse(sameCategory.equals(differentCategory));
    }
}
