package cardList;

import java.awt.Image;

import javax.swing.ImageIcon;

import notguitcgcard.Card;
import notguitcgcard.Main;

public class Card4 extends Card{
	private static ImageIcon card1Image = new ImageIcon(Main.class.getResource("../images/card4.png"));

	String name = "card4";
	int ad = 4;
	public Card4() {
		super(card1Image);
	}
	public String getCardName() {
		return name;
	}
	public int getAd() {
		return ad;
	}
}
