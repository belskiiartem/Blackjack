package belskii.artem.blackjack.game;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import belskii.artem.blackjack.dao.party.Card;
import belskii.artem.blackjack.game.Game;

public class GameTest {
	Game game = new Game();
	
	@Test
	public void testReturnArrayWithBankCardAndGamerCard() {
		assertEquals(ArrayList.class,game.startGame(50L, "sessionId").getClass());
	}
	
	@Test
	public void testHitAll(){
		game.startGame(50L, "sessionId");
		assertEquals(Card.class, game.hitAll("sessionId").getClass());
	}

}
