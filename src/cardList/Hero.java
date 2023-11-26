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
    public void attack(Player player, Enemyfield enemyfield) {
        boolean directattck = true;
        for(int i=0;i<enemyfield.getfieldsize();i++){
            if(this.getFieldnum()==enemyfield.field.get(i).fieldnum){
                int eneHp = enemyfield.field.get(i).getHp();
                eneHp-=getAd();
                enemyfield.field.get(i).setHp(eneHp);
                directattck=false;
            }
        }
        int i = getAd();


        if(directattck) {
            player.decreaseHealth(getAd());

        }
        setAd(i+1);
    }
}