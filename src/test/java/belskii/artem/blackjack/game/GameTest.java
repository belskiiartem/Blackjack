package belskii.artem.blackjack.game;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.junit.Test;

public class GameTest {
	Game game = new Game();

	private String getSessionId() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}

	private String partyId = this.getSessionId();

	@Test
	public void startGame() {
		game.startGame(partyId, 50L);
		assertTrue(game.getGamerCount(partyId) > 0 & game.getGamerCount(partyId) < 30);
		assertTrue(game.getBankCount(partyId) > 0 & game.getBankCount(partyId) < 30);
	}

	@Test
	public void testHitGamer() {
		game.startGame(partyId, 50L);
		assertTrue(game.gamerHit(partyId) > 0 & game.gamerHit(partyId) < 31);
	}

	@Test
	public void testHitBank() {
		game.startGame(partyId, 50L);
		assertTrue(game.bankHit(partyId) > 0 & game.bankHit(partyId) < 31);
	}

	@Test
	public void testBankCount() {
		game.startGame(partyId, 50L);
		assertTrue(game.getBankCount(partyId) > 0 & game.getBankCount(partyId) < 31);
	}

	@Test
	public void testGamerCount() {
		game.startGame(partyId, 50L);
		assertTrue(game.getGamerCount(partyId) > 0 & game.getGamerCount(partyId) < 31);
	}

}
