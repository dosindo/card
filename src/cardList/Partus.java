package cardList;
import javax.swing.ImageIcon;

import notguitcgcard.*;

public class Partus extends Card {
    private static ImageIcon card1Image = new ImageIcon(Main.class.getResource("../images/파르투스.png"));
    String name = "Partus";
    int ad = 7;
    int hp = 8;

    public Partus() {
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

    int cost = 7;

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