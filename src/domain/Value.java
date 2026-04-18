package domain;

public enum Value {

	ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6),
	SEVEN(7), JACK(10), KNIGHT(11), KING(12); 
	
	private final int numValue;
	
	Value(int numValue) {
		this.numValue= numValue;
	}
	
	public int getNumValue() {
		return numValue; 
	}
}