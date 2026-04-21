package domain;

import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Optional;

public class Deck {

	private Deque<Card> cards;
	
	public Deck(int numberOfDecks) {
		this.cards= CardFactory.createFullDeck(numberOfDecks);
	}
	
	public Optional<Card> drawCard() {
		if(cards.isEmpty()) {
			return Optional.empty(); 
		}
		
		return Optional.of(cards.pop()); 
	}
	
	public void refillFromDiscardPile(List<Card> discardedCards) {
		Collections.shuffle(discardedCards);
		
		for(Card card : discardedCards) {
			cards.push(card); 
		}
	}
	
	public boolean isEmpty() {
		return cards.isEmpty();
	}
	
	public int deckSize() { 
		return cards.size();
	}
	
	@Override
    public String toString() {
        return String.format("Mazo: %d cartas restantes.", cards.size());
    }
}