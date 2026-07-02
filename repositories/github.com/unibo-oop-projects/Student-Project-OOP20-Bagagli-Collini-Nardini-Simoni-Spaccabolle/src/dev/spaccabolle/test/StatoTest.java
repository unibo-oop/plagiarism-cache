package dev.spaccabolle.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dev.spaccabolle.states.State;

class StatoTest {
	State stato = null;

	@Test
	void testSetStateAndGetState() {
		State.setState(stato);
		assertEquals(stato, State.getState());
	}

}
