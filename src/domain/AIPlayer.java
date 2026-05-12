package domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa a un jugador controlado por una IA
 */
public class AIPlayer extends Player{

	/**
	 * Constructor del jugador IA
	 * @param name variable heredada de Player
	 */
	public AIPlayer(String name) {
		super(name);
	}

	@Override
	public void playTurn(Deck deck, DiscardPile discardPile) {
		boolean tookDiscard= false;
		Card topDiscard;
		int actualPoints;
		int discardPoints;
		int finalPoints;
		
		System.out.printf("\n%s>>>%s TURNO DE: %s\n\n", cyan, reset, name);
		
		//ROBO
		if(!discardPile.isEmpty()) {
			topDiscard= discardPile.peekLastCard().get();
			
			actualPoints= CombinationChecker.calculateUncombinedPoints(hand.getCards());
			hand.addCard(topDiscard);
			discardPoints= calculateBestPointsAfterDiscard();
			hand.discardCard(hand.size() -1);
			
			if(discardPoints < actualPoints) {
				discardPile.takeCard().ifPresent(hand::addCard);
				System.out.printf("%s ha robado del descarte\n", name);
				tookDiscard= true;
			}
		}
		
		if(!tookDiscard) {
			deck.drawCard().ifPresent(hand::addCard);
			System.out.printf("%s ha robado del mazo\n", name);
		}
		
		//DESCARTE
		List<Card> currentCards= hand.getCards();
		Card toDiscard;
		int bestDiscardIndex= 0;
		int minPoints= Integer.MAX_VALUE;
		int pointsWithoutCard;
		
		for(int i=0; i < currentCards.size(); i++) {
			List<Card> tempHand= new ArrayList<>(currentCards);
			tempHand.remove(i);
			
			pointsWithoutCard= CombinationChecker.calculateUncombinedPoints(tempHand);
			
			if(pointsWithoutCard < minPoints) {
				minPoints= pointsWithoutCard;
				bestDiscardIndex= i;
			}
		}
		
		toDiscard= hand.discardCard(bestDiscardIndex);
		discardPile.pushCard(toDiscard); 
		
		System.out.printf("%s ha descartado: %s\n", name, toDiscard);
		hand.sortHand();
		
		//CERRADO
		if(hand.canClose()) {
			finalPoints= CombinationChecker.calculateUncombinedPoints(hand.getCards());
			
			if(finalPoints <= 3) {
				hasClosed= true;
			}
		}
	}
	
	/**
	 * Evalúa todas las posibilidades de descarte de la mano actual para encontrar
	 * la mejor puntuación posible
	 * 
	 * @return el valor minimo de puntos no combinados que la IA puede lograr en este turno.
	 */
	private int calculateBestPointsAfterDiscard() {
		List<Card> cards= hand.getCards();
		int min= Integer.MAX_VALUE;
		int points;
		
		for(int i=0; i<cards.size(); i++) {
			List<Card> temp= new ArrayList<>(cards);
			temp.remove(i);
			points= CombinationChecker.calculateUncombinedPoints(temp);
			
			if(points < min) {
				min= points;
			}
		}
		
		return min;
	}
}