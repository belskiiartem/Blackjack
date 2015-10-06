package belskii.artem.blackjack.dao.user;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserDaoImplHiberTest {


	@Test
	public void testSetName() {
		UserDao user = new UserDaoImplHiber();
		user.setName("belskii", "artem");
//		System.out.println(user.getFirstName(1L));
	}

//	@Test
//	public void testGetFirstName() {
//		UserDao user = new UserDaoImplHiber();
//		System.out.println(user.getFirstName(1L));
//	}

	@Test
	public void testGetLastName() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAccountIdName() {
		fail("Not yet implemented");
	}

}
