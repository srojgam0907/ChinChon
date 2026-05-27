package test;

import static org.junit.jupiter.api.Assertions.*; // Importaciones de JUnit
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import domain.*;
import java.util.ArrayList;
import java.util.List;

public class CombinationCheckerTest {

    private List<Card> cards;

    @BeforeEach
    void setUp() {
        // Se ejecuta antes de cada test para limpiar la mano
        cards = new ArrayList<>();
    }

    @Test
    void testIsValidSet() { // Test para los trios
    	// Un trío del mismo valor y diferente palo debería ser válido
        cards.add(new Card(Suit.GOLD, Value.ONE));
        cards.add(new Card(Suit.CUPS, Value.ONE));
        cards.add(new Card(Suit.SWORDS, Value.ONE));
        
        assertTrue(CombinationChecker.isSet(cards));
    }

    @Test
    void testIsValidRun() { // Test para las escaleras
    	// Cuatro cartas contigüas del mismo palo debería ser válido
        cards.add(new Card(Suit.CLUBS, Value.ONE));
        cards.add(new Card(Suit.CLUBS, Value.TWO));
        cards.add(new Card(Suit.CLUBS, Value.THREE));
        cards.add(new Card(Suit.CLUBS, Value.FOUR));
        
        assertTrue(CombinationChecker.isRun(cards));
    }
    
    @Test
    void testIsValidChinchon() {
    	// Siete cartas contigüas del mismo palo debería ser válido
    	cards.add(new Card(Suit.SWORDS, Value.ONE));
        cards.add(new Card(Suit.SWORDS, Value.TWO));
        cards.add(new Card(Suit.SWORDS, Value.THREE));
        cards.add(new Card(Suit.SWORDS, Value.FOUR));
        cards.add(new Card(Suit.SWORDS, Value.FIVE));
        cards.add(new Card(Suit.SWORDS, Value.SIX));
        cards.add(new Card(Suit.SWORDS, Value.SEVEN));
        
        assertTrue(CombinationChecker.isChinchon(cards));
    }

    @Test
    void testPointsUncombinedCards() { 
        // Una Sota (10) y un 5 (5) = 15 puntos
        cards.add(new Card(Suit.GOLD, Value.JACK));
        cards.add(new Card(Suit.SWORDS, Value.FIVE)); 
        
        int puntos = CombinationChecker.calculateUncombinedPoints(cards);
        
        assertEquals(15, puntos); 
    }

    @Test
    void testPointsCombinedCards() {
        // Trío de Reyes (0 puntos) + Un Dos suelto (2 puntos)
        cards.add(new Card(Suit.GOLD, Value.KING));
        cards.add(new Card(Suit.CUPS, Value.KING));
        cards.add(new Card(Suit.SWORDS, Value.KING));
        cards.add(new Card(Suit.CLUBS, Value.TWO)); 
        
        int puntos = CombinationChecker.calculateUncombinedPoints(cards);
        
        assertEquals(2, puntos);
    }
}
