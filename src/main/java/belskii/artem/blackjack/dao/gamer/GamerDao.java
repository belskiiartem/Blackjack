package belskii.artem.blackjack.dao.gamer;

import java.util.ArrayList;

public interface GamerDao {
	public void addGamer(String firstName, String lastName);
	public String getFirstName(long gamerId);
	public String getLastName(long gamerId);
	public String getAccountId(long gamerId);
	public Gamer getUserInfo(String cardId);
}
