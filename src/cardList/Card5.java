package cardList;
import java.awt.Image;

import javax.swing.ImageIcon;

import notguitcgcard.Card;
import notguitcgcard.Main;

public class Card5 extends Card{
	private static ImageIcon card1Image = new ImageIcon(Main.class.getResource("../images/card5.png"));

	String name = "card5";
	int ad = 5;
	int hp=10;
	public Card5() {
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
