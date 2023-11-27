package cardList;
import javax.swing.ImageIcon;

import notguitcgcard.*;

public class Pororos extends Card {
    private static ImageIcon card1Image = new ImageIcon(Main.class.getResource("../images/뽀로로스.png"));
    String name = "Pororos";
    int ad = 4;
    int hp = 5;

    public Pororos() {
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
    public void attack(Player player,Enemyfield enemyfield) {
        setatm(new Music(getAtmname(),false));
        setAtmname("deck1.mp3");
        getAtm().start();

        boolean directattck = true;
        for(int i=0;i<enemyfield.getfieldsize();i++){
            if(this.getFieldnum()==enemyfield.field.get(i).fieldnum){
                int eneHp = enemyfield.field.get(i).getHp();
                eneHp-=getAd();
                enemyfield.field.get(i).setHp(eneHp);
                directattck=false;

            }
        }
        if(directattck) {
            player.decreaseHealth(getAd());
        }
        player.decreaseHealth(2);
    }

}