package main;

import java.io.File;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class Deck {
	
	// Deck of Cards
	private final Stack<Card> doc;

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
					System.out.println(e.getMessage() +"Deck could not be created"
							+ " because of one or more faulty card names\n");
				}
		}}
	}
	
	// read deck from File at path
	public Deck(String path) {
		doc = new Stack<>();
		Scanner sc = null;

		// expects absolute
		try {
			sc = new Scanner(new File(path));
		}
		catch(Exception e) {
			// if not absolute, try local (helpful if used without a IDE Path)
			try{
				sc = new Scanner( new File(System.getProperty("user.dir")+ "\\res\\" + path) );
			}
			catch(Exception ee) {
				System.out.println(System.getProperty("user.dir"));
				System.out.println("Deck could not be created because no such file exists\n");
			}
		}
		
		// add delimiter pattern to seperate tokens in sc stream
		sc.useDelimiter(",\\s");

		// build the deck of cards
		while(sc.hasNext()) {
			doc.push(new Card(sc.next()));
		}
		
		// reverse stack to match the requirement example
		Collections.reverse(doc);
	}
	
	public void shuffle() {
		Collections.shuffle(this.doc);
	}
	
	
	public Card nextCard() {
		return doc.pop();
	}
	
}
