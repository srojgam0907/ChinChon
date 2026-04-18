package domain;

public enum Suit {

	GOLD, 	//oro
	CUPS, 	//copas
	SWORDS, //espadas
	CLUBS; 	//bastos
	
	@Override
	public String toString() {
		return switch(this) {
		case GOLD -> "Oro";
		case CUPS -> "Copas";
		case SWORDS -> "Espadas";
		case CLUBS -> "Bastos";
		};
	}
}