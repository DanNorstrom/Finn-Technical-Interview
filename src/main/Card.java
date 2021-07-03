package main;

public class Card {
	
	private String name;
	private int value;

	public Card(String s){
		this.name = s;
		
		String val = s.substring(1, s.length());		
			
		try{
			// acertain correct input format
			if ( !s.matches("[CSHD]{1}(10|[0-9JQKA]{1})")) throw new Exception();
			// allocate value
			if (val.matches("[JQK]")) this.value = 10;
			else if (val.matches("[A]")) this.value = 11;
			else this.value = Integer.parseInt( s.substring(1, s.length()) );
		}
		catch(Exception e) {
			//System.out.println("Could not create card with ID: "+s);
			throw new IllegalArgumentException("Could not create card with ID: "+s);
		}
	}
	
//	Card(String s, String v){
//		this.name = s+v;
//		this.value = v;
//	}
	
	public int value() {
		return this.value;
	}
	
	
	@Override
	public String toString() {
		return this.name;
	}
}
