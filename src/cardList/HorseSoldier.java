package cardList;
import javax.swing.ImageIcon;

import notguitcgcard.*;

public class HorseSoldier extends Card{
    private static ImageIcon card1Image = new ImageIcon(Main.class.getResource("../images/기마병.png"));
    String name = "HorseSoldier";
    int ad = 4;
    int hp = 5;
    public HorseSoldier() {
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
    public void attack(Enemycard enemycard) {

    }
    public void attack(Player player, Enemyfield enemyfield) {
        boolean directattck = true;
        for(int i=0;i<enemyfield.getfieldsize() ;i++){
            if(this.getFieldnum()-1==enemyfield.field.get(i).fieldnum&&this.getFieldnum()+1==enemyfield.field.get(i).fieldnum&&this.getFieldnum()==enemyfield.field.get(i).fieldnum){
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