package domain;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Optional;

/**
 * Representa el montón de cartas descartadas boca arriba en la mesa.
 * Los jugadores pueden interactuar con esta pila robando la última carta descartada
 * o añadiendo una nueva al finalizar su turno.
 */
public class DiscardPile {

	private Deque<Card> discardedCards;
	
	/**
     * Constructor de la clase. 
     * Inicializa una pila de descartes vacía.
     */
	public DiscardPile() {
		this.discardedCards= new ArrayDeque<>();
	}
	
	/**
     * Añade una carta a la parte superior de la pila de descartes.
     * @param card La carta que el jugador decide descartar al final de su turno.
     */
	public void pushCard(Card card) {
		discardedCards.push(card);
	}
	
	/**
     * Permite observar la carta situada en la parte superior sin extraerla de la pila.
     * Útil para que el jugador decida si desea robarla.
     * @return Un Optional con la carta superior, o vacío si la pila no tiene cartas.
     */
	public Optional<Card> peekLastCard() {
		if(discardedCards.isEmpty()) {
			return Optional.empty();
		}
		
		return Optional.of(discardedCards.peek());
	}
	
	/**
     * Extrae y devuelve la carta situada en la parte superior de la pila.
     * Se utiliza cuando un jugador elige robar del montón de descarte.
     * @return Un Optional con la carta extraída, o vacío si la pila está vacía.
     */
	public Optional<Card> takeCard() {
		if(discardedCards.isEmpty()) {
			return Optional.empty();
		}
		
		return Optional.of(discardedCards.pop()); 
	}
	
	/**
     * Recolecta todas las cartas de la pila excepto la última para rellenar el mazo.
     * Según las reglas, cuando el mazo se agota, se barajan los descartes pero
     * se debe mantener la carta superior boca arriba.
     * @return Una lista con las cartas recuperadas para ser reutilizadas en el mazo.
     */
	public List<Card> collectAllButLast() {
		List<Card> reUse= new ArrayList<>();
		Card topCard;
		
		if(discardedCards.size() <= 1) {
			return reUse;
		}
		
		topCard= discardedCards.pop();
		
		reUse.addAll(discardedCards);
		
		discardedCards.clear();
		discardedCards.push(topCard);
		
		return reUse; 
	}
	
	/**
     * Indica si la pila de descartes no contiene ninguna carta.
     * @return true si está vacía, false en caso contrario.
     */
	public boolean isEmpty() {
		return discardedCards.isEmpty();
	}
	
	/**
     * Devuelve la cantidad de cartas que hay actualmente en la pila.
     * @return El número total de cartas descartadas.
     */
	public int size() {
		return discardedCards.size();
	}
	
	/**
     * Devuelve una representación textual de la pila de descartes.
     * Indica cuál es la carta visible para los jugadores.
     * @return Mensaje de estado o la descripción de la última carta.
     */
	@Override
	public String toString() {
		return discardedCards.isEmpty() ? "La pila de descartes está vacía" :
				"Última carta en el descarte: " + discardedCards.peek(); 
	}
}