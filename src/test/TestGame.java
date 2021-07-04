package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.*;

class TestGame {
	
	
	
	public class Game_stub {
		
		// Deck of Cards
		private final Deck doc;
		private final ArrayList<Player> players;
		
		// no argument constructor
		public Game_stub(String[] p) {
			this.doc = new Deck();
			// shuffle random deck
			this.doc.shuffle();
			this.players = new ArrayList<>();
			for(String s : p) {
				this.players.add(new Player(s));
			}
		}
		
		public Game_stub(String[] p, String d) {
			this.doc = new Deck(d);
			this.players = new ArrayList<>();
			for(String s : p) {
				this.players.add(new Player(s));
			}
		}
		
		// Game Driver
		public Player start2ManBlackJack_stub() {
			// prepare the board		
			
			// identify dealer (has separate rules)
			Player dealer = null;
			Player sam = null;
			for(Player p: players) {
				if (p.toString() == "FinnTester") sam = p;
				if (p.toString() == "Dealer") dealer = p;
			}
			
			// 2 cards per player
			for(int i=0; i<2; i++) {
				sam.draw(doc.nextCard());
				dealer.draw(doc.nextCard());
			}
			
			//end game logic
			boolean conclusion = false;
			Player winner = null;
			
			// game loop
			while(!conclusion) {
				// win condition block
				
				// sam blackjack, sam wins (equal outcome if both blackjack)
				if ( sam.sum() == 21 ) {
					conclusion = true;
					winner = sam;
				}
				
				// sam goes bust, dealer wins (equal outcome if both go bust)
				else if ( sam.sum() >= 22 )  {
					conclusion = true;
					winner = dealer;
				}
				
				// dealer bust, sam dosen't, sam wins
				else if (dealer.sum() >= 22)  {  	//( ( sam.sum() < 21 ) && () )
					conclusion = true;
					winner = sam;
				}
				
				// action block
				else if(sam.sum() < 17) sam.draw(doc.nextCard());
				
				else if(dealer.sum() <= sam.sum()) dealer.draw(doc.nextCard()); //( (sam.sum() >= 17) && () )
				
				else {
					conclusion = true;
					winner = dealer;
				}
				
			}

			// game concluded, report winner in line with requirement
			return winner;		
		}
	}
		
	
	@Test
	void testStart2ManBlackJack() {
		
		// stub initialize
		String[] p = {"FinnTester","Dealer"};
		
		Game_stub g_DBJ = new Game_stub(p, "test-dealer-blackjack.txt");
		Game_stub g_PBJ = new Game_stub(p, "test-player-blackjack.txt");
		Game_stub g_DB = new Game_stub(p, "test-dealer-bust.txt");
		Game_stub g_PB = new Game_stub(p, "test-player-bust.txt");

		// test edge cases (double bust and double blackjack lead to the same conclusion)
		assertEquals(g_DBJ.start2ManBlackJack_stub()+"", "Dealer");
		assertEquals(g_PBJ.start2ManBlackJack_stub()+"", "FinnTester");
		assertEquals(g_DB.start2ManBlackJack_stub()+"", "FinnTester");
		assertEquals(g_PB.start2ManBlackJack_stub()+"", "Dealer");
		
	}

}
