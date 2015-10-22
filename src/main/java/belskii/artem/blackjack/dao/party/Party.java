package belskii.artem.blackjack.dao.party;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "partyId" }) )
public class Party {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String partyId;
	private ArrayList<Card> gamerCardsOnHend;
	private ArrayList<Card> bankCardsOnHend;
	private ArrayList<Card> deck;

	Party() {
		gamerCardsOnHend = new ArrayList<Card>();
		bankCardsOnHend = new ArrayList<Card>();
		deck = new ArrayList<Card>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	public ArrayList<Card> getGamerCardsOnHend() {
		return gamerCardsOnHend;
	}

	public void setGamerCardsOnHend(ArrayList<Card> gamerCardsOnHend) {
		this.gamerCardsOnHend = gamerCardsOnHend;
	}

	public ArrayList<Card> getBankCardsOnHend() {
		return bankCardsOnHend;
	}

	public void setBankCardsOnHend(ArrayList<Card> bankCardsOnHend) {
		this.bankCardsOnHend = bankCardsOnHend;
	}

	public ArrayList<Card> getDeck() {
		return deck;
	}

	public void setDeck(ArrayList<Card> deck) {
		this.deck = deck;
	}

	public void gamerHit() {
		ArrayList<Card> deck = this.getDeck();
		Card card = deck.get(deck.size() - 1);
		this.gamerCardsOnHend.add(card);
		deck.remove(deck.size() - 1);
	}

	public void bankHit() {
		ArrayList<Card> deck = this.getDeck();
		Card card = deck.get(deck.size() - 1);
		this.bankCardsOnHend.add(card);
		deck.remove(deck.size() - 1);
	}
}
