package notguitcgcard;
import java.awt.*;
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
	TextMove textMove;
	JLabel vicima = new JLabel(new ImageIcon(Main.class.getResource("../images/동물_플레이.png")));
	JLabel lossima = new JLabel(new ImageIcon(Main.class.getResource("../images/동물_플레이.png")));
	JLabel battima = new JLabel(new ImageIcon(Main.class.getResource("../images/동물_플레이.png")));
	private Player player1;
	private Player player2;
	boolean lose = false;
	boolean vic = false;
	boolean battlest = false;
	private Image screenImage;//더블버터링을 위한
	private Graphics screenGraphic;

	private Image Background = new ImageIcon(Main.class.getResource("../images/이쉬타르 배경화면.png")).getImage();//인트로 사진저장
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menubar.png")));
	private JLabel enemyBoss = new JLabel(new ImageIcon(Main.class.getResource("../images/적_플레이.png")));
	// 동물_플레이, 곤충_플레이, 인간_플레이 3개! .png
	private JLabel ourBoss1 = new JLabel(new ImageIcon(Main.class.getResource("../images/인간_플레이.png")));
	private JLabel ourBoss2 = new JLabel(new ImageIcon(Main.class.getResource("../images/곤충_플레이.png")));
	private JLabel ourBoss3 = new JLabel(new ImageIcon(Main.class.getResource("../images/동물_플레이.png")));

	private ImageIcon exitButtonbasic = new ImageIcon(Main.class.getResource("../images/closeingbutten.png"));
	private ImageIcon exitButtonentered = new ImageIcon(Main.class.getResource("../images/closebutten.png"));
	private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/startbuttonEnter.png"));
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/startbutton.png"));
	private ImageIcon deckscButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/deckButtonEnter.png"));
	private ImageIcon deckscButtonBasicImage = new ImageIcon(Main.class.getResource("../images/deckButton.png"));
	private ImageIcon goldbuttonImage = new ImageIcon(Main.class.getResource("../images/골드획득.png"));
	private ImageIcon deck1BestImage = new ImageIcon(Main.class.getResource("../images/인간_대표.png"));
	private ImageIcon deck2BestImage = new ImageIcon(Main.class.getResource("../images/곤충_대표.png"));
	private ImageIcon deck3BestImage = new ImageIcon(Main.class.getResource("../images/동물_대표.png"));
	private ImageIcon toMainImage = new ImageIcon(Main.class.getResource("../images/메인으로.png"));
	private ImageIcon deck1clicked = new ImageIcon(Main.class.getResource("../images/인간_대표_클릭.png"));
	private ImageIcon deck2clicked = new ImageIcon(Main.class.getResource("../images/곤충_대표_클릭.png"));
	private ImageIcon deck3clicked = new ImageIcon(Main.class.getResource("../images/동물_대표_클릭.png"));
	private ImageIcon toMainImageClicked= new ImageIcon(Main.class.getResource("../images/메인으로_클릭.png"));


	private Image deckimage = new ImageIcon(Main.class.getResource("../images/카드뒷면.png")).getImage();
	private Image field1image = new ImageIcon(Main.class.getResource("../images/적필드.png")).getImage();

	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton exitButton = new JButton(exitButtonbasic);
	private JButton goldButton = new JButton(goldbuttonImage);
	private JButton deckScreenButton = new JButton(deckscButtonBasicImage);
	private JLabel la1;
	private JLabel la2;
	private JLabel la3;
	private JLabel la4;
	private boolean isMainScreen = false;
	//private boolean isDeckScreen = false;
	private boolean isGameScreen = false;
	private Music introMusic = new Music("임시배경음악.mp3",true);
	private Music gameMusic = new Music("배틀브금.mp3",true);
	BattleManager battleManager;
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
	//아홉번째는 end인지
	public Hand hand;
	public Field field;
	public Enemyfield enemyfield;
	public Deck deck;
	FieldButton newfb1 = new FieldButton(gameState,1);
	FieldButton newfb2 = new FieldButton(gameState,2);
	FieldButton newfb3 = new FieldButton(gameState,3);
	FieldButton newfb4 = new FieldButton(gameState,4);

	private JButton deck1;
	private JButton deck2;
	private JButton deck3;
	private JButton mainButton;

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

		startButton.setBounds(180,420,400,100); // 590, 400,...
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

		deckScreenButton.setBounds(180,550,400,100); // 590 ...
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


		goldButton.setBounds(1130,365,60,20);
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
		add(menuBar);

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
		Background = new ImageIcon(Main.class.getResource("../images/덱go6.png")).getImage();


		// 새로운 버튼 생성
		deck1 = new JButton(deck1BestImage);
		deck2 = new JButton(deck2BestImage);
		deck3 = new JButton(deck3BestImage);

		// 버튼 위치 및 크기 설정
		deck1.setBounds(130, 100, 150, 170); // 위치와 크기를 원하는 값으로
		deck1.setBorderPainted(false);
		deck1.setContentAreaFilled(false);
		deck1.setFocusPainted(false);

		deck2.setBounds(130, 300, 150, 170); // 위치와 크기를 원하는 값으로
		deck2.setBorderPainted(false);
		deck2.setContentAreaFilled(false);
		deck2.setFocusPainted(false);

		deck3.setBounds(130, 500, 150, 170); // 위치와 크기를 원하는 값으로
		deck3.setBorderPainted(false);
		deck3.setContentAreaFilled(false);
		deck3.setFocusPainted(false);

		// 메인으로 가는 버튼 생성
		mainButton = new JButton(toMainImage);
		mainButton.setBounds(10, 30, 100, 100); // 위치와 크기를 원하는 값으로
		mainButton.setBorderPainted(false);
		mainButton.setContentAreaFilled(false);
		mainButton.setFocusPainted(false);

		// 메인으로 가는 버튼 이벤트 설정
		mainButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mainButton.setIcon(toMainImageClicked);
				mainButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("bentered.mp3",false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				mainButton.setIcon(toMainImage);
				mainButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("tfile.mp3",false);
				buttonEnteredMusic.start();
				backTomainFromDeck();
			}
		});

		// 메인으로 가는 버튼을 화면에 추가
		add(mainButton);

		// 버튼 이벤트 설정
		deck1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("tfile.mp3",false);
				buttonEnteredMusic.start();
				// deck1 버튼을 클릭하면 nowdeck 값을 1로 설정
				nowDeck = 1;
				for(Card card : deck.deck){
					card.setState("inscreen");
				}

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				deck1.setIcon(deck1clicked);
				deck1.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("bentered.mp3",false);
				buttonEnteredMusic.start();
				makeDeck1ex();
				// 마우스가 버튼 위에 올라갔을 때 이미지 보이게 설정
				printDeck();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				deck1.setIcon(deck1BestImage);
				deck1.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				// 마우스가 버튼에서 벗어났을 때 이미지 숨기게 설정
				dePrintDeck();
			}
		});

		deck2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("tfile.mp3",false);
				buttonEnteredMusic.start();
				// deck1 버튼을 클릭하면 nowdeck 값을 2로 설정
				nowDeck = 2;
				for(Card card : deck.deck){
					card.setState("inscreen");
				}

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				deck2.setIcon(deck2clicked);
				deck2.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("bentered.mp3",false);
				buttonEnteredMusic.start();
				makeDeck2ex();
				// 마우스가 버튼 위에 올라갔을 때 이미지 보이게 설정
				printDeck();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				deck2.setIcon(deck2BestImage);
				deck2.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				// 마우스가 버튼에서 벗어났을 때 이미지 숨기게 설정
				dePrintDeck();
			}
		});

		deck3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("tfile.mp3",false);
				buttonEnteredMusic.start();
				// deck1 버튼을 클릭하면 nowdeck 값을 3로 설정
				nowDeck = 3;
				for(Card card : deck.deck){
					card.setState("inscreen");
				}

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				deck3.setIcon(deck3clicked);
				deck3.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("bentered.mp3",false);
				buttonEnteredMusic.start();
				makeDeck3ex();
				// 마우스가 버튼 위에 올라갔을 때 이미지 보이게 설정
				printDeck();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				deck3.setIcon(deck3BestImage);
				deck3.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				// 마우스가 버튼에서 벗어났을 때 이미지 숨기게 설정
				dePrintDeck();
			}
		});

		// 버튼을 화면에 추가
		add(deck1);
		add(deck2);
		add(deck3);
	}
	public void dePrintDeck(){
		for(Card card:deck.deck){
			card.cardb.setVisible(false);
			card.cardAdLb.setVisible(false);
			card.cardHpLb.setVisible(false);
		}
	}
	public void printDeck(){
		int x = 450;
		int y = 30;
		int row = 5;
		for(int i=0;i<deck.deck.size();i++){
			Card card = deck.deck.get(i);
			add(card.cardb);
			add(card.cardAdLb);
			add(card.cardHpLb);
			container.setComponentZOrder(card.cardb, 1);
			container.setComponentZOrder(card.cardAdLb, 1);
			container.setComponentZOrder(card.cardHpLb, 1);
			card.cardb.setVisible(true);
			card.cardAdLb.setVisible(true);
			card.cardHpLb.setVisible(true);
			card.cardAdLb.setText(card.getAd()+"");
			card.cardHpLb.setText(card.getHp()+"");
			card.cardb.setBounds(x+((int)(i%row))*150,y+((int)(i/row))*230,150,230);
			card.cardAdLb.setBounds(x+((int)(i%row))*150+5,y+((int)(i/row))*230+210,15,10);
			card.cardHpLb.setBounds(x+((int)(i%row)+1)*150-20,y+((int)(i/row))*230+210,15,10);
		}
	}
	public void backTomainFromDeck() {
		isMainScreen=true;
		isGameScreen=false;

		Background = new ImageIcon(Main.class.getResource("../images/backback.jpg")).getImage();
		startButton.setVisible(true);
		deckScreenButton.setVisible(true);

		deck1.setVisible(false);
		deck2.setVisible(false);
		deck3.setVisible(false);
		mainButton.setVisible(false);
	}

	public void backToMain(){
		gameMusic.close();
		introMusic = new Music("임시배경음악.mp3",true);
		introMusic.start();
		isMainScreen=true;
		isGameScreen=false;
		battleManager.setIsgame(false);
		Background = new ImageIcon(Main.class.getResource("../images/이쉬타르 배경화면.png")).getImage();
		startButton.setVisible(true);
		deckScreenButton.setVisible(true);
		goldButton.setVisible(false);
		gameState.clear();
		newfb1.setVisible(false);
		newfb2.setVisible(false);
		newfb3.setVisible(false);
		newfb4.setVisible(false);
		mainButton.setVisible(false);
		enemyBoss.setVisible(false);
		ourBoss1.setVisible(false);
		ourBoss2.setVisible(false);
		ourBoss3.setVisible(false);

		la1.setVisible(false);
		la2.setVisible(false);
		la3.setVisible(false);
		la4.setVisible(false);

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
		lose = false;
		vic = false;
		if(nowDeck==1){
			makeDeck1();
		} else if (nowDeck==2) {
			makeDeck2();
		}else if (nowDeck==3){
			makeDeck3();
		}
		newcardx = 100;
		player2.setHealth(45);
		player1.setHealth(45);
		introMusic.close();
		isMainScreen = false;
		startButton.setVisible(false);
		deckScreenButton.setVisible(false);
		Background = new ImageIcon(Main.class.getResource("../images/게임진행4.png")).getImage();

		hand.hand.clear();
		field.field.clear();

		newfb1.setVisible(true);
		newfb2.setVisible(true);
		newfb3.setVisible(true);
		newfb4.setVisible(true);
		goldButton.setVisible(true);
		gameMusic = new Music("배틀브금.mp3",true);
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
		gameState.add(0);
		////////////////////////실험을 위한 코드이므로 서버구현시 반드시 반드시 지워야함.
		gameState.set(WHOAMI,1);

		////////////////////////
		gameState.set(WHOSTURN,1);
		isGameScreen = true;

		gameState.set(GOLD, 10);
		battleManager = new BattleManager(gameState,field,enemyfield,player1,player2);
		battleManager.start();
		battleManager.setIsgame(true);
		la1 = new JLabel(""+gameState.get(0));
		la1.setFont(new Font("Arial", Font.BOLD, 20));
		la1.setForeground(Color.WHITE);
		la1.setVisible(true);
		la1.setLocation(1057, 410);
		la1.setSize(500, 50);
		add(la1);

		la2 = new JLabel(""+gameState.get(5));
		la2.setFont(new Font("Arial", Font.BOLD, 20));
		la2.setForeground(Color.WHITE);
		la2.setVisible(true);
		la2.setLocation(1055, 348);
		la2.setSize(500, 50);
		add(la2);

		la3 = new JLabel(""+player1.getHealth());
		la3.setFont(new Font("Arial", Font.BOLD, 20));
		// 텍스트 색상 변경
		la3.setForeground(Color.WHITE);
		la3.setVisible(true);
		la3.setLocation(962, 440);
		la3.setSize(500, 50);
		add(la3);

		la4 = new JLabel(""+player2.getHealth());
		la4.setFont(new Font("Arial", Font.BOLD, 20));
		la4.setForeground(Color.WHITE);
		la4.setVisible(true);
		la4.setLocation(1162, 209);
		la4.setSize(500, 50);
		add(la4);
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

		mainButton = new JButton(toMainImage);
		mainButton.setBounds(20, 30, 100, 100); // 위치와 크기를 원하는 값으로
		mainButton.setBorderPainted(false);
		mainButton.setContentAreaFilled(false);
		mainButton.setFocusPainted(false);

		// 메인으로 가는 버튼 이벤트 설정
		mainButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mainButton.setIcon(toMainImageClicked);
				mainButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("bentered.mp3",false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				mainButton.setIcon(toMainImage);
				mainButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				//mp3
				Music buttonEnteredMusic = new Music("tfile.mp3",false);
				buttonEnteredMusic.start();
				backToMain();
			}
		});

		add(mainButton);
		enemyBoss.setVisible(true);
		enemyBoss.setBounds(1050,95,150,170);
		add(enemyBoss);//jframe에 추가
		if(nowDeck==1){
			ourBoss1.setVisible(true);
			ourBoss1.setBounds(850,325,150,170);
			add(ourBoss1);//jframe에 추가
		} else if (nowDeck==2) {
			ourBoss2.setVisible(true);
			ourBoss2.setBounds(850,325,150,170);
			add(ourBoss2);//jframe에 추가
		}else if (nowDeck==3){
			ourBoss3.setVisible(true);
			ourBoss3.setBounds(850,325,150,170);
			add(ourBoss3);//jframe에 추가
		}

	}
	public void turn() {//ai게임하는 턴이므로 주의할것.
		if(gameState.get(WHOSTURN)==gameState.get(WHOAMI)) {
			gameState.set(NOWTURN, DRAWPHASE);
			//드로우&세팅페이즈
			draw();
			for(Card card:field.field){
				card.mainEffect(field, enemyfield);
			}
			int nowgold = gameState.get(GOLD);
			//gameState.set(GOLD, nowgold + 1); 턴마다 골드획득은 헷갈리니까 삭제
			gameState.set(ACT, 3);// 두번 설정하므로 주의.
			gameState.set(NOWTURN, MAINPHASE);
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
				Music m = new Music("eme.mp3",false);
				m.start();
				int cn = (int) (Math.random() * 11) + 1;
				enemyfield.toField("ai"+cn, 2+10-cn, (int)(cn/2)+1, fieldnum);
				add(enemyfield.field.get(enemyfield.getfieldsize() - 1).cardb);
				add(enemyfield.field.get(enemyfield.getfieldsize() - 1).cardAdLb);
				add(enemyfield.field.get(enemyfield.getfieldsize() - 1).cardHpLb);
				container.setComponentZOrder(enemyfield.field.get(enemyfield.getfieldsize() - 1).cardb, 1);
				container.setComponentZOrder(enemyfield.field.get(enemyfield.getfieldsize() - 1).cardAdLb, 1);
				container.setComponentZOrder(enemyfield.field.get(enemyfield.getfieldsize() - 1).cardHpLb, 1);
				enemyfield.field.get(enemyfield.getfieldsize() - 1).cardb.setVisible(true);
				enemyfield.field.get(enemyfield.getfieldsize() - 1).cardAdLb.setVisible(true);
				enemyfield.field.get(enemyfield.getfieldsize() - 1).cardHpLb.setVisible(true);
				enemyfield.field.get(enemyfield.getfieldsize() - 1).cardb.setBounds(enemyfield.field.get(enemyfield.getfieldsize() - 1).x,enemyfield.field.get(enemyfield.getfieldsize() - 1).y,150,230);
				enemyfield.field.get(enemyfield.getfieldsize() - 1).cardAdLb.setBounds(enemyfield.field.get(enemyfield.getfieldsize() - 1).getX()+5,enemyfield.field.get(enemyfield.getfieldsize() - 1).getY()+210,15,10);
				enemyfield.field.get(enemyfield.getfieldsize() - 1).cardHpLb.setBounds(enemyfield.field.get(enemyfield.getfieldsize() - 1).getX()+130,enemyfield.field.get(enemyfield.getfieldsize() - 1).getY()+210,15,10);

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
			la1.setText(""+gameState.get(0));
			la2.setText(""+gameState.get(5));
			la3.setText(""+player1.getHealth());
			la4.setText(""+player2.getHealth());

			//////렉걸리면 이부분 스레드 따로해야함
			if(gameState.get(4)==1) {
				gameState.set(4, 0);
				checkcardx();
				newcardx-=200;
			}
			if(gameState.get(0)==0){
				gameState.set(0,-1);
				battlePhase();
			}
			if(player1.getHealth()==0){
				textMove = new TextMove(0,gameState);
				add(textMove.lossima);
				textMove.lossima.setVisible(true);
				isGameScreen = false;
				lose = true;
				for(Card card : field.field){
					card.cardb.setVisible(false);
					card.cardAdLb.setVisible(false);
					card.cardHpLb.setVisible(false);
				}
				for(Enemycard card : enemyfield.field){
					card.cardb.setVisible(false);
					card.cardHpLb.setVisible(false);
					card.cardAdLb.setVisible(false);
				}
				newfb1.setVisible(false);
				newfb2.setVisible(false);
				newfb3.setVisible(false);
				newfb4.setVisible(false);
				textMove.start();
				//backToMain();

			}
			if(player2.getHealth()==0){
				textMove = new TextMove(1,gameState);
				add(textMove.vicima);
				textMove.vicima.setVisible(true);
				isGameScreen = false;
				vic = true;
				for(Card card : field.field){
					card.cardb.setVisible(false);
					card.cardAdLb.setVisible(false);
					card.cardHpLb.setVisible(false);
				}
				for(Enemycard card : enemyfield.field){
					card.cardb.setVisible(false);
					card.cardHpLb.setVisible(false);
					card.cardAdLb.setVisible(false);
				}
				newfb1.setVisible(false);
				newfb2.setVisible(false);
				newfb3.setVisible(false);
				newfb4.setVisible(false);
				textMove.start();


				//backToMain();
			}
			//수정요
			if(isGameScreen) {
				if (gameState.get(NOWTURN) == ENDPHASE) {
					if(isGameScreen) {
						endPhase();
					}
				}
			}
			//////
		}
		if(lose){

			textMove.lossima.setBounds(textMove.x,textMove.getY(),300,200);
			if(gameState.get(8)==1){
				gameState.set(8,0);
				lose=false;
				backToMain();

			}
		}
		if(vic){
			textMove.vicima.setBounds(textMove.x,textMove.getY(),300,200);
			if(gameState.get(8)==1){
				gameState.set(8,0);
				vic=false;
				backToMain();

			}
		}
		paintComponents(g);//역동적이지 않은 이미지 그리기, 메인프레임에 추가된 요소 그리기 예: add
		
		this.repaint();
	}
	public void makeDeck3(){
		deck.deck.clear();
		deck.toDeck(new Chess());
		deck.toDeck(new Chess());
		deck.toDeck(new Chals());
		deck.toDeck(new Chals());
		deck.toDeck(new Partus());
		deck.toDeck(new Partus());
		deck.toDeck(new Phubaos());
		deck.toDeck(new Phubaos());
		deck.toDeck(new Pigmis());
		deck.toDeck(new Pigmis());
		deck.toDeck(new Pororos());
		deck.toDeck(new Pororos());
		deck.toDeck(new Welchs());
		deck.toDeck(new Welchs());
		deck.toDeck(new Dax());
		deck.toDeck(new Dax());
		deck.toDeck(new Woos());
		deck.toDeck(new Woos());
		deck.toDeck(new Goosegoose());
		deck.toDeck(new Goosegoose());
		deck.toDeck(new Nyangs());
		deck.toDeck(new Nyangs());
		deck.toDeck(new Wills());
		deck.toDeck(new Wills());
		for(Card card:deck.deck){
			card.setAtmname("deck3.mp3");
			card.setSm(new Music("deck3.mp3",false));
		}
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
		for(Card card:deck.deck){
			card.setAtmname("deck2.mp3");
			card.setSm(new Music("deck2.mp3",false));
		}
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
		for(Card card:deck.deck){
			card.setAtmname("deck1.mp3");
			card.setSm(new Music("realdeck1.mp3",false));
		}
	}

	public void makeDeck3ex(){
		deck.deck.clear();
		deck.toDeck(new Chess());

		deck.toDeck(new Chals());

		deck.toDeck(new Partus());

		deck.toDeck(new Phubaos());

		deck.toDeck(new Pigmis());

		deck.toDeck(new Pororos());

		deck.toDeck(new Welchs());

		deck.toDeck(new Dax());

		deck.toDeck(new Woos());

		deck.toDeck(new Goosegoose());

		deck.toDeck(new Nyangs());

		deck.toDeck(new Wills());

	}

	public void makeDeck2ex(){
		deck.deck.clear();
		deck.toDeck(new Addusu());

		deck.toDeck(new Bee());

		deck.toDeck(new Ladybug());

		deck.toDeck(new Akimos());

		deck.toDeck(new Beelzemub());

		deck.toDeck(new Amorucci());

		deck.toDeck(new Fly());

		deck.toDeck(new Girax());

		deck.toDeck(new Ved());

		deck.toDeck(new Vlady());

		deck.toDeck(new Hercules());

		deck.toDeck(new SquolMeterol());


	}
	public void makeDeck1ex(){
		deck.deck.clear();
		deck.toDeck(new Archer());

		deck.toDeck(new Bard());

		deck.toDeck(new Hero());

		deck.toDeck(new HorseSoldier());

		deck.toDeck(new Hunter());

		deck.toDeck(new Knights());

		deck.toDeck(new Priest());

		deck.toDeck(new Scout());

		deck.toDeck(new ShieldSoldier());

		deck.toDeck(new Spearman());

		deck.toDeck(new Thief());

		deck.toDeck(new Wizard());

	}

	public void battlePhase() {//ai용 배틀페이즈임
		System.out.println("제발");
		gameState.set(NOWTURN,BATTLEPHASE);
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
		System.out.println("123123123");
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
		gameState.set(NOWTURN,DRAWPHASE);
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