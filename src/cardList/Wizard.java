package cardList;
import javax.swing.ImageIcon;

import notguitcgcard.*;

public class Wizard extends Card{
    private static ImageIcon card1Image = new ImageIcon(Main.class.getResource("../images/마법사.png"));
    String name = "Wizard";
    int ad = 2;
    int hp = 2;
    public Wizard() {
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

    public void attack(Player player,Enemyfield enemyfield) {
        setAtmname("deck1.mp3");
        setatm(new Music(getAtmname(),false));
        getAtm().start();

        boolean directattck = true;
        for(int i=0;i<enemyfield.getfieldsize();i++){

                int eneHp = enemyfield.field.get(i).getHp();
                eneHp-=getAd();
                enemyfield.field.get(i).setHp(eneHp);
                directattck=false;

        }
        if(directattck) {
            player.decreaseHealth(getAd());
        }
    }
}