package domain;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Optional;

public class DiscardPile {

	public Deque<Card> discardedCards;
	
	public DiscardPile() {
		this.discardedCards= new ArrayDeque<>();
	}
	
	public void pushCard(Card card) {
		discardedCards.push(card);
	}
	
	public Optional<Card> peekLastCard() {
		if(discardedCards.isEmpty()) {
			return Optional.empty();
		}
		
		return Optional.of(discardedCards.peek());
	}
	
	public Optional<Card> takeCard() {
		if(discardedCards.isEmpty()) {
			return Optional.empty();
		}
		
		return Optional.of(discardedCards.pop()); 
	}
	
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
	
	public boolean isEmpty() {
		return discardedCards.isEmpty();
	}
	
	public int size() {
		return discardedCards.size();
	}
	
	@Override
	public String toString() {
		return discardedCards.isEmpty() ? "La pila de descartes está vacía" :
				"Última carta en el descarte: " + discardedCards.peek(); 
	}
}