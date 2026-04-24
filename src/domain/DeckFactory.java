package domain;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * Factoría encargada de la creación y configuración inicial del mazo de cartas.
 * Esta clase centraliza la lógica para generar barajas completas mezcladas y listas para jugar.
 */
public class DeckFactory {

	/**
     * Crea un mazo completo siguiendo las reglas de la baraja española (40 cartas por baraja).
     * El mazo se genera combinando todos los palos y valores disponibles.
     * Al finalizar la creación, las cartas se barajan aleatoriamente.
     * @param numberOfDecks Número de barajas de 40 cartas que compondrán el mazo total. 
     * @return Una Deque de objetos Card, barajada y organizada como una pila.
     */
	public static Deque<Card> createFullDeck(int numberOfDecks) {
		List<Card> cards= new ArrayList<>();
		
		for(int i=0; i < numberOfDecks; i++) {
			for(Suit suit : Suit.values()) {
				for(Value value : Value.values()) {
					cards.add(new Card(suit, value));
				}
			}
		}
		
		Collections.shuffle(cards);
		
		return new ArrayDeque<>(cards); 
	}
}                                 
