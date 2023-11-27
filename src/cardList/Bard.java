package cardList;
import javax.swing.ImageIcon;

import notguitcgcard.*;

public class Bard extends Card{
    private static ImageIcon card1Image = new ImageIcon(Main.class.getResource("../images/바드.png"));
    String name = "Bard";
    int ad = 0;
    int hp = 6;
    public Bard() {
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
        setAtmname("deck1.mp3");
        setatm(new Music(getAtmname(),false));
        getAtm().start();
        player.decreaseHealth(ad);
        System.out.println("공격을 해땅 "+getAd());
    }
    public void mainEffect(Field field, Enemyfield enemyfield){
        for(Card card: field.field){
            int r = card.getAd()+1;
            card.setAd(r);
        }
    }

}