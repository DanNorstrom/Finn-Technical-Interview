package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Deck;

class TestDeck {
	
	private Deck doc;
	private Deck full_doc;

	@BeforeEach
	void setUp() throws Exception {
		doc = new Deck("test.txt");
		full_doc = new Deck();
	}
	
	@Test
	void testDeck() {
		// assert Order
		assertEquals("CA", doc.nextCard().toString());
		assertEquals("D5", doc.nextCard().toString());
		assertEquals("H9", doc.nextCard().toString());
		assertEquals("HQ", doc.nextCard().toString());
		assertEquals("S8", doc.nextCard().toString());	
	}


	@Test
	void testCardTotalValue() {
		
		//ascertain the total point value is retained.
		boolean concluded = false;
		int sum = 0;
		while(!concluded) {
			try {
				sum += full_doc.nextCard().value();
			}
			catch(Exception e) {
				concluded = true;
			}
		}
		
		int sum_real = 0;
		int[] arr = {1,2,3,4,5,6,7,8,9,10,10,10,10,11};
		for (int j:  arr) {
			sum_real += j*4;
		}

		assertEquals(sum_real, sum);

	}
}
