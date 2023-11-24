package cardList;

import java.awt.Image;

import javax.swing.ImageIcon;

import notguitcgcard.Card;
import notguitcgcard.Main;

public class Card7 extends Card{
	private static ImageIcon card1Image = new ImageIcon(Main.class.getResource("../images/card7.png"));
	String name = "card7";
	int ad = 7;
	int hp = 10;
	public Card7() {
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
