package notguitcgcard;

import java.util.ArrayList;

public class Field {
	ArrayList<Card> field = new ArrayList<Card>();
	public void toField(Card card) {
		field.add(card);
	}
	public int getfieldsize(){
		return field.size();
	}
}
