package cardList;
import javax.swing.ImageIcon;

import notguitcgcard.*;

public class Priest extends Card{
    private static ImageIcon card1Image = new ImageIcon(Main.class.getResource("../images/성직자.png"));
    String name = "Priest";
    int ad = 1;
    int hp = 3;
    public Priest() {
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
    public void mainEffect(Field field, Enemyfield enemyfield){
        for(Card card: field.field){
            int r = card.getHp()+1;
            card.setHp(r);
        }
    }
}