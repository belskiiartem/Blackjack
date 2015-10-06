package belskii.artem.blackjack.dao.gamer;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import belskii.artem.blackjack.dao.gamer.GamerDaoImplHiber;

public class GamerDaoImplHiberTest {

	@Test
	public void testSetName() {
		GamerDaoImplHiber gamer = new GamerDaoImplHiber();
		gamer.setName("Belskii", "Artem");
		assertEquals("Artem",gamer.getLastName(1));
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

}
