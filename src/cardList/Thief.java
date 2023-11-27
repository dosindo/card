package cardList;
import javax.swing.ImageIcon;

import notguitcgcard.*;

public class Thief extends Card{
    private static ImageIcon card1Image = new ImageIcon(Main.class.getResource("../images/도적.png"));
    String name = "Thief";
    int ad = 4;
    int hp = 1;
    public Thief() {
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
    public void attack(Player player, Enemyfield enemyfield) {
        setAtmname("deck1.mp3");
        setatm(new Music(getAtmname(),false));
        getAtm().start();
        player.decreaseHealth(getAd());

    }
}