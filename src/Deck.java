import java.util.Collections;
import java.util.Stack;

public class Deck {
	
	// Deck of Cards
	private Stack<Card> doc;

	public Deck() {
		doc = new Stack<>();
		
		String[] suits = {"S","D","H","C"};
		String[] values = {"1","2","3","4","5","6","7","8","9","10","J","Q","K","A"};
		
		for (String s : suits) {
			for (String v : values) {
				try {
					doc.push(new Card(s+v));
				}
				catch (Exception e) {
					System.out.println(e.getMessage() +"/n Deck could not be created"
							+ " because of one or more faulty card names");
				}
		}}
	}
	
	public Deck(String path) {
		doc = new Stack<>();
		// read from file
		
		// read from current directory+path
		
		// or read from absolute path
		
	}
	
	public void shuffle() {
		Collections.shuffle(this.doc);
	}
	
	
	public Card nextCard() {
		return doc.pop();
	}
	
}
