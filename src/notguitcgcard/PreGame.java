package notguitcgcard;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import cardList.*;

public class PreGame extends JFrame{
	private Player player1;
	private Player player2;

	private Image screenImage;//더블버터링을 위한
	private Graphics screenGraphic;
	private Image Background = new ImageIcon(Main.class.getResource("../images/backback.jpg")).getImage();//인트로 사진저장
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menubar.png")));
	
	private ImageIcon exitButtonbasic = new ImageIcon(Main.class.getResource("../images/closeingbutten.png"));
	private ImageIcon exitButtonentered = new ImageIcon(Main.class.getResource("../images/closebutten.png"));
	private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/startbuttonEnter.png"));
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/startbutton.png"));
	private ImageIcon deckscButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/deckButtonEnter.png"));
	private ImageIcon deckscButtonBasicImage = new ImageIcon(Main.class.getResource("../images/deckButton.png"));
	private ImageIcon goldbuttonImage = new ImageIcon(Main.class.getResource("../images/골드획득.png"));

	
	
	private Image deckimage = new ImageIcon(Main.class.getResource("../images/deck.png")).getImage();
	private Image field1image = new ImageIcon(Main.class.getResource("../images/field1.png")).getImage();

	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton exitButton = new JButton(exitButtonbasic);
	private JButton goldButton = new JButton(goldbuttonImage);
	private JButton deckScreenButton = new JButton(deckscButtonBasicImage);
	private JLabel la;
	private boolean isMainScreen = false;
	//private boolean isDeckScreen = false;
	private boolean isGameScreen = false;
	private Music introMusic = new Music("임시배경음악.mp3",true);
	private Music gameMusic = new Music("통통톡톡.mp3",true);
	private int newcardx=100;
	private int mouseX,mouseY;//마우스의 위치
	private Image selectedImage;
	private Image titleImage;
	
	Container container = getContentPane();
	int nowDeck=1;
	final int SCREEN_WIDTH = 1280; //1540;
	final int SCREEN_HEIGHT = 720; //815;
	
	final int DRAWPHASE = 0;
	final int MAINPHASE = 1;
	final int BATTLEPHASE = 2;
	final int ENDPHASE = 3;
	
	
	final int ACT = 0;
	final int NOWTURN = 1;
	final int GOLD = 5;

	final int WHOSTURN = 6;
	final int WHOAMI = 7;
	final int TURNP1 = 1;

	final int TURNP2 = 2;

	final int P1 = 1;
	final int P2 = 2;
	ArrayList<Card> allCard = new ArrayList<Card>();
	ArrayList<Integer> gameState = new ArrayList<Integer>();
	//게임 상태를 저장하는 arraylist임.
	//첫 번째는 남은 행동,
	//두 번째는 현재 페이즈,
	//세 번째는 소환할 위치(0이면 선택안됨 1이면 첫번째에 소환).
	//네 번째는 소환 단계(0이면 실행안됨 1이면 소환시작&카드 선택중 2면 소환 필드선택중)
	//다섯 번째는 checkx할지말지
	//여섯 번째는 골드
	//일곱 번째는 현재 차례를 진행중인 플레이어
	//여덟 번째는 본인이 플레이어1인지 2인지
	public Hand hand;
	public Field field;
	public Enemyfield enemyfield;
	public Deck deck;
	FieldButton newfb1 = new FieldButton(gameState,1);
	FieldButton newfb2 = new FieldButton(gameState,2);
	FieldButton newfb3 = new FieldButton(gameState,3);
	FieldButton newfb4 = new FieldButton(gameState,4);

	JButton deck1;
	JButton deck2;
	JButton mainButton;

	public static void printCard(ArrayList<Card> allCard) {//그냥 콘솔출력임.디버깅용
		for(int i=0;i<allCard.size();i++) {
			System.out.println(allCard.get(i).getName());
		}
	}
	public PreGame() {
		player1 = new Player(45, 1);
		player2 = new Player(45, 1);
		deck = new Deck();
		hand = new Hand();
		field = new Field();
		enemyfield = new Enemyfield();
		Card1 card1 = new Card1();
		Card2 card2 = new Card2();
		Card3 card3 = new Card3();
		Card4 card4 = new Card4();
		Card5 card5 = new Card5();
		Card6 card6 = new Card6();
		Card7 card7 = new Card7();
		Card8 card8 = new Card8();
		Card9 card9 = new Card9();
		
		allCard.add(card1);
		allCard.add(card2);
		allCard.add(card3);
		allCard.add(card4);
		allCard.add(card5);
		allCard.add(card6);
		allCard.add(card7);
		allCard.add(card8);
		allCard.add(card9);
		
		introMusic.start();
		setUndecorated(true);//메뉴바 안보임
		setTitle("tcg");
		setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
		setResizable(false);//사이즈변경불가능
		setLocationRelativeTo(null);//가운데로
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//완전종료
		setVisible(true);//보이게
		setBackground(new Color(0,0,50,0));//배경하양
		setLayout(null);//위치 고정
		
		exitButton.setBounds(1240,0,30,30);//x,y,크기 1505, 0 ...
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonentered);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonbasic);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				
				System.exit(0);
			}
		});
		add(exitButton);
		
		startButton.setBounds(100,420,400,100); // 590, 400,...
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("bentered.mp3",false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("tfile.mp3",false);
				buttonEnteredMusic.start();
				gameStart();
			}
		});
		add(startButton);
		
		deckScreenButton.setBounds(100,550,400,100); // 590 ...
		deckScreenButton.setBorderPainted(false);
		deckScreenButton.setContentAreaFilled(false);
		deckScreenButton.setFocusPainted(false);
		deckScreenButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				deckScreenButton.setIcon(deckscButtonEnteredImage);
				deckScreenButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("bentered.mp3",false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				deckScreenButton.setIcon(deckscButtonBasicImage);
				deckScreenButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("tfile.mp3",false);
				buttonEnteredMusic.start();
				gotoDeckScreen();
			}
		});
		add(deckScreenButton);
		
		
		goldButton.setBounds(1080,200,60,20);
		goldButton.setBorderPainted(false);
		goldButton.setContentAreaFilled(false);
		goldButton.setFocusPainted(false);
		goldButton.setVisible(false);
		goldButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				goldButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				goldButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				if(gameState.get(0)>0) {
					int nowitem = gameState.get(0);
					nowgetGold();
					gameState.set(0, nowitem-1);
				}
			}
		});
		add(goldButton);
		
		
		
		

		menuBar.setBounds(0,0,1280,30);//위치와 크기 0, 0, 1540...
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e)
			{
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter(){
			@Override
			public void mouseDragged(MouseEvent e) {
			int x = e.getXOnScreen();
			int y = e.getYOnScreen();
			setLocation(x-mouseX,y-mouseY);
			}
		});
		add(menuBar);//jframe에 추가

		newfb1.setVisible(false);
		newfb2.setVisible(false);
		newfb3.setVisible(false);
		newfb4.setVisible(false);
		add(newfb1);
		add(newfb2);
		add(newfb3);
		add(newfb4);
		container.setComponentZOrder(newfb1, 1);
		container.setComponentZOrder(newfb2, 1);
		container.setComponentZOrder(newfb3, 1);
		container.setComponentZOrder(newfb4, 1);

		enemyfield.start();
		/*
		for (int i=0;i<9;i++) {
			deck.toDeck(allCard.get(i));
		}
		deck.toDeck(new HorseSoldier());*/

	}
	public void nowgetGold() {
		int nowgold = gameState.get(5);
		gameState.set(5, nowgold+1);
	}

	public void checkcardx() {
		int nowx = 100;
		for(int i=0;i<hand.hand.size();i++) {
			
			if(hand.hand.get(i).state.equals("inhand")) {
				if(hand.hand.get(i).x!=nowx) {
				hand.hand.get(i).x=nowx;
				}
				nowx += 200;
			}
		}
	}
	public void gotoDeckScreen() {
		// isMainScreen = false;
		// isDeckScreen = true;
		deckScreenButton.setVisible(false);
		startButton.setVisible(false);
		Background = new ImageIcon(Main.class.getResource("../images/경태형.jpg")).getImage();

		// 이미지를 표시하기 위한 JLabel 생성
		ImageIcon imageIcon = new ImageIcon(Main.class.getResource("../images/마법사.png"));
		JLabel imageLabel = new JLabel(imageIcon);
		imageLabel.setBounds(50, 110, imageIcon.getIconWidth(), imageIcon.getIconHeight()); // 위치와 크기 설정
		imageLabel.setVisible(false); // 처음에는 숨김 상태로 설정
		add(imageLabel); // 화면에 추가

		// 새로운 버튼 생성
		deck1 = new JButton("버튼1");
		deck2 = new JButton("버튼2");

		// 버튼 위치 및 크기 설정
		deck1.setBounds(50, 50, 100, 50); // 위치와 크기를 원하는 값으로
		deck2.setBounds(170, 50, 100, 50); // 위치와 크기를 원하는 값으로

		// 메인으로 가는 버튼 생성
		mainButton = new JButton("메인으로");
		mainButton.setBounds(300, 50, 100, 50); // 위치와 크기를 원하는 값으로

		// 메인으로 가는 버튼 이벤트 설정
		mainButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 메인으로 가는 버튼을 클릭하면 메소드 호출
				backTomainFromDeck();
			}
		});

		// 메인으로 가는 버튼을 화면에 추가
		add(mainButton);

		// 버튼 이벤트 설정
		deck1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// deck1 버튼을 클릭하면 nowdeck 값을 1로 설정
				nowDeck = 1;
				makeDeck1();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// 마우스가 버튼 위에 올라갔을 때 이미지 보이게 설정
				imageLabel.setVisible(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// 마우스가 버튼에서 벗어났을 때 이미지 숨기게 설정
				imageLabel.setVisible(false);
			}
		});

		deck2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// deck2 버튼을 클릭하면 nowdeck 값을 2로 설정
				nowDeck = 2;
				makeDeck2();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// 마우스가 버튼 위에 올라갔을 때 이미지 보이게 설정
				imageLabel.setVisible(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// 마우스가 버튼에서 벗어났을 때 효과
				imageLabel.setVisible(false);
			}
		});

		// 버튼을 화면에 추가
		add(deck1);
		add(deck2);
	}

	public void backTomainFromDeck() {
		isMainScreen=true;
		isGameScreen=false;
		Background = new ImageIcon(Main.class.getResource("../images/backback.jpg")).getImage();
		startButton.setVisible(true);
		deckScreenButton.setVisible(true);

		deck1.setVisible(false);
		deck2.setVisible(false);
		mainButton.setVisible(false);
	}

	public void backToMain(){
		gameMusic.close();
		introMusic = new Music("임시배경음악.mp3",true);
		introMusic.start();
		isMainScreen=true;
		isGameScreen=false;
		Background = new ImageIcon(Main.class.getResource("../images/backback.jpg")).getImage();
		startButton.setVisible(true);
		deckScreenButton.setVisible(true);
		goldButton.setVisible(false);
		gameState.clear();
		newfb1.setVisible(false);
		newfb2.setVisible(false);
		newfb3.setVisible(false);
		newfb4.setVisible(false);
		mainButton.setVisible(false);
		la.setVisible(false);

		//deck.deck.clear();
		for(Card card : hand.hand){
			card.cardb.setVisible(false);
			card.cardAdLb.setVisible(false);
			card.cardHpLb.setVisible(false);
		}
		for(Card card : field.field){
			card.cardb.setVisible(false);
			card.cardAdLb.setVisible(false);
			card.cardHpLb.setVisible(false);
		}
		hand.hand.clear();
		field.field.clear();
		for(Enemycard card: enemyfield.field){
			card.cardb.setVisible(false);
			card.cardAdLb.setVisible(false);
			card.cardHpLb.setVisible(false);
		}
		enemyfield.field.clear();
	}
	public void gameStart() {
		makeDeck2();
		newcardx = 100;
		player2.setHealth(45);
		player1.setHealth(45);
		introMusic.close();
		isMainScreen = false;
		startButton.setVisible(false);
		deckScreenButton.setVisible(false);
		Background = new ImageIcon(Main.class.getResource("../images/임시배경2.jpg")).getImage();
		
		hand.hand.clear();
		field.field.clear();

		newfb1.setVisible(true);
		newfb2.setVisible(true);
		newfb3.setVisible(true);
		newfb4.setVisible(true);
		goldButton.setVisible(true);
		gameMusic = new Music("통통톡톡.mp3",true);
		gameMusic.start();
		gameState.clear();
		gameState.add(1);
		gameState.add(DRAWPHASE);
		gameState.add(0);
		gameState.add(0);
		gameState.add(0);
		gameState.add(0);
		gameState.add(0);
		gameState.add(0);
		////////////////////////실험을 위한 코드이므로 서버구현시 반드시 반드시 지워야함.
		gameState.set(WHOAMI,1);

		////////////////////////
		gameState.set(WHOSTURN,1);
		isGameScreen = true;
		gameState.set(GOLD, 10);
		la = new JLabel("남은 행동: "+gameState.get(0)+" 남은 골드: "+gameState.get(5)+" 플레이어 남은체력: "+player1.getHealth()+" 적 남은 체력: "+player2.getHealth());
		la.setVisible(true);
		la.setLocation(800, 50);
		la.setSize(500, 50);
		add(la);
		
		for(int i=0;i<deck.deck.size();i++) {
			deck.deck.get(i).start();
			System.out.println(deck.deck.get(i).getCardName());
		}
		hand.hand.clear();
		System.out.println(gameState.get(WHOAMI)+"임");

		draw();
		draw();
		draw();
		turn();

		mainButton = new JButton("메인으로");
		mainButton.setBounds(50, 50, 100, 50); // 위치와 크기를 원하는 값으로

		mainButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 메인으로 가는 버튼을 클릭하면 메소드 호출
				backToMain();
			}
		});

		add(mainButton);
	}
	public void turn() {//ai게임하는 턴이므로 주의할것.
		if(gameState.get(WHOSTURN)==gameState.get(WHOAMI)) {
			//드로우&세팅페이즈
			draw();
			for(Card card:field.field){
				card.mainEffect(field, enemyfield);
			}
			int nowgold = gameState.get(GOLD);
			//gameState.set(GOLD, nowgold + 1); 턴마다 골드획득은 헷갈리니까 삭제
			gameState.set(ACT, 3);// 두번 설정하므로 주의.
			gameState.set(NOWTURN, 1);
			////////
			//메인페이즈

			//////
			System.out.println("내턴");
		}
		else{
			int fieldnum;
			if(enemyfield.getfieldsize()<4) {
				while(true) {
					fieldnum = (int) (Math.random() * 4) + 1;
					boolean go = true;
					for(int i=0;i<enemyfield.getfieldsize();i++){
						if(enemyfield.field.get(i).fieldnum==fieldnum){
							go=false;
						}
					}
					if(go){
						break;
					}
				}
				int cn = (int) (Math.random() * 9) + 1;
				enemyfield.toField("card"+cn, 10, cn, fieldnum);
				add(enemyfield.field.get(enemyfield.getfieldsize() - 1).cardb);
				add(enemyfield.field.get(enemyfield.getfieldsize() - 1).cardAdLb);
				add(enemyfield.field.get(enemyfield.getfieldsize() - 1).cardHpLb);
				container.setComponentZOrder(enemyfield.field.get(enemyfield.getfieldsize() - 1).cardb, 1);
				container.setComponentZOrder(enemyfield.field.get(enemyfield.getfieldsize() - 1).cardAdLb, 1);
				container.setComponentZOrder(enemyfield.field.get(enemyfield.getfieldsize() - 1).cardHpLb, 1);
			}
			battlePhase();
		}

	}
	public void draw() {
		if(hand.gethandsize()==5){
			return;
		}
		deck.draw(1,hand,newcardx);
		hand.hand.get(hand.gethandsize()-1).setGameState(gameState);
		hand.hand.get(hand.gethandsize()-1).setField(field);
		hand.hand.get(hand.gethandsize()-1).setPlayer2(player2);
		hand.hand.get(hand.gethandsize()-1).setPlayer1(player1);
		hand.hand.get(hand.gethandsize()-1).setEnemyfield(enemyfield);
		hand.hand.get(hand.gethandsize()-1).setHand(hand);
		add(hand.hand.get(hand.gethandsize()-1).cardb);
		add(hand.hand.get(hand.gethandsize()-1).cardAdLb);
		add(hand.hand.get(hand.gethandsize()-1).cardHpLb);
		newcardx+=200;
		container.setComponentZOrder(hand.hand.get(hand.gethandsize()-1).cardb, 1);
		container.setComponentZOrder(hand.hand.get(hand.gethandsize()-1).cardAdLb, 1);
		container.setComponentZOrder(hand.hand.get(hand.gethandsize()-1).cardHpLb, 1);
	}
	public void paint(Graphics g) {
		screenImage = createImage(SCREEN_WIDTH,SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D)screenGraphic);//형변환
		g.drawImage(screenImage, 0, 0, null);
	}
	public void screenDraw(Graphics2D g) {
		g.drawImage(Background, 0, 0, null);//단순이미지 그리기
		if(isMainScreen)
		{
			g.drawImage(selectedImage, 340, 100, null);//단순 이미지!!
			g.drawImage(titleImage, 340, 70, null);
		}
		
		if(isGameScreen) {
			g.drawImage(deckimage,1100,720-230,null);
			g.drawImage(field1image,200,300-230,null);
			for(int i=0;i<hand.gethandsize();i++) {
				hand.hand.get(i).cardb.setBounds(hand.hand.get(i).x,hand.hand.get(i).y,150,230);
				hand.hand.get(i).cardAdLb.setBounds(hand.hand.get(i).getX()+10,hand.hand.get(i).getY()+210,15,10);
				hand.hand.get(i).cardHpLb.setBounds(hand.hand.get(i).getX()+130,hand.hand.get(i).getY()+210,15,10);
			}
			for(int i=0;i<field.getfieldsize();i++) {

				field.field.get(i).cardb.setBounds(field.field.get(i).x,field.field.get(i).y,150,230);
				field.field.get(i).cardAdLb.setBounds(field.field.get(i).getX()+5,field.field.get(i).getY()+210,15,10);
				field.field.get(i).cardHpLb.setBounds(field.field.get(i).getX()+130,field.field.get(i).getY()+210,15,10);
				if(field.field.get(i).state.equals("notinscreen")){
					field.field.remove(field.field.get(i));
				}
			}
			for(int i=0;i<enemyfield.getfieldsize();i++) {
				enemyfield.field.get(i).cardb.setBounds(enemyfield.field.get(i).x,enemyfield.field.get(i).y,150,230);
				enemyfield.field.get(i).cardAdLb.setBounds(enemyfield.field.get(i).getX()+5,enemyfield.field.get(i).getY()+210,15,10);
				enemyfield.field.get(i).cardHpLb.setBounds(enemyfield.field.get(i).getX()+130,enemyfield.field.get(i).getY()+210,15,10);
			}
			la.setText("남은 행동: "+gameState.get(0)+" 남은 골드: "+gameState.get(5)+"\n플레이어 남은체력: "+player1.getHealth()+"\n적 남은 체력: "+player2.getHealth());
			//////렉걸리면 이부분 스레드 따로해야함
			if(gameState.get(4)==1) {
				gameState.set(4, 0);
				checkcardx();
				newcardx-=200;
			}
			if(gameState.get(0)<=0){
				battlePhase();
			}
			if(player1.getHealth()==0){
				backToMain();
			}
			if(player2.getHealth()==0){
				backToMain();
			}
			//////
		}
		paintComponents(g);//역동적이지 않은 이미지 그리기, 메인프레임에 추가된 요소 그리기 예: add
		
		this.repaint();
	}
	public void makeDeck2(){
		deck.deck.clear();
		deck.toDeck(new Addusu());
		deck.toDeck(new Addusu());
		deck.toDeck(new Bee());
		deck.toDeck(new Bee());
		deck.toDeck(new Ladybug());
		deck.toDeck(new Ladybug());
		deck.toDeck(new Akimos());
		deck.toDeck(new Akimos());
		deck.toDeck(new Beelzemub());
		deck.toDeck(new Beelzemub());
		deck.toDeck(new Amorucci());
		deck.toDeck(new Amorucci());
		deck.toDeck(new Fly());
		deck.toDeck(new Fly());
		deck.toDeck(new Girax());
		deck.toDeck(new Girax());
		deck.toDeck(new Ved());
		deck.toDeck(new Ved());
		deck.toDeck(new Vlady());
		deck.toDeck(new Vlady());
		deck.toDeck(new Hercules());
		deck.toDeck(new Hercules());
		deck.toDeck(new SquolMeterol());
		deck.toDeck(new SquolMeterol());

	}
	public void makeDeck1(){
		deck.deck.clear();
		deck.toDeck(new Archer());
		deck.toDeck(new Archer());
		deck.toDeck(new Bard());
		deck.toDeck(new Bard());
		deck.toDeck(new Hero());
		deck.toDeck(new Hero());
		deck.toDeck(new HorseSoldier());
		deck.toDeck(new HorseSoldier());
		deck.toDeck(new Hunter());
		deck.toDeck(new Hunter());
		deck.toDeck(new Knights());
		deck.toDeck(new Knights());
		deck.toDeck(new Priest());
		deck.toDeck(new Priest());
		deck.toDeck(new Scout());
		deck.toDeck(new Scout());
		deck.toDeck(new ShieldSoldier());
		deck.toDeck(new ShieldSoldier());
		deck.toDeck(new Spearman());
		deck.toDeck(new Spearman());
		deck.toDeck(new Thief());
		deck.toDeck(new Thief());
		deck.toDeck(new Wizard());
		deck.toDeck(new Wizard());
	}

	public void battlePhase() {//ai용 배틀페이즈임
		if (gameState.get(WHOSTURN) == gameState.get(WHOAMI)) {
			for (Card card : field.field) {
				card.attack(player2, enemyfield);
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
			}
			gameState.set(0,-1);
		}
		else{
			for (Enemycard enemycard : enemyfield.field){
				boolean directattck = true;
				for(Card card : field.field){
					if(card.fieldnum==enemycard.fieldnum){
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

		endPhase();
	}
	/*pvp배틀페이즈
	public void battlePhase() {
		if (gameState.get(WHOSTURN) == gameState.get(WHOAMI)) {
			for (Card card : field.field) {
				if (gameState.get(WHOAMI) == 1) {

					card.attack(player2);
				} else {
					card.attack(player1);
				}
			}
			gameState.set(0,-1);
		}
		endPhase();
	}
	 */

	public void endPhase() {
		if(gameState.get(WHOSTURN)==gameState.get(WHOAMI)) {
			if(gameState.get(WHOAMI)==1){
				gameState.set(WHOSTURN, 2);
			}
			else{
				gameState.set(WHOSTURN, 1);

			}
		}
		else{
			gameState.set(WHOSTURN, 1);
			gameState.set(0,3);
		}
		System.out.println(gameState.get(WHOSTURN)+"의 차례");
		turn();
	}
}
/*
public void endPhase() {
		if(gameState.get(WHOSTURN)==gameState.get(WHOAMI)) {
			if(gameState.get(WHOAMI)==1){
				gameState.set(WHOSTURN, 2);
			}
			else{
				gameState.set(WHOSTURN, 1);

			}
		}
		System.out.println(gameState.get(WHOSTURN)+"의 차례");
		turn();
	}
}

 */