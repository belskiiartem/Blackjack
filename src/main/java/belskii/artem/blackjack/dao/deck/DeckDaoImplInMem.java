package belskii.artem.blackjack.dao.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class DeckDaoImplInMem implements DeckDao{
	ArrayList<Card> deck = new ArrayList<Card>();
	
	public DeckDaoImplInMem(){
		this.initializeDeck();
	}
	
	public void shuffleDeck() {
		long seed = System.nanoTime();
		Collections.shuffle(deck, new Random(seed));				
	}

	public Card getOneCard() {
		Random random = new Random();
		return deck.get(random.nextInt(52));
	}
	
	private void initializeDeck(){
		String [] color = {"Clubs", "Diamonds", "Hearts", "Spades"};
		String [] rank = {"2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace"};
		for (int i = 0; i<color.length;i++){
			for (int i2=0;i2<rank.length;i2++){
				deck.add(new Card(color[i], rank[i2]));
			}
		}
	}
	
	public Card getCardFromSleeve(int id){
		return deck.get(id);
	}
	
}
