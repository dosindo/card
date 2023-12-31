package cardList;
import javax.swing.ImageIcon;

import notguitcgcard.*;

public class Chess extends Card {
    private static ImageIcon card1Image = new ImageIcon(Main.class.getResource("../images/체스.png"));
    String name = "Chess";
    int ad = 5;
    int hp = 2;

    public Chess() {
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
    public int invi = 1;
    public void attacked(int dam) {
        if (invi == 0) {
            int nowhp = getHp() - dam;
            setHp(nowhp);

        } else {
            invi = 0;
        }
    }
}