package belskii.artem.blackjack.dao.party;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class PartyDaoImplHiberTest {

	private String getSessionId() {
		SecureRandom random = new SecureRandom();
	    return new BigInteger(130, random).toString(32);
	}
	private String sessionId = this.getSessionId();

	@Test
	public void testShuffleDeck() {
		PartyDaoImplHiber party = new PartyDaoImplHiber();
		party.shuffleDeck(sessionId, 50L);
	}
	
	@Test
	public void testGamerHit() {
		PartyDaoImplHiber party = new PartyDaoImplHiber();
		party.shuffleDeck(sessionId, 50L);
		party.gamerHit(sessionId);
		assertTrue(party.gamerCardsOnHend(sessionId).size()>0& party.gamerCardsOnHend(sessionId).size()<51);
	}
	

	@Test
	public void testBankHit() {
		PartyDaoImplHiber party = new PartyDaoImplHiber();
		party.shuffleDeck(sessionId, 50L);
		party.bankHit(sessionId);
		assertTrue(party.bankCardsOnHend(sessionId).size()>0 & party.bankCardsOnHend(sessionId).size()<51);

	}

	@Test
	public void testGamerCardsOnHend() {
		PartyDaoImplHiber party = new PartyDaoImplHiber();
		party.shuffleDeck(sessionId, 50L);
		party.gamerHit(sessionId);
		assertTrue(party.gamerCardsOnHend(sessionId).size()>0& party.gamerCardsOnHend(sessionId).size()<51);
	}

	@Test
	public void testBankCardsOnHend() {
		PartyDaoImplHiber party = new PartyDaoImplHiber();
		party.shuffleDeck(sessionId, 50L);
		party.bankHit(sessionId);
		assertTrue(party.bankCardsOnHend(sessionId).size()>0 & party.bankCardsOnHend(sessionId).size()<51);

	}



}
