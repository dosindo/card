package cardList;
import javax.swing.ImageIcon;

import notguitcgcard.*;

public class Welchs extends Card {
    private static ImageIcon card1Image = new ImageIcon(Main.class.getResource("../images/웰치스.png"));
    String name = "Welchs";
    int ad = 4;
    int hp = 4;

    public Welchs() {
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
    public void attacked(int dam){
        int nowhp = getHp()-dam;
        if(nowhp<=0) {
            getGameState().set(5, getGameState().get(5) + 5);
        }

        setHp(nowhp);
        System.out.println(dam+"만큼 공격받음! 현재체력 "+getHp()+"임!");
    }

}