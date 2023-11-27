package notguitcgcard;

import java.util.ArrayList;

public class BattleManager extends Thread{
    ArrayList<Integer> gameState;
    Field field;
    Enemyfield enemyfield;
    Player player1;
    Player player2;
    boolean isgame = false;
    BattleManager(ArrayList<Integer> gamestate, Field field, Enemyfield enemyfield, Player player1, Player player2){
        this.gameState = gamestate;
        this.field = field;
        this.enemyfield = enemyfield;
        this.player1 = player1;
        this.player2 = player2;
    }

    public void setIsgame(boolean isgame) {
        this.isgame = isgame;
    }

    @Override
    public void run() {
        while(isgame){
            if(gameState.get(1)==2){
                if (gameState.get(6) == gameState.get(7)) {
                    for (Card card : field.field) {
                        card.attack(player2, enemyfield);

                    }
                    gameState.set(0,-1);
                }
                else{
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    for (Enemycard enemycard : enemyfield.field){
                        boolean directattck = true;
                        for(Card card : field.field){
                            if(card.fieldnum==enemycard.fieldnum && enemycard.getHp()>0){
                                card.attacked(enemycard.getAd());
                                System.out.println(enemycard.getAd()+"만큼 공격함!");
                                directattck=false;
                            }

                        }
                        if(directattck) {
                            player1.decreaseHealth(enemycard.getAd());

                        }
                    }

                }
                System.out.println("52811369");
                gameState.set(1,3);
            }

            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
/////////////////////////
				/*
				boolean directattck = true;
				for(int i=0;i<enemyfield.getfieldsize();i++){
					if(card.fieldnum==enemyfield.field.get(i).fieldnum){
						card.attack(enemyfield.field.get(i));
						directattck=false;
					}
				}
				if(directattck) {
					if (gameState.get(WHOAMI) == 1) {
						card.attack(player2);
					} else {
						card.attack(player1);
					}
				}
				*/
///////////////////////////