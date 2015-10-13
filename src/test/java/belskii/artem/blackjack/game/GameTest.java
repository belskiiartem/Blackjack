package belskii.artem.blackjack.game;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import belskii.artem.blackjack.dao.deck.Card;
import belskii.artem.blackjack.game.Game;

public class GameTest {
	Game game = new Game();
	
	@Test
	public void testReturnArrayWithBankCardAndGamerCard() {
		assertEquals(ArrayList.class,game.startGame(50L).getClass());
	}
	
	@Test
	public void testHit(){
		game.startGame(50L);
		assertEquals(Card.class, game.hit().getClass());
	}

}
