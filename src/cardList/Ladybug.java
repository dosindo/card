package cardList;
import javax.swing.ImageIcon;

import notguitcgcard.*;

public class Ladybug extends Card {
    private static ImageIcon card1Image = new ImageIcon(Main.class.getResource("../images/레이디버그.png"));
    String name = "Ladybug";
    int ad = 2;
    int hp = 2;

    public Ladybug() {
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

    int cost = 2;

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