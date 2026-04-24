package domain;

/**
 * Representa una carta individual de la baraja española utilizada en el juego del Chinchón.
 * Cada carta consta de un palo y un valor.
 * Esta clase implementa Comparable para permitir la ordenación automática de la mano del jugador.
 */
public class Card implements Comparable<Card>{

	private final Suit suit;
	private final Value value;
	
	/**
     * Constructor de la clase Card.
     * @param suit El palo asignado a la carta.
     * @param value El valor asignado a la carta.
     */
	public Card(Suit suit, Value value) {
		this.suit= suit;
		this.value= value;
	}
	
	/**
     * Obtiene el palo de la carta.
     * @return El objeto Suit correspondiente.
     */
	public Suit getSuit() {
		return suit;
	}
	
	/**
     * Obtiene el valor de la carta.
     * @return El objeto Value correspondiente.
     */
	public Value getValue() {
		return value;
	}
	
	/**
     * Devuelve los puntos que otorga esta carta si no está combinada al final de la ronda.
     * Según las reglas, los puntos coinciden con el valor numérico de la carta.
     * @return El valor entero de los puntos.
     */
	public int getPoints() {
		return value.getNumValue(); 
	}
	
	/**
     * Compara esta carta con otra para fines de ordenación.
     * El criterio de ordenación es primero por el orden jerárquico del palo 
     * y, en caso de que sea el mismo, por el valor numérico de la carta.
     * @param o La otra carta con la que comparar.
     * @return Un entero negativo, cero o un entero positivo si esta carta es menor, igual o mayor que la otra.
     */
	@Override
	public int compareTo(Card o) {
		// Primero ordenamos por palo y luego por valor
		
		if (this.suit != o.suit) {
			return this.suit.compareTo(o.suit);
		}
		
		return Integer.compare(this.value.getNumValue(), o.value.getNumValue()); 
	}

	/**
     * Devuelve una representación visual de la carta.
     * Muestra el valor numérico seguido del emoji del palo.
     * @return Una cadena de texto formateada.
     */
	@Override
	public String toString() {
		return String.format("%d %s", value.getNumValue(), suit.getEmoji()); 
	}
}