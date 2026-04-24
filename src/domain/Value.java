package domain;

/**
 * Representa los valores numéricos de las cartas en la baraja española para el Chinchón.
 * Incluye los números del 1 al 7 y las figuras (Sota, Caballo y Rey).
 * * Según las reglas del juego:
 * - Los números 8 y 9 no se utilizan.
 * - Las figuras tienen un valor de 10, 11 y 12 puntos respectivamente.
 */
public enum Value {

	ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6),
	SEVEN(7), JACK(10), KNIGHT(11), KING(12); 
	
	private final int numValue;
	
	/**
     * Constructor del enumerado Value.
     * @param numValue El valor entero asociado a la carta.
     */
	Value(int numValue) {
		this.numValue= numValue;
	}
	
	/**
     * Obtiene el valor numérico de la carta.
     * @return El valor entero de la carta.
     */
	public int getNumValue() {
		return numValue; 
	}
}