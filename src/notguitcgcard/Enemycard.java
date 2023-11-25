package notguitcgcard;

import javax.swing.*;
import java.awt.*;

public class Enemycard extends Thread{
    JButton cardb = new JButton();
    int x;
    int y;
    ImageIcon cardImage;
    int hp;
    int ad;
    int fieldnum;
    String state2;
    JLabel cardAdLb;
    JLabel cardHpLb;
    String cardname;
    Enemycard(String cardname,int hp,int ad,int fieldnum){
        this.cardname = cardname;
        String src = "../images/"+cardname+".png";
        cardImage = new ImageIcon(Main.class.getResource(src));
        cardb.setIcon(cardImage);
        this.hp = hp;
        this.ad = ad;
        this.fieldnum = fieldnum;
        x=50+fieldnum*150;
        y=300-230;
        cardAdLb = new JLabel(ad+"");
        cardHpLb = new JLabel(hp+"");
        cardAdLb.setOpaque(true); //Opaque값을 true로 미리 설정해 주어야 배경색이 적용된다.
        cardAdLb.setBackground(Color.pink);
        cardHpLb.setOpaque(true); //Opaque값을 true로 미리 설정해 주어야 배경색이 적용된다.
        cardHpLb.setBackground(Color.pink);
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void run(){
        try{
        while(true){
            cardAdLb.setText(ad+"");
            cardHpLb.setText(hp+"");
            if(getHp()<=0){
                cardb.setVisible(false);
                cardAdLb.setVisible(false);
                cardHpLb.setVisible(false);
                setState2("notinscreen");
            }
        }

        }
        catch(Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void setState2(String state) {
        this.state2 = state;
    }
    public String getState2(){
        return state2;
    }

    public void setAd(int ad) {
        this.ad = ad;
    }
    public int getAd(){
        return ad;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }
}
