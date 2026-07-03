package com.jlearn.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.jlearn.model.users.User;
import com.jlearn.model.users.UserImpl;

/**
 *
 * A users management tester.
 *
 */
public class TestUsers {

    /**
     *
     * Constructor and getters test.
     *
     */

    @Test
    public void test() {
        final int testAge = 25;
        final User user = new UserImpl("Mario", "Rossi", "Marrossi", testAge, "supermario01@gmail.com", "0547324578");
        assertEquals(user.getTel(), "0547324578");
        assertEquals(user.getEmail(), "supermario01@gmail.com");
        assertEquals(user.getAge(), testAge);
        assertEquals(user.getNickname(), "Marrossi");
        assertEquals(user.getSurname(), "Rossi");
        assertEquals(user.getName(), "Mario");

    }

}
