package cardList;
//개인 업데이트오류 주석처리
import javax.swing.ImageIcon;

import notguitcgcard.Card;
import notguitcgcard.Main;
import notguitcgcard.Player;

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
	int cost = ad;
	public void setAd(int ad){this.ad = ad;}
	public void setHp(int hp){this.hp = hp;}
	public int getCost(){return cost;}
	public void attack(Player player) {
		player.decreaseHealth(ad);
		System.out.println("공격을 해땅 "+getAd());
		ad+=3;
	}
}
