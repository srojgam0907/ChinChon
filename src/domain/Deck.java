package domain;

import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Optional;

/**
 * Gestiona el mazo principal de cartas de la partida.
 * Se encarga de suministrar cartas a los jugadores y de recargarse cuando se agota.
 * Utiliza una estructura de datos Deque para un comportamiento de pila (LIFO).
 */
public class Deck {

	private Deque<Card> cards;
	
	/**
     * Constructor de la clase Deck.
     * Utiliza la factoría DeckFactory para generar las cartas iniciales ya barajadas.
     * @param numberOfDecks Número de barajas de 40 cartas que se van a utilizar.
     */
	public Deck(int numberOfDecks) {
		this.cards= DeckFactory.createFullDeck(numberOfDecks);
	}
	
	/**
     * Extrae y devuelve la carta superior del mazo.
     * @return Un Optional que contiene la carta robada, o un Optional vacío si el mazo no tiene cartas.
     */
	public Optional<Card> drawCard() {
		if(cards.isEmpty()) {
			return Optional.empty(); 
		}
		
		return Optional.of(cards.pop()); 
	}
	
	/**
     * Rellena el mazo con cartas procedentes del montón de descartes.
     * Este proceso ocurre cuando el mazo se queda sin cartas y es necesario reciclar el descarte.
     * Las cartas se barajan antes de ser añadidas de nuevo al mazo.
     * @param discardedCards Lista de cartas provenientes de la pila de descartes.
     */
	public void refillFromDiscardPile(List<Card> discardedCards) {
		Collections.shuffle(discardedCards);
		
		for(Card card : discardedCards) {
			cards.push(card); 
		}
	}
	
	/**
     * Comprueba si el mazo está vacío.
     * @return true si no quedan cartas en el mazo, false en caso contrario.
     */
	public boolean isEmpty() {
		return cards.isEmpty();
	}
	
	/**
     * Obtiene el número de cartas que quedan actualmente en el mazo.
     * @return Cantidad de cartas restantes.
     */
	public int deckSize() { 
		return cards.size();
	}
	
	/**
     * Devuelve una representación textual del estado del mazo.
     * @return Cadena que indica cuántas cartas quedan.
     */
	@Override
    public String toString() {
        return String.format("Mazo: %d cartas restantes.", cards.size());
    }
}