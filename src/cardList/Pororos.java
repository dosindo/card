package cardList;
import javax.swing.ImageIcon;

import notguitcgcard.*;

public class Pororos extends Card {
    private static ImageIcon card1Image = new ImageIcon(Main.class.getResource("../images/뽀로로스.png"));
    String name = "Pororos";
    int ad = 4;
    int hp = 5;

    public Pororos() {
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

    int cost = 4;

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
        getPlayer2().setHealth(getPlayer2().getHealth()-2);
    }
}