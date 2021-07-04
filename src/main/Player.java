package main;

import java.util.ArrayList;

public class Player {

	private String name;
	private ArrayList<Card> hand;
	
	public Player(String n) {
		this.name = n;
		// players always starts with an empty hand
		this.hand = new ArrayList<>();
	}
		
	// add card to the players hand
	public void draw(Card d) {
		this.hand.add(d);
	}
	
	// sum of all cards on the players hand
	public int sum() {
		// empty hand
		if (this.hand.size() <= 0) {
			return 0;
		}
		else {
			//this.hand.stream().mapToInt(c -> c.value()).sum();
			
			int sum = 0;
			for(Card c: this.hand){
				sum += c.value();
			}
			return sum;
		}
	}
	
	// displays the player's current hand
	public String ShowHand() {
		if (this.hand.size() <= 0) {
			return "<empty>";
		}
		else{
			String sum = "";
			for(Card c: this.hand){
				sum += c+", ";
			}
			// remove last separators
			return sum.substring(0, sum.length()-2);
		}
	}
	
	@Override
	public String toString() {
		return name;
	}
}
