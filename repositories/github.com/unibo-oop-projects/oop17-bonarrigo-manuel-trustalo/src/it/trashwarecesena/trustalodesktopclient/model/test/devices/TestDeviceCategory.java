package it.trashwarecesena.trustalodesktopclient.model.test.devices;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.trashwarecesena.trustalodesktopclient.model.devices.DeviceCategory;
import it.trashwarecesena.trustalodesktopclient.model.devices.concreteness.DeviceCategoryImpl;
import it.trashwarecesena.trustalodesktopclient.model.test.TestConstants;

/**
 * A test over the construction and equality behaviours of the
 * {@link DeviceCategoryImpl} implementation.
 * 
 * @author Manuel Bonarrigo
 *
 */
@RunWith(JUnitPlatform.class)
@SuppressFBWarnings("NP_NULL_PARAM_DEREF_NONVIRTUAL")
public class TestDeviceCategory {

    private final DeviceCategory category;
    private final DeviceCategory differentCategory;
    private final DeviceCategory sameCategory;

    private final Executable nullFirstParameter = () -> {
        new DeviceCategoryImpl(null, TestConstants.A_STRING, true);
    };

    private final Executable nullSecondParameter = () -> {
        new DeviceCategoryImpl(TestConstants.A_STRING, null, true);
    };

    private final Executable emptyFirstParameter = () -> {
        new DeviceCategoryImpl(TestConstants.EMPTY_STRING, TestConstants.A_STRING, true);
    };

    private final Executable singleEmptyFirstParameter = () -> {
        new DeviceCategoryImpl(TestConstants.SINGLE_SPACE_STRING, TestConstants.A_STRING, true);
    };

    private final Executable multiEmptyFirstParameter = () -> {
        new DeviceCategoryImpl(TestConstants.MULTI_SPACE_STRING, TestConstants.A_STRING, true);
    };

    private final Executable emptySecondParameter = () -> {
        new DeviceCategoryImpl(TestConstants.A_STRING, TestConstants.SINGLE_SPACE_STRING, true);
    };

    private final Executable singleEmptySecondParameter = () -> {
        new DeviceCategoryImpl(TestConstants.A_STRING, TestConstants.EMPTY_STRING, true);
    };

    private final Executable multiEmptySecondParameter = () -> {
        new DeviceCategoryImpl(TestConstants.A_STRING, TestConstants.MULTI_SPACE_STRING, true);
    };

    /**
     * Set up all the objects needed by the test.
     */
    public TestDeviceCategory() {
        this.category = new DeviceCategoryImpl(TestConstants.A_STRING, TestConstants.A_STRING, true);
        this.differentCategory = 
                new DeviceCategoryImpl(TestConstants.A_DIFFERENT_STRING, TestConstants.A_DIFFERENT_STRING, true);
        this.sameCategory = new DeviceCategoryImpl(TestConstants.THE_SAME_STRING, TestConstants.THE_SAME_STRING, true);
    }

    /**
     * Tests over the constructor(s) ability to pursue only the legal initialization.
     */
    @Test
    public void constructionTest() {
        assertTrue(category.getAcronym().equals(TestConstants.A_STRING.toUpperCase(TestConstants.IT)));
        assertTrue(category.getName().equals(TestConstants.A_STRING));
        assertTrue(category.isMultipleCompoundAllowed());

        assertThrows(NullPointerException.class, nullFirstParameter);
        assertThrows(NullPointerException.class, nullSecondParameter);
        assertThrows(IllegalArgumentException.class, emptyFirstParameter);
        assertThrows(IllegalArgumentException.class, emptySecondParameter);
        assertThrows(IllegalArgumentException.class, singleEmptyFirstParameter);
        assertThrows(IllegalArgumentException.class, singleEmptySecondParameter);
        assertThrows(IllegalArgumentException.class, multiEmptyFirstParameter);
        assertThrows(IllegalArgumentException.class, multiEmptySecondParameter);
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
