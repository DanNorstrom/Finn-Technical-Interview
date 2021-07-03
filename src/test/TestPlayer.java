package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.*;

class TestPlayer {

	private Deck doc;
	private Deck full_doc;
	private Player player;

	@BeforeEach
	void setUp() throws Exception {
		doc = new Deck("test.txt");
		full_doc = new Deck();
		player = new Player("FinnTester");
	}
	
	
	@Test
	void testDraw() {
		Card c = doc.nextCard();
		player.draw(c);
		assertEquals(c.toString(), player.ShowHand() );
	}

	@Test
	void testHandSum() {
		// acertain the value of the players hand is equal to the cards drawn.
		int sum = 0;
		for(int i=0; i<5; i++) {
			Card c = full_doc.nextCard();
			player.draw(c);
			sum += c.value();
		}
		
		assertEquals(sum, player.sum());
	}


	@Test
	void testToString() {
		assertEquals("FinnTester", player+"");
	}

}
