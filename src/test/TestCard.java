package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Card;

class TestCard {
	

	@Test
	void testCard1() {
		// card should be invalid
		assertThrows(IllegalArgumentException.class, () -> new Card("invalidCard"));

	}
	
	@Test
	void testCard2() {
		// card should be valid
		assertTrue(new Card("CQ") instanceof Card);
	}
	
	@Test
	void testValue1() {
		// value should be valid
		Card t = new Card("CQ");
		assertEquals(10, t.value());
	}

	@Test
	void testToString() {
		Card t = new Card("DK");
		assertEquals("DK",t.toString());
	}

}
