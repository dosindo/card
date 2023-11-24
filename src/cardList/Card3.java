package cardList;

import java.awt.Image;

import javax.swing.ImageIcon;

import notguitcgcard.Card;
import notguitcgcard.Main;

public class Card3 extends Card{
	private static ImageIcon card1Image = new ImageIcon(Main.class.getResource("../images/card3.png"));

	String name = "card3";
	int ad = 3;
	int hp=10;
	public Card3() {
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
