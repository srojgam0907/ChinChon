package domain;

/**
 * Representa los cuatro palos de la baraja española utilizados en el juego del Chinchón.
 * Los palos disponibles son Oros, Copas, Espadas y Bastos.
 * Cada palo tiene asociado un emoji para facilitar su visualización en la consola.
 */
public enum Suit {

	GOLD("🪙"), 	
	CUPS("🍷"), 	
	SWORDS("⚔️"), 
	CLUBS("🦯"); 
	
	private final String emoji;
	
	/**
     * Constructor del enum Suit.
     * @param emoji Cadena de texto que contiene el emoji que representa al palo.
     */
	Suit(String emoji) {
		this.emoji= emoji;
	}
	
	/**
     * Obtiene el emoji asociado a este palo.
     * @return Una cadena con el emoji del palo.
     */
	public String getEmoji() {
		return emoji;  
	}
}