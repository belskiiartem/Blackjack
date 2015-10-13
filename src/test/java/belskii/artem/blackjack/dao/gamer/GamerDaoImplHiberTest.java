package belskii.artem.blackjack.dao.gamer;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import belskii.artem.blackjack.dao.account.Account;
import belskii.artem.blackjack.dao.gamer.GamerDaoImplHiber;
import belskii.artem.blackjack.dao.journal.Journal;

public class GamerDaoImplHiberTest {

	@Test
	public void testAddGamer() {
		GamerDaoImplHiber gamer = new GamerDaoImplHiber();
		gamer.addGamer("Belskii", "Artem");
	}

	@Test
	public void testGetFirstName() {
		GamerDaoImplHiber gamer = new GamerDaoImplHiber();
		assertEquals("Belskii", gamer.getFirstName(1));
	}

	@Test
	public void testGetLastName() {
		GamerDaoImplHiber gamer = new GamerDaoImplHiber();
		assertEquals("Belskii", gamer.getFirstName(1));
	}

	@Test
	public void testGetAccountId() {
		GamerDaoImplHiber gamer = new GamerDaoImplHiber();
		assertEquals(String.class, gamer.getAccountId(1).getClass());
	}
	
	@Test
	public void testGetUserInfo(){
		GamerDaoImplHiber gamer = new GamerDaoImplHiber();
		assertTrue(gamer.getUserInfo("1234567890123456").getLastName().length()>=1);
		assertTrue(gamer.getUserInfo("1234567890123456").getFirstName().length()>=1);
		
	}

}
