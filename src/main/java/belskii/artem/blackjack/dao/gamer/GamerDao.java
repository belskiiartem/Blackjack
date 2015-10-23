package belskii.artem.blackjack.dao.gamer;

import java.util.ArrayList;

public interface GamerDao {
	public void addGamer(String firstName, String lastName, long accountId);
	public String getFirstName(long gamerId);
	public String getLastName(long gamerId);
	public long getAccountId(long gamerId);
	public Gamer getUserInfo(String cardId);
}
