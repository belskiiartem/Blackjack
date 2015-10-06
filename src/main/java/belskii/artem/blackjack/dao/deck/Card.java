package belskii.artem.blackjack.dao.deck;

public class Card {
	
	String rank;
	String color;
	public Card(String color, String rank){
		this.setColor(color);
		this.setRank(rank);
	}
	
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
}
