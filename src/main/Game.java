/* Author: Dan Norström
 * This project uses a Deck(.java) of Cards(.java) to play a Game(.java) of BlackJack with x Players(.java) (Sam and a Dealer, usually)
 * 
 * <Small Memos>
 * 1. Instantiate deck with overloading (polymorphic?)
 * 2. Instantiate player count
 * 
 * <Requirement Ambiguity>
 * 1. "each player is given two cards from the top of a <shuffled> deck of cards."
 *    - for the requirement of:
 *    
 *    "When supplied with the following cardlist:
 *	  CA, D5, H9, HQ, S8
 *	  The output should look like:
 *    sam
 *	  sam: CA, H9
 *	  dealer: D5, HQ, S8"
 *	  
 *    Conclusion: to always be true, the input using a spesific set of cards cannot be shuffled
 * 
 * <Challanges>
 * 1. final doesn't make objects immutable in Java, using private hinders access from outside the class,
 *    but the data isnt immutable.
 *    TODO:creating immutability by adding deep cloning to class access methods solves this.
 *    
 * <Junit Tests>
 * 1. new deck with known cards, ensure cards are given correctly
 * 2. card tests
 * 3. deck tests
 * 4. player tests
 * 5. game tests
 * 
 * 
 * <Self-imposed code regulation>
 * 1. SOLID (O): Limit Player, Card and Deck to hold no information about Blackjack
 * 	  MOTIVATION: So that it can be reused for other card games if we change the game driver.
 * 	  NOTE: some games will require more class methods.
 * 			avoid boolean flags in players or make abstract player class.
 * 2. Classes requires no knowledge of each other (Except for Game driver)
*/
package main;

import java.util.ArrayList;

public class Game {
	
	// Deck of Cards
	private final Deck doc;
	private final ArrayList<Player> players;
	
	// no argument constructor
	public Game(String[] p) {
		this.doc = new Deck();
		this.doc.shuffle();
		
		this.players = new ArrayList<>();
		for(String s : p) {
			this.players.add(new Player(s));
		}
	}
	
	public Game(String[] p, String d) {
		this.doc = new Deck(d);
		
		this.players = new ArrayList<>();
		for(String s : p) {
			this.players.add(new Player(s));
		}
	}
	
	// Game Driver
	public void start2ManBlackJack() {
		// prepare the board		
		
		// identify players
		Player dealer = null;
		Player sam = null;
		for(Player p: players) {
			if (p.toString() == "Sam") sam = p;
			if (p.toString() == "Dealer") dealer = p;
		}
		
		// 2 cards per player
		for(int i=0; i<2; i++) {
			sam.draw(this.doc.nextCard());
			dealer.draw(this.doc.nextCard());
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
			
			// dealer blackjack, sam doesn't (equal outcome if both blackjack)
			else if ( dealer.sum() == 21 ) {
				conclusion = true;
				winner = dealer;
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
		System.out.println(winner.toString());
		for(Player p: players) {
			System.out.println(p +": "+p.ShowHand());
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
		default: System.out.println("Requires exactly one file path or empty input");
				 System.exit(1);
		}
		
		
	}
}

//###################################################################################

/*
 *  Began working on 2-7 man Blackjack, but the rules for aces
 *  as well as the win conditions requires a pot and betting system
 *  to take into account the win ratio if multiple players blackjack.
 *  - it goes against some of the requirements in the brief, hence
 *  we keep developing a 2man Blackjack.
 */


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
//	// No initial conclusion:
//	if (dealer21 || (winner.size() <= 0)) {
//		
//	}
//	
//	// player 22, player loses
//	// dealer 22, dealer loses
//	// player + dealer 22, dealer wins
//	// players draw if sum less then 17
//	
//	
//	
//}
