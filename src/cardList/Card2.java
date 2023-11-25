package cardList;

import java.awt.Image;

import javax.swing.ImageIcon;

import notguitcgcard.Card;
import notguitcgcard.Main;

public class Card2 extends Card{
	private static ImageIcon card1Image = new ImageIcon(Main.class.getResource("../images/card2.png"));

	String name = "card2";
	int ad = 2;
	int hp =10;
	public Card2() {
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
	int cost = ad;
	public void setAd(int ad){this.ad = ad;}
	public void setHp(int hp){this.hp = hp;}
	public int getCost(){return cost;}
}
