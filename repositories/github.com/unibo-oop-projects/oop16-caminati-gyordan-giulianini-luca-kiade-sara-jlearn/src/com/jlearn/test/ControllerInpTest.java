package com.jlearn.test;

import org.junit.Test;

import com.jlearn.controller.checker.ControllerInputCheck;
import com.jlearn.controller.checker.ControllerInputCheckImpl;
import com.jlearn.model.users.UserImpl;

/**
 * {@link ControllerInputCheck} tester.
 */
public class ControllerInpTest {

    /**
     * Test the method for input user.
     */
    // CHECKSTYLE:OFF
    @Test
    public void createAndTestParser() { // NOPMD
        final ControllerInputCheck cont = ControllerInputCheckImpl.getInstance();

        org.junit.Assert.assertFalse(cont.checkUserInput(new UserImpl("", "", "", 0, "", ""), null));
        org.junit.Assert.assertFalse(cont.checkUserInput(new UserImpl("", "", "", 5, "", ""), null));
        org.junit.Assert.assertFalse(cont.checkUserInput(new UserImpl("", "", "gd-fg", 5, "", ""), null));
        org.junit.Assert.assertFalse(cont.checkUserInput(new UserImpl("", "sdfsdf-", "dfsdf", 8, "", ""), null));
        org.junit.Assert.assertFalse(cont.checkUserInput(new UserImpl("asd-", "sdf", "sdf", 76, "", ""), null));
        org.junit.Assert.assertFalse(cont.checkUserInput(new UserImpl("", "", "", 1, "", ""), null));
        org.junit.Assert.assertFalse(cont.checkUserInput(new UserImpl("", "", "", 6666999, "", ""), null));
        org.junit.Assert.assertFalse(cont.checkUserInput(new UserImpl("", "---", "", 3, "", ""), null));
        org.junit.Assert.assertFalse(cont.checkUserInput(new UserImpl("", "", "", 0,
                "ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd", ""), null));
        org.junit.Assert.assertFalse(cont.checkUserInput(new UserImpl("", "", "", 4, "M,.", "asd"), null));
        org.junit.Assert.assertFalse(cont.checkUserInput(new UserImpl("", "", "", 6, "hkj.-", "545"), null));
        org.junit.Assert.assertFalse(cont.checkUserInput(new UserImpl("545,.,", "", "hj", 0, "", ""), null));
        org.junit.Assert.assertFalse(cont.checkUserInput(new UserImpl("", "asd", "", 9, "", ""), null));
        org.junit.Assert.assertFalse(cont.checkUserInput(new UserImpl("SDF", "ASDF", "", 0, "", ""), null));
        org.junit.Assert.assertTrue(cont.checkUserInput(new UserImpl("test", "tizio", "sc", 54, "dgdsg.@.com",
                "1234567890"), null));
        org.junit.Assert.assertTrue(cont.checkUserInput(new UserImpl("", "", "D8F", 3, "",
                ""), null));

    }

}
