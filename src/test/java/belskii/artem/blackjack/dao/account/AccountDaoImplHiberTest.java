package belskii.artem.blackjack.dao.account;

import static org.junit.Assert.*;

import org.junit.Test;

public class AccountDaoImplHiberTest {

	@Test
	public void testAddAccount() {
		AccountDaoImplHiber account = new AccountDaoImplHiber();
		account.addAccount("1234567890123456", 100L);
		assertTrue(account.getCardNumber(1L).length()==16);
		assertTrue(account.getBalance(1L)>=0);
	}
	@Test
	public void testUpdateBalance(){
		AccountDaoImplHiber account = new AccountDaoImplHiber();
		account.updateBalance(1, 20);
		assertTrue(account.getBalance(1)==20);
	}
	
	@Test
	public void testGetCardId() {
		AccountDaoImplHiber account = new AccountDaoImplHiber();
		assertTrue(account.getCardNumber(1L).length()==16);
	}

	@Test
	public void testGetBalance() {
		AccountDaoImplHiber account = new AccountDaoImplHiber();
		assertTrue(account.getBalance(1L)>=0);
	}
	
	@Test
	public void testFindCard(){
		AccountDaoImplHiber account = new AccountDaoImplHiber();
		assertEquals(-1L, account.findCard("999999999999999"));
		assertEquals(1, account.findCard("1234567890123456"));
	}


}
