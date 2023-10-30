package notguitcgcard;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	ArrayList<Card> deck = new ArrayList<Card>();
	public void toDeck(Card card) {
		deck.add(card);
	}
	public void draw(int num,Hand hand,int newcardx) {
		for(int i=0;i<num;i++) {
			Collections.shuffle(deck);
			if(deck.size()==0){
				System.out.println("덱이 없음");
			}
			else {
			hand.toHand(deck.get(0));
			deck.get(0).draw(newcardx);
			deck.remove(0);
			}
		}
	}
}
