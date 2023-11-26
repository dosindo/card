package cardList;
import javax.swing.ImageIcon;

import notguitcgcard.*;

public class Wills extends Card {
    private static ImageIcon card1Image = new ImageIcon(Main.class.getResource("../images/윌스.png"));
    String name = "Wills";
    int ad = 5;
    int hp = 8;

    public Wills() {
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

    int cost = 5;

    public void setAd(int ad) {
        this.ad = ad;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getCost() {
        return cost;
    }

    public void attack(Player player) {
        player.decreaseHealth(ad);
        System.out.println("공격을 해땅 " + getAd());
    }

}