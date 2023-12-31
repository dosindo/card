package notguitcgcard;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
public class Card extends Thread{
	int ad;
	int x,y;
	int newcardx;
	int fieldnum;
	int hp;
	Music sm;
	String atmname;
	Music atm;
	ArrayList<Integer> gameState;
	Field field;
	Enemyfield enemyfield;
	Hand hand;
	String state = "notinscreen";
	ImageIcon card1Image;
	JButton cardb = new JButton();
	JLabel cardAdLb;
	JLabel cardHpLb;
	Player player2;
	Player player1;

	public void setAtmname(String atmname) {
		this.atmname = atmname;
	}
	public String getAtmname(){
		return atmname;
	}
	public void setSm(Music sm){
		this.sm = sm;
	}

	public void setatm(Music atm){
		this.atm = atm;
	}
	public Player getPlayer2() {
		return player2;
	}
	public void setPlayer2(Player player2){
		this.player2 = player2;
	}
	public Player getPlayer1() {
		return player1;
	}
	public void setPlayer1(Player player1){
		this.player1 = player1;
	}

	public Enemyfield getEnemyfield() {
		return enemyfield;
	}
	public void setEnemyfield(Enemyfield enemyfield){
		this.enemyfield = enemyfield;
	}
	public void setGameState(ArrayList<Integer> gameState) {
		this.gameState = gameState;
	}
	public void setField(Field field){
		this.field = field;
	}
	public void setHand(Hand hand){
		this.hand = hand;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void draw(int newcardx) {
		x=1000;
		y=720-230+100;
		this.newcardx = newcardx;
		setState("drawing");
	}
	protected Card(ImageIcon card1Image){
		this.card1Image = card1Image;
		cardb.setIcon(card1Image);

		cardb.setBounds(100,500,150,230);//x,y,크기
		cardb.setBorderPainted(false);
		cardb.setContentAreaFilled(false);
		cardb.setFocusPainted(false);
		cardb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				//cardb.setIcon(card1Image);
				if(state.equals("inhand")) {
				y=720-230;
				cardb.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				//cardb.setIcon(card1Image);
				if(state.equals("inhand")) {
				y=720-230+100;
				cardb.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				if(gameState.get(6)==gameState.get(7))//WHOSTURN==WHOAMI?
				{
					if (gameState.get(0) > 0 && gameState.get(3) == 0 && state.equals("inhand") && gameState.get(5)>=getCost()) {
						setState("sommoning");
						gameState.set(3, 1);
						y = 720 - 230 - 100;
					} else if (state.equals("sommoning") && gameState.get(3) != 0) {
						setState("inhand");
						gameState.set(3, 0);
						y = 720 - 230 + 100;
					}
				}
				System.out.println(state);
			}
		});
		cardAdLb = new JLabel(ad+"");
		cardHpLb = new JLabel(hp+"");
		cardAdLb.setOpaque(true); //Opaque값을 true로 미리 설정해 주어야 배경색이 적용된다.
		cardAdLb.setBackground(Color.pink);
		cardHpLb.setOpaque(true); //Opaque값을 true로 미리 설정해 주어야 배경색이 적용된다.
		cardHpLb.setBackground(Color.pink);
	}
	
	public void run() {
		try {
			while (true) {
				if(getHp()<=0){
					destroy();
					fieldnum = 1000;
					setState("notinscreen");
				}
				cardAdLb.setText(getAd()+"");
				cardHpLb.setText(getHp()+"");
				if (state.equals("notinscreen")) {
					cardb.setVisible(false);
					cardAdLb.setVisible(false);
					cardHpLb.setVisible(false);
					cardb.setBounds(0,0,0,0);
				} else if (state.equals("drawing")) {
					cardb.setVisible(true);
					cardAdLb.setVisible(true);
					cardHpLb.setVisible(true);
					while (true) {

						if (x != newcardx) {
							x -= 1;
						} else {
							setState("inhand");
							break;
						}
						try {
							Thread.sleep(1);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				} else if (state.equals("inhand")) {

				} else if(state.equals("sommoning")&&gameState.get(3)==2) {
					
					int ac = gameState.get(0);
					gameState.set(0, ac-1);
					state="infield";
					this.sommon();
					int nowgold = gameState.get(5);
					gameState.set(5,nowgold-getCost());
					x=50+gameState.get(2)*150;
					y=300;
					fieldnum = gameState.get(2);
					gameState.set(3, 0);
					gameState.set(2, 0);
					gameState.set(4,1);
					hand.sommon(this);
					field.toField(this);
				}else if(state.equals("sommoning")&&gameState.get(3)==0) {
					
					state="inhand";
					
					y=720-130;
					gameState.set(3, 0);
					gameState.set(2, 0);
				}
				try {
					Thread.sleep(1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	public void setY(int y){
		this.y = y;
	}
	public ArrayList<Integer> getGameState(){
		return gameState;
	}
	public String getCardName() {
		return null;
	}
	public int getAd() {
		return 0;
	}
	public int getHp() {
		return 0;
	}

	public void attack(Player player) {
		player.decreaseHealth(ad);
		System.out.println("공격을 해땅 "+getAd());
	}
	public void attack(Enemycard enemycard) {
		int eneHp = enemycard.getHp();
		eneHp-=getAd();
		enemycard.setHp(eneHp);
	}
	public int getFieldnum(){
		return this.fieldnum;
	}
	public Music getAtm(){
			return atm;
	}

	public Music getSm() {
		return sm;
	}


	public void attack(Player player, Enemyfield enemyfield) {
		    setAtmname("deck1.mp3");
			setatm(new Music(getAtmname(),false));
			atm.start();

		setatm(new Music(getAtmname(),false));
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

	}
	public void mainEffect(Field field, Enemyfield enemyfield){
		return;
	}
	public void setAd(int i){
		this.ad = i;
	}
	public void attacked(int dam){
		int nowhp = getHp()-dam;
		setHp(nowhp);
		System.out.println(dam+"만큼 공격받음! 현재체력 "+getHp()+"임!");
	}
	public void destroy(){
		return;
	}
	public void setHp(int hp){this.hp = hp;}


	public void sommon() {
		sm.start();
		//오버라이딩
	}

	public Field getField() {
		return field;
	}

	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getCost(){
		return 0;
	}
}
