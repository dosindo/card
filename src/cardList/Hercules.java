package cardList;
import javax.swing.ImageIcon;

import notguitcgcard.*;

public class Hercules extends Card {
    private static ImageIcon card1Image = new ImageIcon(Main.class.getResource("../images/헤라클레스.png"));
    String name = "Hercules";
    int ad = 15;
    int hp = 15;

    public Hercules() {
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

    int cost = 8;

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
    public void sommon(){
        Field field = getField();
        for(Card card: field.field){
            int r = card.getHp()-99;
            card.setHp(r);
        }
    }


}