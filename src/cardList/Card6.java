package cardList;

import java.awt.Image;

import javax.swing.ImageIcon;

import notguitcgcard.Card;
import notguitcgcard.Main;

public class Card6 extends Card{
	private static ImageIcon card1Image = new ImageIcon(Main.class.getResource("../images/card6.png"));

	String name = "card6";
	int ad = 6;
	int hp=10;
	public Card6() {
		super(card1Image);
	}
	public String getCardName() {
		return name;
	}
	public int getAd() {
		return ad;
	}
	public int getHp() {
		return hp;
	}
}
