package domain;

public enum Suit {

	GOLD("🪙"), 	//oro
	CUPS("🍷"), 	//copas
	SWORDS("⚔️"), //espadas
	CLUBS("🦯"); 	//bastos
	
	private final String emoji;
	
	Suit(String emoji) {
		this.emoji= emoji;
	}
	
	public String getEmoji() {
		return emoji;  
	}
}