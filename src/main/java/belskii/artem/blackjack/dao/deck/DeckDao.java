package belskii.artem.blackjack.dao.deck;

public interface DeckDao {
	public void shuffleDeck();
	public Card getOneCard();
}
