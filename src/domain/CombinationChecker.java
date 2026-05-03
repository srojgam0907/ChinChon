package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Clase para detectar combinaciones válidas en una mano.
 * Se encarga de identificar grupos de cartas iguales (Sets) y escaleras (Runs).
 */
public class CombinationChecker {

	/**
     * Comprueba si un grupo de cartas forma un "Set" (mismo número).
     * @param cards Lista de cartas a validar.
     * @return true si hay al menos 3 cartas y todas tienen el mismo valor.
     */
	public static boolean isSet(List<Card> cards) {
		int firstValue;
		
		if(cards.size() < 3) {
			return false;
		}
		
		firstValue= cards.get(0).getValue().getNumValue();
		
		for(Card card : cards) {
			if(card.getValue().getNumValue() != firstValue) {
				return false;
			}
		}
		
		return true; 
	}
	
	/**
     * Comprueba si un grupo de cartas forma un "Run" (escalera del mismo palo).
     * @param cards Lista de cartas a validar (deben estar ordenadas).
     * @return true si hay al menos 3 cartas consecutivas del mismo palo.
     */
	public static boolean isRun(List<Card> cards) {
		Suit firstSuit; 
		Card current;
		Card next;
		
		if(cards.size() < 3) {
			return false;
		}
		
		firstSuit= cards.get(0).getSuit();
		
		for(int i=0; i< cards.size()-1; i++) {
			current= cards.get(i);
			next= cards.get(i +1); 
			
			//Comprueba que sean del mismo palo y que la siguiente sea el valor actual +1
			if(current.getSuit() != firstSuit || 
				next.getSuit()  != firstSuit || 
				next.getValue().getNumValue() != current.getValue().getNumValue() +1) {
				
				return false;
			}
		}
		
		return true; 
	}
	
	/**
     * Verifica si el jugador tiene un Chinchón (7 cartas consecutivas del mismo palo).
     * @return true si se cumple la condición de Chinchón.
     */
	public static boolean isChinchon(List<Card> cards) {
		if(cards.size() == 7 && isRun(cards)) {
			return true;
			
		} else {
			return false; 
		}
	}
	
	/**
     * Calcula la puntuación total de las cartas que NO están combinadas.
     * @param cards Lista de cartas sueltas seleccionadas. 
     * @return Suma de puntos de las cartas sueltas.
     */
	public static int calculateUncombinedPoints(List<Card> cards) {
		int points= 0;
		List<Card> uncombinedCards= new ArrayList<>(cards);
		Collections.sort(uncombinedCards);
		
		removeRuns(uncombinedCards);
		removeSets(uncombinedCards);
		
		for(Card card : uncombinedCards) {
			points += card.getPoints();
		}
		
		return points; 
	}
	
	/**
	 * Busca y elimina las escaleras
	 * @param cards Lista de cartas a revisar
	 */
	public static void removeRuns(List<Card> cards) {
		if(cards.size() < 3) {
			return;
		}
		
		List<Card> toRemove= new ArrayList<>();
		boolean found= false;
		Card last;
		Card current;
		
		for(int i=0; i<cards.size() && !found; i++) {
			List<Card> sequence = new ArrayList<>();
			sequence.add(cards.get(i));
			
			for(int j= i + 1; j< cards.size(); j++) {
				last= sequence.get(sequence.size() -1);
				current= cards.get(j);
				
				if(current.getSuit() == last.getSuit() && current.getValue().getNumValue() == last.getValue().getNumValue() +1) {
					sequence.add(current);
				}
			}
			
			if(sequence.size() >=3) {
				toRemove.addAll(sequence);
				found= true;
			}
		}
		cards.removeAll(toRemove);
	}
	
	/**
	 * Busca y elimina los sets
	 * @param cards Lista de cartas a revisar
	 */
	public static void removeSets(List<Card> cards) {
		if(cards.size() < 3) {
			return;
		}
		
		List<Card> toRemove= new ArrayList<>();
		boolean found= false;
		int value;
		
		for(int i=0; i < cards.size() && !found; i++) {
			value= cards.get(i).getValue().getNumValue();
			List<Card> group= new ArrayList<>();
			
			for(Card c : cards) {
				if(c.getValue().getNumValue() == value) {
					group.add(c);
				}
			}
			
			if(group.size() >= 3) {
				toRemove.addAll(group);
				found= true;
			}
		}
		
		cards.removeAll(toRemove);
	}
}















