package notguitcgcard;

import java.util.ArrayList;

public class Field {
	ArrayList<Card> field = new ArrayList<Card>();
	public void toDeck(Card card) {
		field.add(card);
	}
}
