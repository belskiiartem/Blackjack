package belskii.artem.blackjack.dao.gamer;

public interface GamerDao {
	public void setName(String firstName, String lastName);
	public String getFirstName(long gamerId);
	public String getLastName(long gamerId);
	public String getAccountId(long gamerId);
}
