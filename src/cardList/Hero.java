package cardList;
import javax.swing.ImageIcon;

import notguitcgcard.Card;
import notguitcgcard.Enemyfield;
import notguitcgcard.Main;
import notguitcgcard.Player;

public class Hero extends Card{
    private static ImageIcon card1Image = new ImageIcon(Main.class.getResource("../images/영웅.png"));
    String name = "Hero";
    int ad = 10;
    int hp = 10;
    public Hero() {
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
    int cost = 10;
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