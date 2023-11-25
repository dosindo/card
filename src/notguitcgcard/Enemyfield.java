package notguitcgcard;

import java.util.ArrayList;

public class Enemyfield extends Thread{
    ArrayList<Enemycard> field = new ArrayList<Enemycard>();

    public void toField(String cardname,int hp, int ad, int fieldnum) {
        Enemycard card = new Enemycard(cardname,hp,ad,fieldnum);
        card.start();
        field.add(card);
        card.setState2("inscreen");
    }
    public int getfieldsize() {
        return field.size();
    }
    public void run(){
        try{
        while(true){
            field.removeIf(card -> card.getState2().equals("notinscreen"));
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
        catch(Exception e) {
        System.err.println(e.getMessage());
    }
    }
}