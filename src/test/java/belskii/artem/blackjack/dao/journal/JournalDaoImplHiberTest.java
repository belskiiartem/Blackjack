package belskii.artem.blackjack.dao.journal;

import static org.junit.Assert.*;

import org.junit.Test;

public class JournalDaoImplHiberTest {


	@Test
	public void testGetJournal() {
		JournalDaoImplHiber journal = new JournalDaoImplHiber();
		assertTrue(journal.getUserJournal(1).size()>0);

	}

	@Test
	public void testNewIvent() {
	JournalDaoImplHiber journal = new JournalDaoImplHiber();
	journal.newIvent(1, "win", 1, 50);
	}

}
