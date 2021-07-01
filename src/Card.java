
public class Card {
	
	private String name;
	private int value;

	public Card(String s) throws Exception{
		this.name = s;

		String val = s.substring(1, s.length());
		try{
			if (val.matches("[JQK]")) this.value = 10;
			else if (val.matches("[A]")) this.value = 11;
			else this.value = Integer.parseInt( s.substring(1, s.length()) );
		}
		catch(Exception e) {
			// ensure exception is thrown up the stack (to deck usually)
			System.out.println("Could not create card with ID: "+s);
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
