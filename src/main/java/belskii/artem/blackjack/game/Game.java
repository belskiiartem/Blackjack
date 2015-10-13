package belskii.artem.blackjack.game;

import java.util.ArrayList;
import java.util.List;

import belskii.artem.blackjack.dao.deck.Card;
import belskii.artem.blackjack.dao.deck.DeckDao;
import belskii.artem.blackjack.dao.deck.DeckDaoImplInMem;

public class Game {
	ArrayList <Card> bank= new ArrayList<Card>();
	ArrayList <Card> gamer= new ArrayList<Card>();
	ArrayList<ArrayList> party=new ArrayList<ArrayList>();
	DeckDao deck=null;

	public List startGame(long bet) {
		deck=new DeckDaoImplInMem();
		bank.add(deck.getOneCard());
		bank.add(deck.getOneCard());
		gamer.add(deck.getOneCard());
		gamer.add(deck.getOneCard());
		party.add(bank);
		party.add(gamer);
		return party;
	}
//	класс который реализует всю логику
//	поле Банк, , и поле игрок,
//	методы хит и стенд, по результатам которых определяется сколько у игрока осков на данный момент 

	public Object hit() {
		// TODO Auto-generated method stub
		return deck.getOneCard();
	}
	

}
