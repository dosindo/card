package cardList;
import javax.swing.ImageIcon;

import notguitcgcard.*;

public class Woos extends Card {
    private static ImageIcon card1Image = new ImageIcon(Main.class.getResource("../images/우스.png"));
    String name = "Woos";
    int ad = 2;
    int hp = 10;

    public Woos() {
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

    int cost = 6;

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

    @Override
    public void mainEffect(Field field, Enemyfield enemyfield) {
        int i = getHp()+1;
        setHp(i);
    }


    private void atm(String s, boolean b) {
    }
}