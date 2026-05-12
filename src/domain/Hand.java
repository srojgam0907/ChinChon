package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tema2_1_EscrituraEnPantalla.colores.Colors;

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
     * Verifica si la mano actual (que debe tener 8 cartas tras robar) permite cerrar.
     * @return true si es valido o false si no puede cerrar 
     */
    public boolean canClose() {
    	List<Card> currentCards= getCards();
    	
    	for(int i=0; i< currentCards.size(); i++) {
    		List<Card> tempHand= new ArrayList<>(currentCards);
    		Card candidateToDiscard= tempHand.remove(i);
    		
    		if(isValidClosingHand(tempHand, candidateToDiscard)) {
    			return true;
    		}
    	}
    	
    	return false; 
    }
    
    /**
     * Determina si una configuración de cartas es válida para finalizar la ronda.
     * @param cards La lista de 7 cartas que se van a evaluar.
     * @param lastCard La carta que el jugador ha decidido soltar para cerrar.
     * @return close True si la combinación permite cerrar la ronda, false en caso contrario.
     */
    public boolean isValidClosingHand(List<Card> cards, Card lastCard) {
    	int uncombinedPoints;
    	
    	if(CombinationChecker.isChinchon(cards)) {
    		return true;
    	}
    	
    	uncombinedPoints= CombinationChecker.calculateUncombinedPoints(cards);
    	
    	if(uncombinedPoints <= 5) {
    		return true;
    		
    	} else { 
    		return false;
    	}
    }
    
    /**
     * Devuelve una representación visual de la mano para la consola.
     * @return Cadena con las cartas numeradas.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Tu mano:\n");
        for (int i = 0; i < cards.size(); i++) {
            sb.append(String.format("%s[%d]%s %s",Colors.CYAN_BRIGHT, i+1, Colors.RESET, cards.get(i))); 
            
            if(i < cards.size() -1) {
            	sb.append(", "); 
            }
        }
        return sb.toString();
    }
}