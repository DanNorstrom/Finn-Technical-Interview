import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/* Author: Dan Norström
 * This project uses a Deck(.java) of Cards(.java) to play a Game(.java) of BlackJack with x Players(.java) (Sam and a Dealer, usually)
 * 
 * <Small Memos>
 * 1. Instantiate deck with overloading (polymorphic?)
 * 2. Instantiate player count
 * 
 * 
 * <Challanges>
 * 1. final doesn't make objects immutable, using private hinders access from anythin except the class methods
 *    but the data isnt immutable.
 *    TODO:creating immutability or adding deep cloning to class methods solves this.
 *    
 * <Junit Tests>
 * 1. new deck with known cards, ensure cards are given correctly
 * 2. card tests
 * 3. deck tests
 * 4. player tests
 * 5. game tests
 * 
 * 
 * <Extra implementation>
 * 1. Allow multiple players
 * 	  - if dealer loses, all players below 22 wins.
 *    - 
 * 2. Allow Player, Card and Deck to hold no information about Blackjack
 * 	  So that it can be reused for other card games if we change the game driver.
 * 	  NOTE: some games will require more class methods. We cannot use Boolean flags in Player.
 * 
 * >>. Aces can be 1 and 11
*/


public class Game {
	
	// Deck of Cards
	private final Deck doc;
	private final ArrayList<Player> players;
	
	// no argument constructor
	public Game(String[] p) {
		this.doc = new Deck();
		this.players = new ArrayList<>();
		for(String s : p) {
			this.players.add(new Player(s));
		}
	}
	
	public Game(String[] p, String d) {
		doc = new Deck(d);
		this.players = new ArrayList<>();
		for(String s : p) {
			this.players.add(new Player(s));
		}
	}
	
	// Game Driver
	public void start2ManBlackJack() {
		// prepare the board
		doc.shuffle();
	
		// 2 cards per player
		for(int i=0; i<2; i++) {
			for(Player p: players) {
				p.draw(doc.nextCard());
		}}
		
		// identify dealer (has seperate rules)
		Player dealer = null;
		Player sam = null;
		for(Player p: players) {
			if (p.toString() == "Sam") sam = p;
			if (p.toString() == "Dealer") dealer = p;
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
			
			else if(dealer.sum() < sam.sum()) dealer.draw(doc.nextCard()); //( (sam.sum() >= 17) && () )
			
			else {
				conclusion = true;
				winner = dealer;
			}
			
		}

		
		System.out.println(winner.toString());
		for(Player p: players) {
			System.out.println(p.ShowHand());
		}
			
	}
	
	
	// Initialize a Game of 21
	public static void main(String[] args) {
		Game game;
		String[] players = {"Sam","Dealer"};
		
		// populate the game
		switch (args.length) {
		
		// empty input
		case 0: game = new Game(players);
				game.start2ManBlackJack();
				break;
				
		// single input		
		case 1: game = new Game(players, args[0]);
				game.start2ManBlackJack();
				break;
				
		// invalid inputs
		default: System.out.println("Requires exaclty one file path or empty input");
				 System.exit(1);
		}
		
		
	}
}



//public void startBlackJack() {
//	// prepare the board
//	doc.shuffle();
//
//	// 2 cards per player
//	for(int i=0; i<2; i++) {
//		for(Player p: players) {
//			p.draw(doc.nextCard());
//	}}
//	
//	// check for blackJack
//	ArrayList<Player> twoAce = new ArrayList<>();
//	ArrayList<Player> blackJack = new ArrayList<>();
//	for(Player p: players) {
//		if (p.sum() == 21) blackJack.add(p);
//		if (p.sum() == 22) twoAce.add(p);
//	}
//	
//	// declare win by blackJack
//	boolean dealer21 = false;
//	ArrayList<Player> winner = new ArrayList<>();
//	
//	// Sole winner (dealer dosent stop the game)
//	if (blackJack.size() == 1) {
//		Player w = blackJack.get(0);
//		if (w.toString() == "Dealer") dealer21 = true;
//		winner.add(w);
//	}
//	
//	// game end, at least one blackjack player isnt the dealer
//	else if (blackJack.size() >= 2) {
//		// if dealer & x players has blackJack, x players wins,
//		Iterator<Player> itr = blackJack.iterator();
//		while(itr.hasNext()) {
//			Player p = itr.next();
//			if(p.toString() == "Dealer") itr.remove();
//			else winner.add(p);
//		}
//	}
//	
//	
//	
//	
//	// No intitial conclusion:
//	if (dealer21 || (winner.size() <= 0)) {
//		
//	}
//	
//	// player 22, player loses
//	// dealer 22, dealer loses
//	
//	
//	// player + dealer 22, dealer wins
//	
//	
//	// players draw if sum less then 17
//	
//	
//	
//}
