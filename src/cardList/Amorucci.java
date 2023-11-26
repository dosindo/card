package cardList;
import javax.swing.ImageIcon;

import notguitcgcard.*;

public class Amorucci extends Card {
    private static ImageIcon card1Image = new ImageIcon(Main.class.getResource("../images/아모루치.png"));
    String name = "Amorucci";
    int ad = 3;
    int hp = 10;

    public Amorucci() {
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
    public void attacked(int dam){
        int i=1;
        int nowhp = getHp()-dam;
        if (nowhp<=1 && i==1)
        {
            nowhp=1;
            i--;

        }
        setHp(nowhp);
        System.out.println(dam+"만큼 공격받음! 현재체력 "+getHp()+"임!");
    }

}