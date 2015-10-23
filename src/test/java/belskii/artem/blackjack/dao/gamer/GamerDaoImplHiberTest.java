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
		gamer.addGamer("Artem", "Belskii", 1L);
	}

	@Test
	public void testGetFirstName() {
		GamerDaoImplHiber gamer = new GamerDaoImplHiber();
		assertEquals("Artem", gamer.getFirstName(2));
	}

	@Test
	public void testGetLastName() {
		GamerDaoImplHiber gamer = new GamerDaoImplHiber();
		assertEquals("Belskii", gamer.getLastName(2));
	}

	@Test
	public void testGetAccountId() {
		GamerDaoImplHiber gamer = new GamerDaoImplHiber();
		assertTrue(gamer.getAccountId(2)>=1);
	}
	
	@Test
	public void testGetUserInfo(){
		GamerDaoImplHiber gamer = new GamerDaoImplHiber();
		assertEquals("",gamer.getUserInfo("1234567890123456").getLastName());
		assertEquals("",gamer.getUserInfo("1234567890123456").getFirstName());
		
	}

}
