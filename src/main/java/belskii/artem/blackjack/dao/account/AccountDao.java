package belskii.artem.blackjack.dao.account;

public interface AccountDao {
	public void addAccount(String cardId, long balance);
	public String getCardId(long id);
	public long getBalance(long id);
	public void updateBalance(long id, long newBalance);
}
