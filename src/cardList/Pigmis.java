package cardList;
import javax.swing.ImageIcon;

import notguitcgcard.*;

public class Pigmis extends Card {
    private static ImageIcon card1Image = new ImageIcon(Main.class.getResource("../images/피그미스.png"));
    String name = "Pigmis";
    int ad = 1;
    int hp = 12;

    public Pigmis() {
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

    int cost = 3;

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