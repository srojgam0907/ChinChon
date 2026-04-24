package domain;

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
		
		if(cards.size() < 3) {
			return false;
		}
		
		firstSuit= cards.get(0).getSuit();
		
		for(int i=0; i< cards.size()-1; i++) {
			Card current= cards.get(i);
			Card next= cards.get(i +1);
			
			//Comprueba que sean del mismo palo y que la siguiente sea el valor actual +1
			if(current.getSuit() != firstSuit || 
				next.getSuit()  != firstSuit || 
				next.getValue().getNumValue() != current.getValue().getNumValue() +1) {
				
				return false;
			}
		}
		
		return true; 
	}
}