package domain;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class DeckFactory {

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
