package cardList;
import java.awt.Image;

import javax.swing.ImageIcon;

import notguitcgcard.Card;
import notguitcgcard.Main;

public class Card1 extends Card{
	private static ImageIcon card1Image = new ImageIcon(Main.class.getResource("../images/humanbow.png"));
	String name = "card1";
	int ad = 1;
	int hp = 10;
	public Card1() {
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
