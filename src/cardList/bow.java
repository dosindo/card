package cardList;
import javax.swing.ImageIcon;

import notguitcgcard.Card;
import notguitcgcard.Main;
import notguitcgcard.Player;

public class bow extends Card{
    private static ImageIcon card1Image = new ImageIcon(Main.class.getResource("../images/humanbow.png"));
    String name = "card1";
    int ad = 1;
    int hp = 10;
    public bow() {
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
    public void attack(Player player) {
        player.decreaseHealth(ad);
        System.out.println("공격을 해땅 "+getAd());
        ad+=3;
    }
}
