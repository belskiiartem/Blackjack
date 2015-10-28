package belskii.artem.blackjack.game;

import java.util.ArrayList;
import java.util.List;

import belskii.artem.blackjack.dao.account.AccountDao;
import belskii.artem.blackjack.dao.account.AccountDaoImplHiber;
import belskii.artem.blackjack.dao.journal.JournalDao;
import belskii.artem.blackjack.dao.journal.JournalDaoImplHiber;
import belskii.artem.blackjack.dao.party.Card;
import belskii.artem.blackjack.dao.party.PartyDao;
import belskii.artem.blackjack.dao.party.PartyDaoImplHiber;

public class Game {
	PartyDao party = new PartyDaoImplHiber();
	JournalDao journal = new JournalDaoImplHiber();
	AccountDao account = new AccountDaoImplHiber();
	private int BANK_STOP_COUNT=16;

	public void startGame(String partyId, long bet) {
		party.shuffleDeck(partyId, bet);
		party.bankHit(partyId);
		party.bankHit(partyId);
		party.gamerHit(partyId);
		party.gamerHit(partyId);
	}

	public int getGamerCount(String partyId) {
		return this.getCount(party.gamerCardsOnHend(partyId));
	}

	public int getBankCount(String partyId) {
		return this.getCount(party.bankCardsOnHend(partyId));
	}

	private int getCount(List<Card> cardOnHends) {
		int count = 0;
		ArrayList<String> numbers = new ArrayList<String>();
		ArrayList<String> picture = new ArrayList<String>();
		// prepare arrays for comparing
		numbers.add(0, "NaN");
		numbers.add(1, "NaN");
		numbers.add(2, "2");
		numbers.add(3, "3");
		numbers.add(4, "4");
		numbers.add(5, "5");
		numbers.add(6, "6");
		numbers.add(7, "7");
		numbers.add(8, "8");
		numbers.add(9, "9");
		numbers.add(10, "10");
		picture.add("Jack");
		picture.add("Queen");
		picture.add("King");
		// "Ace"=11 if count >21
		for (int i = 0; cardOnHends.size() > i; i++) {
			Card currentCard = cardOnHends.get(i);
			if (numbers.indexOf(currentCard.getRank()) > 0) {
				count += Integer.valueOf(currentCard.getRank());
			} else if (picture.indexOf(currentCard.getRank()) > 0) {
				count += 10;
			} else if (currentCard.getRank().equals("Ace")) {
				if (count <= 10) {
					count += 11;
				} else {
					count += 1;
				}
			}

		}
		return count;
	}

	public String getResult(String partyId, Long userId, Long accountId, Long bet){
		String result="";
			
		int bankCount=this.getBankCount(partyId);
		int gamerCount=this.getGamerCount(partyId);
		
		if (bankCount==21){
			result="bankWon";
			journal.newIvent(userId, "bankWon", accountId, bet);
			long currentBalance=account.getBalance(accountId);
			long newBalance=currentBalance-bet;
			account.updateBalance(accountId, newBalance);
			
		}
		else if (gamerCount==21){
			result="gamerWon";
			journal.newIvent(userId, "gamerWon", accountId, bet);
			
			long currentBalance=account.getBalance(accountId);
			long newBalance=currentBalance+bet;
			account.updateBalance(accountId, newBalance);
		}
		else if (bankCount>gamerCount & bankCount<=21){
			result="bankWon";
			journal.newIvent(userId, "bankWon", accountId, bet);
			
			long currentBalance=account.getBalance(accountId);
			long newBalance=currentBalance-bet;
			account.updateBalance(accountId, newBalance);
			
		} else if(gamerCount>bankCount & gamerCount<=21){
			result="gamerWon";
			journal.newIvent(userId, "gamerWon", accountId, bet);
			
			long currentBalance=account.getBalance(accountId);
			long newBalance=currentBalance+bet;
			account.updateBalance(accountId, newBalance);
			
		} else if(gamerCount>21& bankCount>21 ){
			result="noOneWon";
			journal.newIvent(userId, "gamerWon", accountId, bet);
			
		} else if(gamerCount>21){
			result="bankWon";
			journal.newIvent(userId, "bankWon", accountId, bet);
			
			long currentBalance=account.getBalance(accountId);
			long newBalance=currentBalance-bet;
			account.updateBalance(accountId, newBalance);
		} else if(bankCount>21){
			result="gamerWon";
			journal.newIvent(userId, "gamerWon", accountId, bet);
			long currentBalance=account.getBalance(accountId);
			long newBalance=currentBalance+bet;
			account.updateBalance(accountId, newBalance);

		} else {
			result="noOneWon";
			journal.newIvent(userId, "noOneWin", accountId, bet);

		}
		return result;
	}
	
	public List<Card> getBankCardsOnHend(String partyId){
		return party.bankCardsOnHend(partyId);
	}
	
	public List<Card> getGamerCardsOnHend(String partyId){
		return party.gamerCardsOnHend(partyId);
	}

	public int gamerHit(String partyId) {
		party.gamerHit(partyId);
		return this.getGamerCount(partyId);
	}

	public int bankHit(String partyId) {
		if (this.getBankCount(partyId)<=BANK_STOP_COUNT){
			party.bankHit(partyId);
		}
		return this.getBankCount(partyId);
	}
}