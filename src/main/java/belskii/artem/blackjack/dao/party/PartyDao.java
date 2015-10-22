package belskii.artem.blackjack.dao.party;

import java.util.List;

public interface PartyDao {
	public void shuffleDeck(String partyId);
	public List<Card> gamerCardsOnHend(String partyId);
	public List<Card> bankCardsOnHend(String partyId);
	public Party gamerHit(String partyId);
	public Party bankHit(String partyId);
}
