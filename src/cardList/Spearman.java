package cardList;
import javax.swing.ImageIcon;

import notguitcgcard.Card;
import notguitcgcard.Main;
import notguitcgcard.Player;

public class Spearman extends Card{
    private static ImageIcon card1Image = new ImageIcon(Main.class.getResource("../images/창병.png"));
    String name = "Spearman";
    int ad = 3;
    int hp = 10;
    public Spearman() {
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
    public void setAd(int ad){
        this.ad = ad;
    }
    public void setHp(int hp){
        this.hp = hp;
    }
    public int getCost(){
        return cost;
    }
    public void attack(Player player) {
        player.decreaseHealth(ad);
        System.out.println("공격을 해땅 "+getAd());
    }
}