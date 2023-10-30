package notguitcgcard;

import java.util.ArrayList;

public class Hand {
	ArrayList<Card> hand = new ArrayList<Card>();
	public void toHand(Card card) {
		hand.add(card);
	}
	public void sommon(Card card) {
		hand.remove(card);
	}
	public int gethandsize() {
		return hand.size();
	}
}
