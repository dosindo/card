package cardList;
import javax.swing.ImageIcon;

import notguitcgcard.*;

public class Chals extends Card {
    private static ImageIcon card1Image = new ImageIcon(Main.class.getResource("../images/샬스.png"));
    String name = "Chals";
    int ad = 7;
    int hp = 9;

    public Chals() {
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
    public void attack(Player player,Enemyfield enemyfield) {
        setAtmname("deck1.mp3");
        getAtm().start();
        setatm(new Music(getAtmname(),false));
        boolean directattck = true;
        for(int i=0;i<enemyfield.getfieldsize();i++){
            if(this.getFieldnum()==enemyfield.field.get(i).fieldnum){
                int eneHp = enemyfield.field.get(i).getHp();
                eneHp-=getAd();
                if(eneHp<=0)
                {
                    setHp(9);

                }
                enemyfield.field.get(i).setHp(eneHp);
                directattck=false;
            }
        }
        if(directattck) {
            player.decreaseHealth(getAd());
        }
    }

}