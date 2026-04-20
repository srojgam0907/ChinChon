package domain;

public class Card implements Comparable<Card>{

	private final Suit suit;
	private final Value value;
	
	public Card(Suit suit, Value value) {
		this.suit= suit;
		this.value= value;
	}
	
	public Suit getSuit() {
		return suit;
	}
	
	public Value getValue() {
		return value;
	}
	
	public int getPoints() {
		return value.getNumValue(); 
	}
	
	@Override
	public int compareTo(Card o) {
		// Primero ordenamos por palo y luego por valor
		
		if (this.suit != o.suit) {
			return this.suit.compareTo(o.suit);
		}
		
		return Integer.compare(this.value.getNumValue(), o.value.getNumValue()); 
	}

	@Override
	public String toString() {
		return String.format("%d %s", value.getNumValue(), suit.getEmoji()); 
	}
}