package it.unibo.memory.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    void testCardCreation() {
        Card card = new Card(5); // Creo  una carta con simbolo 5 per il test
        
        assertEquals(5, card.getSymbol()); // <-- Verifico che il simbolo sia corretto e la carta sia coperta
        assertFalse(card.isFaceUp());
    }
}