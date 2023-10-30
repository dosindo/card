package cardList;

import java.awt.Image;

import javax.swing.ImageIcon;

import notguitcgcard.Card;
import notguitcgcard.Main;

public class Card8 extends Card{
	private static ImageIcon card1Image = new ImageIcon(Main.class.getResource("../images/card8.png"));
	String name = "card8";
	int ad = 8;
	public Card8() {
		super(card1Image);
	}
	public String getCardName() {
		return name;
	}
	public int getAd() {
		return ad;
	}
	
}
