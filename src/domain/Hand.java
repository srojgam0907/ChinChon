package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representa la mano de un jugador en el juego del Chinchón.
 * Gestiona un conjunto de 7 cartas (u 8 durante el turno) y contiene la lógica
 * para organizar combinaciones (escaleras o grupos) y calcular puntos.
 */
public class Hand {

	private List<Card> cards;
	
	/**
	 * Constructor de la clase Hand
	 * Crea una lista vacia de cartas.
	 */
	public Hand() {
		cards= new ArrayList<>();
	}
	
	/**
	 * Añade una carta a la mano del jugador
	 * @param card La carta a añadir, proveniente del mazo o del descarte
	 */
	public void addCard(Card card) {
		cards.add(card);
	}
	
	/**
     * Elimina una carta de la mano según su índice.
     * @param index El índice de la carta a descartar.
     * @return La carta eliminada para ser entregada a la pila de descartes.
     */
	public Card discardCard(int index) {
		return cards.remove(index);
	}
	
	/**
     * Ordena las cartas de la mano utilizando el criterio definido en la clase Card.
     * Facilita la visualización y la creación de combinaciones por parte del jugador.
     */
	public void sortHand() {
		Collections.sort(cards);
	}
	
	/**
     * Calcula la puntuación total de las cartas que NO están combinadas.
     * @param uncombinedCards Lista de cartas sueltas seleccionadas.
     * @return Suma de puntos de las cartas sueltas.
     */
	public int calculateTotalPoints(List<Card> UncombinedCards) {
		int total= 0;
		
		for(Card card : UncombinedCards) { 
			total += card.getPoints();
		}
		
		return total;
	} 
	
	/**
     * Verifica si el jugador tiene un Chinchón (7 cartas consecutivas del mismo palo).
     * @return true si se cumple la condición de Chinchón.
     */
	public boolean hasChinchon() {
		if(cards.size() < 7) {
			return false;
		}
		
		sortHand();
		
		for(int i= 0; i < 6; i++) {
			Card current= cards.get(i);
			Card next= cards.get(i +1);
			
			if(current.getSuit() != next.getSuit() || next.getValue().getNumValue() != current.getValue().getNumValue() + 1) {
				return false;
			}
		}
		
		return true; 
	}
	
	/**
     * Devuelve el número actual de cartas en la mano.
     * @return Cantidad de cartas.
     */
    public int size() {
        return cards.size();
    }

    /**
     * Obtiene una carta específica sin eliminarla de la mano.
     * @param index Posición de la carta.
     * @return La carta en la posición indicada.
     */
    public Card getCard(int index) {
        return cards.get(index);
    }
    
    /**
     * Devuelve una copia de las cartas para que el validador
     * pueda analizarlas sin modificar la mano original.
     * @return lista de cartas
     */
    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }
    
    /**
     * Devuelve una representación visual de la mano para la consola.
     * @return Cadena con las cartas numeradas.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Tu mano:\n");
        for (int i = 0; i < cards.size(); i++) {
            sb.append(String.format("[%d] %s", i+1, cards.get(i))); 
            
            if(i < cards.size() -1) {
            	sb.append(", "); 
            }
        }
        return sb.toString();
    }
}