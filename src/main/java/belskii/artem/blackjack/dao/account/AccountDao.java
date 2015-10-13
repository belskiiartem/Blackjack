package belskii.artem.blackjack.dao.account;

import belskii.artem.blackjack.dao.gamer.Gamer;

public interface AccountDao {
	public void addAccount(String cardId, long balance);
	public String getCardNumber(long id);
	public long getBalance(long id);
	public void updateBalance(long id, long newBalance);
	public long findCard(String cardId);
	public Account getInfo(long accId);
}
