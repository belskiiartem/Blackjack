package belskii.artem.blackjack.dao.journal;

import java.util.List;

public interface JournalDao {
	public List getJournal(long userId);
	public void newIvent(long userId, String action, long accountId, long amount);
}
