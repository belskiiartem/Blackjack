package belskii.artem.blackjack.dao.deck;

import static org.junit.Assert.*;

import org.junit.Test;

public class DeckDaoImplInMemTest {

	@Test
	public void testDeckDaoImplInMem() {
		DeckDaoImplInMem deck = new DeckDaoImplInMem();
		assertEquals(String.class, deck.getOneCard().color.getClass());
	}

	@Test
	public void testShuffleDeck() {
		DeckDaoImplInMem deck = new DeckDaoImplInMem();
		Card beforeShuffl=deck.getCardFromSleeve(0);
		deck.shuffleDeck();
		Card afterShuffl=deck.getCardFromSleeve(0);
		assertTrue(!beforeShuffl.equals(afterShuffl));
	}

	@Test
	public void testGetOneCard() {
		DeckDaoImplInMem deck = new DeckDaoImplInMem();
		Card firstCard = deck.getOneCard();
		Card secondCard = deck.getOneCard();
		assertTrue(!firstCard.equals(secondCard));
	}

}
