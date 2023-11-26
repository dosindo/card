package notguitcgcard;

import java.util.ArrayList;

public class Field {
	public ArrayList<Card> field = new ArrayList<Card>();

	public void toField(Card card) {
		field.add(card);
	}

	public int getfieldsize() {
		return field.size();
	}

/*
	public void attackPlayers(Player1 player1, Player2 player2) {
		for (Card card : field) {
			if (card.state.equals("infield")) {
				card.attack(player1);
				card.attack(player2);
			}
		}
	}
	*/
}