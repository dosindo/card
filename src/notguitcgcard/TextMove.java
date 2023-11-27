package notguitcgcard;

import javax.swing.*;
import java.util.ArrayList;

public class TextMove extends Thread{
    JLabel vicima = new JLabel(new ImageIcon(Main.class.getResource("../images/승리.png")));
    JLabel lossima = new JLabel(new ImageIcon(Main.class.getResource("../images/패배.png")));
    JLabel battima = new JLabel(new ImageIcon(Main.class.getResource("../images/동물_플레이.png")));
    int stat;
    int x=500;
    int y=0;
    ArrayList<Integer> gameState;
    TextMove(int i, ArrayList<Integer> gameState){
        stat = i;
        this.gameState = gameState;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(stat==0||stat==1){
            if(stat==0){
                Music m = new Music("패배.mp3",false);
                m.start();
            }
            else if(stat==1){
                Music m =new Music("게임클리어5.mp3",false);
                m.start();
            }
        for(int i=0;i<350;i++){

            y+=1;
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for(int i=0;i<50;i++){
            y-=1;
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        vicima.setVisible(false);
        lossima.setVisible(false);
        gameState.set(8,1);
    }
}
