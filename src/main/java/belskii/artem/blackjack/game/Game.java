package belskii.artem.blackjack.game;

import java.util.ArrayList;

import belskii.artem.blackjack.dao.party.Card;
import belskii.artem.blackjack.dao.party.PartyDao;

public class Game {
	ArrayList <Card> bank= new ArrayList<Card>();
	ArrayList <Card> gamer= new ArrayList<Card>();
	ArrayList<ArrayList> party=new ArrayList<ArrayList>();
	PartyDao deck=null;

	public ArrayList startGame(long bet, String jSessionId) {
//		//deck=new DeckDaoImplInMem();
//		deck=new DeckDaoImplHiber(jSessionId);
//		bank.add(deck.getOneCard(jSessionId));
//		bank.add(deck.getOneCard(jSessionId));
//		gamer.add(deck.getOneCard(jSessionId));
//		gamer.add(deck.getOneCard(jSessionId));
//		party.add(bank);
		party.add(gamer);
		return party;
	}

	public Object hitAll(String jSessionId) {
		party.clear();
//		bank.add(deck.getOneCard(jSessionId));
//		gamer.add(deck.getOneCard(jSessionId));
//		party.add(bank);
		party.add(gamer);
		return party;
	}

	public Object hitBank(String jSessionId) {
		party.clear();
//		bank.add(deck.getOneCard(jSessionId));
		party.add(bank);
		party.add(gamer);
		return party;
	}

	public Object hitGamer(String jSessionId) {
		party.clear();
//		gamer.add(deck.getOneCard(jSessionId));
		party.add(bank);
		party.add(gamer);
		return party;
	}
	

}
