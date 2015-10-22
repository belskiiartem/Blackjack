package belskii.artem.blackjack.dao.party;

import java.io.Serializable;

public class Card implements Serializable {

	private String rank;
	private String color;

	public Card() {
	}

	public Card(String color, String rank) {
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
