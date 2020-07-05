package rotris;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Rectangle;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;


public class RonaTetris {

	public static boolean startedgame = false;
	public static 	Timer decortime = new Timer();
	public static 	JPanel forstartp = new JPanel();

	public static int key; 
	public static boolean donesong = false;
	public static boolean pls = true;
	public static Color mydarkgray = Color.getHSBColor(0f, 0f, 0.1f);

	public static Color mypink = Color.getHSBColor(0.0f, 0.3f, 0.7f);
	public static JPanel coord[][] = new JPanel[10][20]; // 10 rows 20 columns
	public static JPanel base = new JPanel();

	public static boolean onstorepage = false;
	public static boolean gameover = false;

	public static int timegone;
	public static char fallingtype;
	public static char currenttype;
	public static  boolean done;
	public static long starttime;
	public static char type;
	public static JPanel storeFront = new JPanel();
	public static int score = 0;

	public static char pretype;
	public static boolean wait = false;
	public static JPanel horizontalmainp = new JPanel();

	public static char holdtype;
	public static boolean onhold= false;
	public static int counter = 0;
	public static int x0, x1, x2, x3, y0, y1, y2, y3, smallestx, smallesty, futurey, futurex, horizontalshift;
	public static boolean cancontinue;

	public static JPanel startp = new JPanel();
	public static JPanel endp = new JPanel();

	public static JPanel[][] getPanel() {
		return coord;
	}

	


	public static JFrame main = new JFrame ();
	public static JPanel mainp = new JPanel();


	public static void setKey(int thekey) {
		key = thekey;
	}

	public static char getCurrentType() {

		return fallingtype;
	}

	public static char getPreviewType() {
		return pretype;
	}

	public static char getHoldType() {

		return holdtype;
	}

	// switch panel

	public static void switchp(JFrame frame, JPanel base, JPanel newp) {

		frame.remove(base);
		base.removeAll();
		base.validate();
		base.add(newp);
		frame.add(base);
		frame.validate();
		frame.repaint();


	}

	// play audio method

	public static void play(String thefile) throws UnsupportedAudioFileException, IOException, LineUnavailableException {


		Path audiopath = Paths.get(thefile); // relative file name! so it works everywhere hopefully
		// the file is in the folder containing src but not in the src folder

		File thesynth = new File(audiopath.toString()); // get the file path from the path

		AudioInputStream audio = AudioSystem.getAudioInputStream(thesynth); // get the audio data from file thesynth and put it as its own variable 

		Clip segment = AudioSystem.getClip(); // initialize
		segment.open(audio); // the clip is the audio aka the synth
		boolean play = true; // to let it play
		while ( play == true) { // play it 
			segment.setFramePosition(0); // make it start at the beginning
			segment.start(); // start it 
			segment.drain(); // let it finish or else it wont play
			play = false; // make it false once its done playing

			;	
		}
	}	

	// gen types of blocks

	static char gettype() {

		double probability;
		probability = Math.random();

		// these chances are rigged 
		if (probability<0.25) { 
			type = 'I'; // i-block, 4 straight cubes
		}
		else if (probability<0.375) {
			type = 'L'; // L-block, like an L
		}
		else if (probability<0.455) {
			type = 'T'; // T-block, like a T
		} 
		else if (probability<0.525) {
			type = 'Z';} //Z-block, like stairs 


		else if (probability<0.675) {
			type = 'S';} //S-block, like stairs the other way


		else if (probability<0.825) {
			type = 'O';} //O block like a square

		else if( probability<1.0){

			type = 'J';
		} // like a backwards L



		return type;
	}


	static MouseListener mousetest = new MouseListener() {



		public void mouseclicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			key = 27;


		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}
	};




	static
	MouseListener mousekey1 = new MouseListener() {



		public void mouseclicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			key = 49;




		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}
	};


	static
	MouseListener mousekey2 = new MouseListener() {



		public void mouseclicked(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			key = 50;



		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}
	};



	static MouseListener mousekey3 = new MouseListener() {



		public void mouseclicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override 
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			key = 51;


		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}
	};



	static MouseListener mousekey4 = new MouseListener() {



		public void mouseclicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override 
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			key = 52;


		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}
	};



	static	MouseListener start = new MouseListener() {



		public void mouseclicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub


		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override 
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

			startedgame = true;
			TetrisBotMaybe.reset();

			switchp(main, base, horizontalmainp);

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}
	};

	static MouseListener clickedbotver = new MouseListener() {



		public void mouseclicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override 
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

			startedgame = true;
			TetrisBotMaybe.reset();
			switchp(main, base, horizontalmainp);
			key = 53;

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}
	};



	static MouseListener replaymouse = new MouseListener() {



		public void mouseclicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override 
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

			score = 0;

			gameover = false;
			switchp(main, base, horizontalmainp);
			TetrisBotMaybe.reset();

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}
	};
	static MouseListener gobackmouse = new MouseListener() {



		public void mouseclicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			switchp(main, base, startp);
		

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}
	};




	static MouseListener gostoremouse = new MouseListener() {



		public void mouseclicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			switchp(main, base, storeFront);

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}
	};




	static void paused(int timewanted) {
		long starttime = System.nanoTime()/10000000; // base time whenever its called
		boolean done = false;
		while (done == false)
		{
			if 	(System.nanoTime()/10000000 - starttime >timewanted) {done = true;}
		}

	}


	public static void setblock(JPanel type[][], char typechar) { 

		// takes the one you want to replace (preview or on hold) and fills it with 
		// a new type



		currenttype = typechar;
		for (int x = 0; x<4; x++) for(int y = 0; y<4; y++) {


			type[x][y].setBackground(mydarkgray);
		}

		if (typechar == 'I') {

			type[0][0].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
			type[0][1].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
			type[0][2].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
			type[0][3].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));


		}

		else if (typechar == 'L') {
			type[0][0].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
			type[0][1].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
			type[0][2].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
			type[1][2].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));


		}

		else if (typechar == 'T') {

			type[1][0].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
			type[0][1].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
			type[1][1].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
			type[2][1].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));

		}

		else if (typechar == 'Z') {
			type[0][0].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
			type[1][0].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
			type[1][1].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
			type[2][1].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));


		}

		else if (typechar == 'O') {
			type[0][0].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
			type[0][1].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
			type[1][0].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
			type[1][1].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));


		}
		else if (typechar == 'J') {
			type[0][2].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
			type[1][0].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
			type[1][1].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
			type[1][2].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));

		}
		else if (typechar == 'S') {
			type[1][0].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
			type[2][0].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
			type[1][1].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
			type[0][1].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
		}





	}



	public static 
	KeyListener mainkeylistener = new KeyListener()
	{	public void keyTyped(KeyEvent e) {	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		key = e.getKeyCode();


	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub		

	};

	};
	public static void main (String [] args) throws Exception, IOException, LineUnavailableException {

		play("background - ozzed.wav");



		StoreItems.makemice();

		Rectangle screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();

		int screenx = (int) (screen.getWidth());
		int screeny = (int) (screen.getHeight());
		int currentbutton;
		int count = 0;
		int length = 50;
		int scoreboost = 1;
		int playercoins = 0;

		int fillavail = 0; 
		int boostavail = 0;
		int gravityavail = 0;


		boolean startedbot = false;

		boolean canfall = true;
		boolean canrotate = true;
		boolean canside = true;
		GridBagLayout coordGrid = new GridBagLayout(); // layout that can use coordinates!!!!!!
		GridBagConstraints gridCon = new GridBagConstraints();
		JPanel gameGrid = new JPanel(coordGrid);
		JPanel previewGrid = new JPanel(coordGrid);
		JPanel holdGrid = new JPanel(coordGrid);
		JPanel[][] preview = new JPanel[4][4]; // 4 rows 4 columns
		JPanel[][] hold = new JPanel[4][4];
		JPanel[][] storeGrid = new JPanel[3][3];
		JLabel next = new JLabel("NEXT");
		JLabel holdlabel = new JLabel("HOLD");
		JLabel storelabel = new JLabel("STORE");
		JLabel escback = new JLabel ("ESC");
		JLabel coins = new JLabel ("coins: " + Integer.toString(playercoins));
		JLabel title = new JLabel("ROTRIS");
		JLabel titlew1 = new JLabel("RF");


		JPanel storelabelP = new JPanel();
		JPanel coinsP = new JPanel ();


		Timer falltime = new Timer();
		Timer boosttime = new Timer();


		BorderLayout storelabelPL = new BorderLayout();

		BorderLayout coinsPL = new BorderLayout();

		storelabelP.setLayout(storelabelPL);
		coinsP.setLayout(coinsPL);


		Border border = BorderFactory.createLineBorder(Color.white, (int)(screeny/1200) + 1); // white border 
		Border storeb = BorderFactory.createLineBorder(mydarkgray, (int)(screeny/450) + 1); 
		Border emptyb = BorderFactory.createEmptyBorder((int)screeny/90 , (int)(screeny/90) , (int)(screeny/90), (int)(screeny/90)); // empty border 
		Border thickb = BorderFactory.createEmptyBorder((int)screeny/50 , (int)(screeny/90) , (int)(screeny/90), (int)(screeny/90)); // empty border 

		Border thinneremptyb = BorderFactory.createEmptyBorder((int)(screeny/180), (int)(screeny/180), (int)(screeny/180), (int)(screeny/180)); 



		Path fontpath = Paths.get("Fipps-Regular.otf"); 



		File thefont = new File(fontpath.toString()); // get the file path from the path

		Font pixel = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(thefont)).deriveFont(Font.PLAIN, (int)(screeny/50)) ;

		Font smallerpixel = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(thefont)).deriveFont(Font.PLAIN, (int)(screeny/75)) ;

		Font biggerpixel = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(thefont)).deriveFont(Font.PLAIN, (int)(screeny/25)) ;



		// start page


		JPanel forendp = new JPanel();

		BoxLayout endlayout = new BoxLayout( forendp, BoxLayout.Y_AXIS);
		forendp.setLayout(endlayout);



		JPanel forname = new JPanel();
		JPanel startbuttonp = new JPanel();
		JPanel botverp = new JPanel();
		JPanel replayp = new JPanel();

		JLabel fornamel = new JLabel("R O T R I S");
		JLabel startbuttonl = new JLabel("START");
		JLabel botverl = new JLabel("WATCH THE BOT");
		JLabel replayl = new JLabel("REPLAY");

		JButton startbuttonb = new JButton();
		JButton botverb = new JButton();
		JButton replayb = new JButton();

		BoxLayout startlayout = new BoxLayout( forstartp, BoxLayout.Y_AXIS);


		Border startempty = BorderFactory.createEmptyBorder((int)screeny/25 , (int)(screeny/90) , (int)(screeny/90), (int)(screeny/90)); // empty border 

		forstartp.setLayout(startlayout);

		botverb.setBackground(mydarkgray);
		botverb.add(botverl);
		botverp.add(botverb);
		botverl.setForeground(Color.white);
		botverl.setFont(pixel);
		botverb.addMouseListener(clickedbotver);
		botverb.setBackground(mypink);
		botverp.setBorder(emptyb);
		botverb.setBorder(emptyb);

		botverp.setBackground(mydarkgray);

		startbuttonb.setBackground(mydarkgray);
		startbuttonb.add(startbuttonl);
		startbuttonp.add(startbuttonb);
		startbuttonp.setBorder(emptyb);
		startbuttonb.setBorder(emptyb);

		startbuttonl.setForeground(Color.white);
		startbuttonl.setFont(pixel);
		startbuttonb.addMouseListener(start);
		startbuttonp.setBackground(mydarkgray);
		startbuttonb.setBackground(mypink);
		replayp.setBackground(mydarkgray);

		replayb.add(replayl);
		replayl.setForeground(Color.white);
		replayl.setFont(pixel);
		replayp.setBackground(mydarkgray);
		replayb.setBorder(emptyb);
		replayb.setBackground(mypink);
		replayp.setBorder(emptyb);
		replayp.add(replayb);
		replayp.setFont(pixel);
		replayb.addMouseListener(replaymouse);

		forname.setBorder(startempty);
		forname.add(fornamel);
		forname.setBackground(mydarkgray);
		fornamel.setForeground(Color.white);
		fornamel.setFont(biggerpixel);

		startp.setBackground(mydarkgray);


		startp.add(forstartp);



		JLabel gameoverl = new JLabel("GAME OVER");
		gameoverl.setForeground(mypink);
		gameoverl.setBackground(mydarkgray);
		gameoverl.setBorder(startempty);
		gameoverl.setFont(pixel);

		JLabel yourscore = new JLabel("YOUR SCORE: " + score);
		yourscore.setForeground(mypink);
		yourscore.setBackground(mydarkgray);
		yourscore.setBorder(emptyb);
		yourscore.setFont(pixel);

		JPanel gameoverp = new JPanel();
		JPanel yourscorep = new JPanel();
		yourscorep.setBackground(mydarkgray);

		gameoverp.add(gameoverl);
		gameoverp.setBackground(mydarkgray);

		yourscorep.add(yourscore);

		forendp.add(gameoverp);
		forendp.add(yourscorep);
		forendp.add(replayp);
		endp.setBackground(mydarkgray);
		forendp.setBackground(mydarkgray);
		endp.add(forendp);








		startp.setPreferredSize(new Dimension ((int) (screenx*0.313125), (int)(screeny*0.725)));
		endp.setPreferredSize(new Dimension ((int) (screenx*0.313125), (int)(screeny*0.725)));







		// store page



		JPanel storeTop = new JPanel();
		JButton back = new JButton();

		StoreItems.makebutton("BUY FILL"); // button 1
		StoreItems.makebutton("BUY BOOST"); // button 2
		StoreItems.makebutton("BUY GRAVITY"); // button 3

		StoreItems.makepanel("<html><br>This power fills<br> one square on <br>the lowest line.<br>"
				+ "COST: 1 COIN</html>"); // panel 4


		StoreItems.makepanel("<html> <br>Receive a score <br> multiplier for the <br>next 30  seconds."
				+ "<br>COST: 2 COINS</html>"); // panel 5

		StoreItems.makepanel("<html><br>Acquire gravity! <br>Let the emptiest <br>column fall. "
				+ "<br>COST: 3 COINS</html>"); // panel 6





		back.setBorder(emptyb);
		back.addMouseListener(mousetest);






		back.add(escback);

		JPanel storeMid = new JPanel();

		JPanel storeBot = new JPanel();




		BorderLayout storelayout = new BorderLayout();

		BoxLayout botlayout = new BoxLayout( storeBot, BoxLayout.X_AXIS);






		storeFront.setVisible(true);
		storeFront.setLayout(storelayout);
		storeTop.setLayout(coordGrid);

		storeBot.setLayout(botlayout);


		storeMid.setPreferredSize(new Dimension ((int) (screenx*0.313125), (int)(screeny*0.6)));
		storeTop.setBackground(mydarkgray);

		storelabelP.add(storelabel);

		storelabelP.setBorder(emptyb);
		coinsP.setBorder(emptyb);


		coinsP.add(coins);

		gridCon.weightx = 1;

		gridCon.weighty = 1;

		gridCon.gridy = 0;

		gridCon.gridx=0;

		storeTop.add(back, gridCon);

		gridCon.gridx=1;

		storeTop.add(storelabelP, gridCon);
		gridCon.gridx=2;

		storeTop.add(coinsP, gridCon);
		storeBot.setBackground(mydarkgray);


		storeTop.setPreferredSize(new Dimension ((int) ((screenx*0.16)), (int)(screeny/16)));
		storeBot.setPreferredSize(new Dimension ((int) ((screenx*0.16)), (int)(screeny/16)));




		storeFront.add(storeTop, BorderLayout.NORTH);

		storeMid.setLayout(coordGrid);



		for (int x = 0; x<2; x++) 
			for ( int y = 0; y<3; y++) { 
				storeGrid[x][y] = new JPanel(); 
				storeGrid[x][y].setBorder(storeb);
				storeGrid[x][y].setBackground(mydarkgray); 
				storeGrid[x][y].setLayout(new BorderLayout());
				// constraints in the layout 


				gridCon.fill = GridBagConstraints.BOTH;
				gridCon.gridx = x; 

				if (x!=1) {
					gridCon.weightx = 1;
				}
				else if (x ==1) {
					gridCon.weightx = 2;
				}
				gridCon.weighty = 1;
				gridCon.gridy = y;
				storeMid.add (storeGrid[x][y], gridCon); // add it to the gridbaglayout
			}


		storeGrid[0][0].add(StoreItems.getpanel(1));
		storeGrid[0][1].add(StoreItems.getpanel(2));
		storeGrid[0][2].add(StoreItems.getpanel(3));


		storeGrid[1][0].add(StoreItems.getpanel(4));
		storeGrid[1][1].add(StoreItems.getpanel(5));
		storeGrid[1][2].add(StoreItems.getpanel(6));



		storeFront.add(storeBot, BorderLayout.SOUTH);
		storeFront.add(storeMid, BorderLayout.CENTER);



		storelabelP.setBackground(mydarkgray);
		coinsP.setBackground(Color.getHSBColor(0.12f, 0.3f, 0.7f));
		storelabel.setForeground(mypink);
		coins.setForeground(Color.white);
		back.setBackground(mypink);
		escback.setForeground(Color.white);
		escback.setFont(smallerpixel);
		storelabel.setFont(pixel);

		coins.setFont(smallerpixel);


		// makes 200 panels
		for (int x = 0; x<10; x++) // for every row (10 rows)
			for ( int y = 0; y<20; y++) { // do 20 vertical sections
				coord[x][y] = new JPanel(); // make new panel 
				coord[x][y].setPreferredSize(new Dimension((int)(screeny/33.33),(int)(screeny/33.33)));
				coord[x][y].setBackground(mydarkgray); // darkkkk mode
				coord[x][y].setName("empty"); // does not have a tetris square in it
				coord[x][y].setBorder(border);
				// constraints in the layout 
				gridCon.gridx = x; 
				gridCon.gridy = y;
				gameGrid.add (coord[x][y], gridCon); // add it to the gridbaglayout
			}




		for (int x = 0; x<4; x++) 
			for ( int y = 0; y<4; y++) { 
				preview[x][y] = new JPanel();  
				preview[x][y].setPreferredSize(new Dimension((int)(screeny/90),(int)(screeny/90)));
				preview[x][y].setBackground(mydarkgray); 
				preview[x][y].setBorder(border);

				// constraints in the layout 
				gridCon.gridx = x; 
				gridCon.gridy = y;
				previewGrid.add (preview[x][y], gridCon); // add it to the gridbaglayout
			}


		for (int x = 0; x<4; x++) 
			for ( int y = 0; y<4; y++) {
				hold[x][y] = new JPanel(); // make new panel 
				hold[x][y].setPreferredSize(new Dimension((int)(screeny/90),(int)(screeny/90)));
				hold[x][y].setBackground(mydarkgray); // darkkkk mode
				hold[x][y].setBorder(border);

				// constraints in the layout 
				gridCon.gridx = x; 
				gridCon.gridy = y;
				holdGrid.add (hold[x][y], gridCon); // add it to the gridbaglayout
			}










		JPanel bottom = new JPanel();
		bottom.setPreferredSize(new Dimension((int)(screeny/3.33),(int)(screeny/16)));
		bottom.setBorder(emptyb);
		bottom.setBackground(mydarkgray);
		JLabel scorel = new JLabel("score: " + Integer.toString(score) + " coins: " + Integer.toString(playercoins));

		scorel.setForeground(Color.white);
		bottom.add(scorel);
		scorel.setFont(smallerpixel);

		JPanel nextp = new JPanel();
		nextp.add(next);

		JPanel holdp = new JPanel();
		holdp.add(holdlabel);


		holdp.setBackground(mydarkgray);
		nextp.setBackground(mydarkgray);
		next.setForeground(Color.white);
		//next.setPreferredSize(new Dimension ((int) ((screenx*0.0625)), (int)(screeny/16)));
		//	holdlabel.setPreferredSize(new Dimension ((int) ((screenx*0.0625)), (int)(screeny/16)));

		nextp.setForeground(Color.white);
		nextp.setPreferredSize(new Dimension ((int) ((screenx*0.0625)), (int)(screeny/16)));
		holdp.setPreferredSize(new Dimension ((int) ((screenx*0.0625)), (int)(screeny/16)));


		holdlabel.setForeground(Color.white);
		next.setFont(smallerpixel);
		holdlabel.setFont(smallerpixel);
		next.setBorder(thinneremptyb);

		holdlabel.setBorder(thinneremptyb);


		JButton gobackb = new JButton();
		JLabel gobackl = new JLabel("BACK");
		gobackl.setForeground(Color.white);
		gobackl.setFont(smallerpixel);
		gobackb.add(gobackl);
		gobackb.addMouseListener(gobackmouse);
		gobackb.setBorder(thinneremptyb);
		gobackb.setBackground(mypink);





		JButton gostoreb = new JButton();
		JLabel gostorel = new JLabel("STORE");
		gostorel.setForeground(Color.white);
		gostorel.setFont(smallerpixel);
		gostoreb.add(gostorel);
		gostoreb.addMouseListener(gostoremouse);
		gostoreb.setBorder(thinneremptyb);
		gostoreb.setBackground(mypink);

		JPanel righttopspacer = new JPanel();
		righttopspacer.setPreferredSize(new Dimension ((int) ((screenx*0.0625)), (int)(screeny/9)));
		righttopspacer.setBackground(mydarkgray);
		righttopspacer.setBorder(thinneremptyb);

		righttopspacer.add(gostoreb);



		JPanel lefttopspacer = new JPanel();
		lefttopspacer.setPreferredSize(new Dimension ((int) ((screenx*0.0625)), (int)(screeny/9)));
		lefttopspacer.setBackground(mydarkgray);
		lefttopspacer.setBorder(thinneremptyb);
		lefttopspacer.add(gobackb);


		JPanel leftmidspacer = new JPanel();
		leftmidspacer.setPreferredSize(new Dimension ((int) ((screenx*0.0625)), (int)(screeny/14)));
		leftmidspacer.setBackground(mydarkgray);


		JPanel leftbotspacer = new JPanel();
		leftbotspacer.setPreferredSize(new Dimension ((int) ((screenx*0.0625)), (int)(screeny/6)));
		leftbotspacer.setBackground(mydarkgray);


		JPanel left = new JPanel();
		JPanel right = new JPanel();
		right.setBackground(mydarkgray);
		left.setBackground(mydarkgray);


		left.setBorder(emptyb);

		title.setFont(pixel);
		title.setBorder(emptyb);
		title.setForeground(Color.getHSBColor(0.0f, 0.3f, 0.7f));


		titlew1.setFont(pixel);
		titlew1.setBorder(emptyb);
		titlew1.setForeground(Color.white);




		JPanel top = new JPanel();
		top.setBackground(mydarkgray);
		
		top.add(title);
		top.setPreferredSize(new Dimension((int)(screeny/3.33),(int)(screeny/16)));
		top.setBorder(thinneremptyb);
		top.setVisible(true);


		BoxLayout xlayout = new BoxLayout(horizontalmainp, BoxLayout.X_AXIS);

		BoxLayout ylayout = new BoxLayout(mainp, BoxLayout.Y_AXIS);
		BoxLayout ylayoutl = new BoxLayout(left, BoxLayout.Y_AXIS);
		BoxLayout ylayoutr = new BoxLayout(right, BoxLayout.Y_AXIS);




		left.setLayout(ylayoutl);
		right.setLayout(ylayoutr);
		right.setPreferredSize(new Dimension ((int) ((screenx*0.08)), (int)(screeny/3)));



		left.add(lefttopspacer);
		left.add(nextp);
		left.add(previewGrid);
		left.add(leftmidspacer);
		left.add(holdp);
		left.add(holdGrid);
		left.add(leftbotspacer);


		right.add(righttopspacer);
		right.setBorder(emptyb);

		main.requestFocus();


		Path keypath = Paths.get("key1.png"); 
		File keyfile = new File(keypath.toString()); // get the file path from the path
		BufferedImage key1 = ImageIO.read(keyfile);

		JButton keybutton1 = new JButton(new ImageIcon (key1));
		JPanel key1stats = new JPanel();
		JPanel key1stats2 = new JPanel();
		JLabel key1statsL = new JLabel("FILL");
		JLabel key1statsL2 = new JLabel ( fillavail + " AVAIL");

		key1stats.add(key1statsL);
		key1stats2.add(key1statsL2);

		key1stats.setBackground(mydarkgray);
		key1stats2.setBackground(mydarkgray);

		key1statsL.setForeground(Color.white);
		key1statsL.setFont(smallerpixel);
		key1statsL2.setForeground(Color.white);
		key1statsL2.setFont(smallerpixel);

		keybutton1.setBackground(mydarkgray);
		keybutton1.setBorder(emptyb);
		keybutton1.addMouseListener(mousekey1);


		JPanel key1panel = new JPanel();
		key1panel.setBackground(mydarkgray);

		key1panel.add(keybutton1);
		right.add(key1panel);
		right.add(key1stats);
		right.add(key1stats2);





		keypath = Paths.get("key2.png"); 
		keyfile = new File(keypath.toString()); // get the file path from the path
		BufferedImage key2 = ImageIO.read(keyfile);
		JButton keybutton2 = new JButton(new ImageIcon (key2));
		JPanel key2stats = new JPanel();
		JPanel key2stats2 = new JPanel();
		JLabel key2statsL = new JLabel("BOOST");
		JLabel key2statsL2 = new JLabel ( boostavail + " AVAIL");

		key2stats.add(key2statsL);
		key2stats2.add(key2statsL2);

		key2stats.setBackground(mydarkgray);
		key2stats2.setBackground(mydarkgray);

		key2statsL.setForeground(Color.white);
		key2statsL.setFont(smallerpixel);
		key2statsL2.setForeground(Color.white);
		key2statsL2.setFont(smallerpixel);

		keybutton2.setBackground(mydarkgray);
		keybutton2.setBorder(emptyb);
		keybutton2.addMouseListener(mousekey2);

		JPanel key2panel = new JPanel();
		key2panel.setBackground(mydarkgray);

		key2panel.add(keybutton2);
		right.add(key2panel);
		right.add(key2stats);
		right.add(key2stats2);



		keypath = Paths.get("key3.png"); 
		keyfile = new File(keypath.toString()); // get the file path from the path
		BufferedImage key3 = ImageIO.read(keyfile);
		JButton keybutton3 = new JButton(new ImageIcon (key3));
		JPanel key3stats = new JPanel();
		JPanel key3stats2 = new JPanel();
		JLabel key3statsL = new JLabel("GRAVITY");
		JLabel key3statsL2 = new JLabel ( gravityavail + " AVAIL");

		key3stats.add(key3statsL);
		key3stats2.add(key3statsL2);

		key3stats.setBackground(mydarkgray);
		key3stats2.setBackground(mydarkgray);

		key3statsL.setForeground(Color.white);
		key3statsL.setFont(smallerpixel);
		key3statsL2.setForeground(Color.white);
		key3statsL2.setFont(smallerpixel);

		keybutton3.setBackground(mydarkgray);
		keybutton3.setBorder(emptyb);
		keybutton3.addMouseListener(mousekey3);

		JPanel key3panel = new JPanel();
		key3panel.setBackground(mydarkgray);

		key3panel.add(keybutton3);
		right.add(key3panel);
		right.add(key3stats);
		right.add(key3stats2);


		keypath = Paths.get("key4.png"); 
		keyfile = new File(keypath.toString()); // get the file path from the path
		BufferedImage key4 = ImageIO.read(keyfile);
		JButton keybutton4 = new JButton(new ImageIcon (key4));
		JPanel key4stats = new JPanel();
		JPanel key4stats2 = new JPanel();
		JLabel key4statsL = new JLabel("CHEAT");
		JLabel key4statsL2 = new JLabel ( "UNLIMITED");

		key4stats.add(key4statsL);
		key4stats2.add(key4statsL2);

		key4stats.setBackground(mydarkgray);
		key4stats2.setBackground(mydarkgray);

		key4statsL.setForeground(Color.white);
		key4statsL.setFont(smallerpixel);
		key4statsL2.setForeground(Color.white);
		key4statsL2.setFont(smallerpixel);

		keybutton4.setBackground(mydarkgray);
		keybutton4.setBorder(emptyb);
		keybutton4.addMouseListener(mousekey4);

		JPanel key4panel = new JPanel();
		key4panel.add(keybutton4);
		right.add(key4panel);
		key4panel.setBackground(mydarkgray);
		right.add(key4stats);
		right.add(key4stats2);



		Canvas acanvas = new Canvas();
		acanvas.setSize(150,450);

		right.add(acanvas);
		main.pack();





		horizontalmainp.setLayout(xlayout);
		horizontalmainp.add(left);
		horizontalmainp.add(mainp);
		horizontalmainp.add(right);

		switchp(main, base, startp);


		mainp.setBackground(mydarkgray);
		mainp.setLayout(ylayout);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setResizable(true);
		main.setTitle("ROTRIS");
		mainp.add(top);
		mainp.add(gameGrid);
		mainp.add(bottom);		

		main.add(base);

		mainp.setVisible(true);




		main.setVisible(true);
		mainp.setBackground(mydarkgray);


		main.setExtendedState(JFrame.MAXIMIZED_BOTH);
		Thread mybot = new Thread(new TetrisBotMaybe ());
		
		
		//mybot.start();




		forstartp.setBackground(mydarkgray);


		forstartp.add(forname);



		forstartp.add(startbuttonp);


		forstartp.add(botverp);

		JPanel hi = Decor2.getdecor2();
		hi.setBorder(thickb);

		forstartp.add(hi);

		while(gameover == true || startedgame == false) {

			paused(25);
			Decor2.repaintdecor2();
			Decor2.getnewdecor2();
			startp.repaint();

		}
	forstartp.remove(hi);
// only sparkles in the beginning



		pretype = gettype();
		int horizontal = 0;
		int vertical = 0;



		boolean exit = false;


		while(exit == false) {




			paused(5);

			while (gameover == false ) {
				scorel.setText("score: " + Integer.toString(score) + " coins: " + Integer.toString(playercoins));


				vertical = 0;
				horizontal = 0;
				count = count + 1;

				if (count <50) {
					length = (int)(50-0.5*count);
				}
				else {length = 25;}




				type = pretype;


				if (type == 'I') {
					fallingtype = 'I';

					coord[4][0].setBackground(mypink);
					coord[4][1].setBackground(mypink);
					coord[4][2].setBackground(mypink);
					coord[4][3].setBackground(mypink);

					coord[4][0].setName("falling");
					coord[4][1].setName("falling");
					coord[4][2].setName("falling");
					coord[4][3].setName("falling");


				}

				else if (type == 'L') {
					fallingtype = 'L';

					coord[4][0].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
					coord[4][1].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
					coord[4][2].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
					coord[5][2].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));

					coord[4][0].setName("falling");
					coord[4][1].setName("falling");
					coord[4][2].setName("falling");
					coord[5][2].setName("falling");
				}

				else if (type == 'T') {

					fallingtype = 'T';

					coord[4][0].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
					coord[3][1].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
					coord[4][1].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
					coord[5][1].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));

					coord[4][0].setName("falling");
					coord[3][1].setName("falling");
					coord[4][1].setName("falling");
					coord[5][1].setName("falling");
				}

				else if (type == 'Z') {
					fallingtype = 'Z';

					coord[4][0].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
					coord[5][0].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
					coord[5][1].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
					coord[6][1].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));

					coord[4][0].setName("falling");
					coord[5][0].setName("falling");
					coord[5][1].setName("falling");
					coord[6][1].setName("falling");
				}

				else if (type == 'O') {
					fallingtype = 'O';

					coord[4][0].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
					coord[4][1].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
					coord[5][0].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
					coord[5][1].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));

					coord[4][0].setName("falling");
					coord[4][1].setName("falling");
					coord[5][0].setName("falling");
					coord[5][1].setName("falling");
				}
				else if (type == 'J') {
					fallingtype = 'J';

					coord[4][2].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
					coord[5][0].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
					coord[5][1].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
					coord[5][2].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));

					coord[4][2].setName("falling");
					coord[5][0].setName("falling");
					coord[5][1].setName("falling");
					coord[5][2].setName("falling");
				}
				else if (type == 'S') {
					fallingtype = 'S';

					coord[4][0].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
					coord[5][0].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
					coord[4][1].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
					coord[3][1].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));

					coord[4][0].setName("falling");
					coord[5][0].setName("falling");
					coord[4][1].setName("falling");
					coord[3][1].setName("falling");


				}
				paused(10);

				pretype = gettype();
				TetrisBotMaybe.setbotstatus(false);

				setblock(preview, pretype);
				main.pack();

				do{


					canfall = true;
					falltime.startpause(); // start the fall count


					if (Boosts.getboosted() == true) { // if the scoreboost is active 

						if (boosttime.ispaused(3000) == false) {// boost score for 30 seconds 


							scoreboost = Boosts.getscoreboost();
						}
						else {Boosts.setboosted(false);}

					}
					else {
						scoreboost = 1;
					}
					main.addKeyListener(mainkeylistener);
					main.requestFocus();
					// thank u @ http://www.javaprogrammingforums.com/whats-wrong-my-code/3535-my-keylistener-not-working.html

					while (falltime.ispaused(length) == false ) { // while not falling




						if(key == 27 && wait == false) { // if key is ESC = pause and switch to store
							key = 0;
							wait = true;

							switchp(main, base, storeFront);
							onstorepage = true;

							while (onstorepage == true) {
								main.requestFocus();
								// sometimes still loses main key listener and main mouse listener


								currentbutton = StoreItems.getbuttonclicked();
								if(currentbutton == 1) {
									StoreItems.resetbutton();


									if (playercoins >=1) {
										fillavail = fillavail+1;
										key1statsL2.setText(fillavail + " AVAIL");

										playercoins = playercoins-1;
										coins.setText("coins: " + Integer.toString(playercoins));
										scorel.setText("score: " + Integer.toString(score) + " coins: " + Integer.toString(playercoins));

									}


								}

								else if(currentbutton == 2) {
									StoreItems.resetbutton();

									if (playercoins >=2) {


										boostavail = boostavail+1;
										key2statsL2.setText(boostavail + " AVAIL");

										playercoins = playercoins-2;
										coins.setText("coins: " + Integer.toString(playercoins));
										scorel.setText("score: " + Integer.toString(score) + " coins: " + Integer.toString(playercoins));


									}

								}

								else if(currentbutton == 3) {

									StoreItems.resetbutton();

									if (playercoins >=3) {


										gravityavail = gravityavail+1;
										key3statsL2.setText(gravityavail + " AVAIL");

										playercoins = playercoins-3;
										coins.setText("coins: " + Integer.toString(playercoins));
										scorel.setText("score: " + Integer.toString(score) + " coins: " + Integer.toString(playercoins));


									}

								}










								if (key == 27 && wait == true) {
									key = 0;
									wait = false;
									onstorepage = false;
									switchp(main, base, horizontalmainp);}
							}


						}




						if (key == 49) // 1 key

						{key = 0;


						if (fillavail >0) {
							Boosts.Fill(coord);
							fillavail = fillavail -1;

							key1statsL2.setText(fillavail + " AVAIL");

						}


						}


						if (key == 32 ) // spacebar = hold block
						{

							key = 0;
							if (onhold == false) { // if nothing is on hold

								holdtype = pretype;
								setblock(hold, holdtype);
								onhold = true;
								pretype = gettype();
								setblock(preview, pretype);
							}
							else {
								setblock (hold, pretype);
								pretype = holdtype;
								holdtype = currenttype;

								setblock (preview, pretype);

							}
						}

						if (key == 50)  // number 2 score boost
						{
							key = 0;

							if (boostavail>0) {
								Boosts.DoubleScore(scoreboost);
								boosttime.startpause();

								boostavail = boostavail-1;
								key2statsL2.setText(boostavail + " AVAIL");
							}


						}

						if (key == 51)  // number 3 fall
						{



							key = 0;

							if (gravityavail>0) {
								Boosts.Gravity(coord);

								gravityavail = gravityavail-1;
								key3statsL2.setText(gravityavail + " AVAIL");
							}


						}

						if (key == 52) {

							// press 4 to cheat (and for easier marking)
							key = 0;

							for (int x=0; x<13; x++) {
								paused(3);

								playercoins = playercoins + 1;
								scorel.setText("score: " + Integer.toString(score) + " coins: " + Integer.toString(playercoins));
								// a prettier effect than just doing +12

							}

							coins.setText("coins: " + Integer.toString(playercoins));

						}


						if (key == 53) {
							// for testing bot



							if (startedbot == false) {
								mybot.start();							}



							else if (TetrisBotMaybe.getbot() == true){


								TetrisBotMaybe.setbot(false);
							}
							else {
								
								
								TetrisBotMaybe.setbot(true);

							}
							startedbot = true;



							key =0;


						}


						if (key == 40) // down arrow go down for more speed
						{	
							key = 0;

							for (int x = 0; x<10; x++) 
								for ( int y = 19; y>=0; y--) { 

									if (coord[x][y].getName().equals("falling") && canfall !=false) { // if its falling

										if (y==19) {canfall = false;} // but its at the 19th row
										else 	if (
												coord[x][y+1].getName().equals("full") 	)
										{	canfall = false;}


									}}

							if (canfall == false) {
								for (int x = 0; x<10; x++)
									for ( int y = 19; y>=0; y--) {
										if (coord[x][y].getName().equals("falling")
												||coord[x][y].getName().equals("doneleft")
												||coord[x][y].getName().equals("doneright") 
												) 
										{	coord[x][y].setName("full"); 
										}
									}
							}

							else {
								score = score + 1*scoreboost;
								scorel.setText("score: " + Integer.toString(score) + " coins: " + Integer.toString(playercoins));
								play("higher key.wav");
							}
							for (int x = 0; x<10; x++) // for every row (10 rows)
								for ( int y = 19; y>=0; y--) { // do 20 vertical sections from bottom to top

									// if its falling
									if (coord[x][y].getName().equals("falling")) {
										// set it to dark gray
										coord[x][y].setBackground(mydarkgray);
										//name it empty
										coord[x][y].setName("empty");

										futurex = x;
										futurey = y+1;

										coord[futurex][futurey].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
										coord[futurex][futurey].setName("falling");}
								}

						}




						//rotation time 


						// rotationnnn

						// so its easy to find this segment

						if (key == 38) {
							canrotate = true;
							key = 0;

							counter = 0;
							for (int y=0; y<20;y++) for (int x = 0; x<10;x++){ // check from up to down left to right 

								if(coord[x][y].getName().equals("falling")) {

									if(counter ==0) {
										x0 = x;
										y0 = y;
									}

									else if(counter ==1) {
										x1 = x;
										y1 = y;
									}

									else if(counter ==2) {
										x2 = x;
										y2 = y;
									}

									else if(counter ==3) {
										x3 = x;
										y3 = y;
									}

									counter = counter + 1;
									coord[x][y].setBackground(mydarkgray);
									coord[x][y].setName("empty");


								}
								// done changing the falling to empty

							}// done checking if things are falling



							// smallest y check
							if(y0<=y0 && y0 <= y1 && y0<=y2 && y0<=y3) {
								smallesty = y0;
							}
							else if(y1<=y0 && y1 <= y1 && y1<=y2 && y1<=y3) {
								smallesty = y1;
							}
							else if(y2<=y0 && y2 <= y1 && y2<=y2 && y2<=y3) {
								smallesty = y2;
							}
							else {smallesty = y3;}

							// smallest x check
							if(x0<=x0 && x0 <= x1 && x0<=x2 && x0<=x3) {
								smallestx = x0;
							}
							else if(x1<=x0 && x1 <= x1 && x1<=x2 && x1<=x3) {
								smallestx = x1;
							}
							else if(x2<=x0 && x2 <= x1 && x2<=x2 && x2<=x3) {
								smallestx = x2;
							}
							else {smallestx = x3;}



							x0 = x0 - smallestx;
							x1 = x1 - smallestx;
							x2 = x2 - smallestx;
							x3 = x3 - smallestx;
							y0 = y0 - smallesty;
							y1 = y1 - smallesty;
							y2 = y2 - smallesty;
							y3 = y3 - smallesty;


							coord[x0][y0].setName("gorotate");
							coord[x1][y1].setName("gorotate");
							coord[x2][y2].setName("gorotate");
							coord[x3][y3].setName("gorotate");

							key = 0; // done getting new coordinates to be red


							// now rotate the new coordinates

							horizontalshift = 0;

							for (int y=0; y<20;y++) for (int x = 0; x<10;x++){ 

								if (coord[x][y].getName().equals("gorotate")){// check all rotating blocks

									futurey = x+smallesty+1;
									futurex = 3-y+smallestx-2;

									while (futurex+horizontalshift>9) {
										horizontalshift = horizontalshift - 1;
									}

									while (futurex+horizontalshift<0) {
										horizontalshift ++;
									}

								} // end of if its name is go rotate
							} // end of for 0 to 20

							if (futurey>19) {canrotate = false;	} // cant rotate if its at the bottom row 




							for (int x = 0; x<10; x++) for (int y = 0; y<20; y++){

								{if (coord[x][y].getName().equals("gorotate")){

									futurey = x+smallesty+1;
									futurex = 3-y + horizontalshift+smallestx-2;


									if (futurey < 20 && futurex< 10) {
										if (coord[x][y].getName()== "gorotate" && (coord[futurex][futurey].getName()!= "empty") && (coord[futurex][futurey].getName()!= "gorotate")){
											canrotate = false;
										}}
									
									else {canrotate = false;}
								} //end of test to see if its rotateable

								}}
							
							
							if (canrotate == true) {

								play("higher key.wav");
								for (int y=0; y<20;y++) for (int x = 0; x<10;x++){


									if (coord[x][y].getName().equals("gorotate")){

										futurey = x+smallesty+1;
										futurex = 3-y + horizontalshift+smallestx-2;

										coord[x][y].setName("empty");

										coord[futurex][futurey].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
									}
								} // end of for
							}

							// other colour change
							else {
								for (int y=0; y<20;y++) for (int x = 0; x<10;x++){ 

									if (coord[x][y].getName().equals("gorotate")){
										coord[x][y].setBackground(mydarkgray);
										coord[x+smallestx][y+smallesty].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
										coord[x+smallestx][y+smallestx].setName("falling");
									}
								}

							} // end of else
						} // end of key == 38



						// done rotate


						// make any missing blocks fall
						for (int y=0; y<20;y++) for (int x = 0; x<10;x++){ 

							if (coord[x][y].getBackground().equals(Color.getHSBColor(0.0f, 0.3f, 0.7f)) && coord[x][y].getName().equals("full") == false) {
								coord[x][y].setName("falling");}



							if(coord[x][y].getBackground().equals(mydarkgray) && coord[x][y].getName().equals("empty")== false) {
								coord[x][y].setName("empty");
							}
						};




						canside = true; // let it go;

						if (key == 39) { // if moving right

							key = 0;

							for (int y=19; y>=0;y--) for (int x = 9; x>=0;x--){ // check from down to up right to left and move blocks one at a time

								if(coord[x][y].getName().equals("falling")) { // if its the current block

									if (x<9) {
										if (coord[x+1][y].getName().equals("full")==true) {
											canside = false;

										}	// end of if its full

									}
									else if (x==9) {
										canside = false;
									} // end of else if
								} // end of if its falling
							} // end of for



							if (canside == true){

								for (int y=19; y>=0;y--) for (int x = 9; x>=0;x--){
									// check all coordinates
									if (coord[x][y].getName().equals("falling")) {

										coord[x][y].setName("goright"); }

									// if the squares are currently falling, make it goright

									if (coord[x][y].getName().equals("goright"))

									{ // if its supposed to go right
										coord[x][y].setBackground(mydarkgray); // make the current empty (aka dark gray)
										coord[x][y].setName("empty");

										coord[x+1][y].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));  // fill it with red
										coord[x+1][y].setName("doneright"); // and set its status as done 


									}
								}// end of go right
								canside = false;



								for (int y=19; y>=0;y--) for (int x = 9; x>0;x--){

									if (coord[x][y].getName().equals("doneright")) { // if its done moving right
										// if it was set as falling in the previous block some would just keep going right so its gotta be outside the checker
										coord[x][y].setName("falling"); // set it as falling for the next cycle
									}

								}


								play("key.wav");
							}

						}// end of right click



						// left key version
						if (key == 37) { // if moving left

							key = 0;

							for (int y=19; y>=0;y--) for (int x = 9; x>=0;x--){ // check from down to up left to left and move blocks one at a time

								if(coord[x][y].getName().equals("falling")) { // if its the current block

									if (x>0) {
										if (coord[x-1][y].getName().equals("full")==true) {
											canside = false;

										}	

									}
									else if (x==0) {
										canside = false;
									}}}



							if (canside == true){

								for (int y=19; y>=0;y--) for (int x = 0; x<10;x++){
									if (coord[x][y].getName().equals("falling")) {

										coord[x][y].setName("goleft"); }

									if (coord[x][y].getName().equals("goleft"))

									{ // if its supposed to go left
										coord[x][y].setBackground(mydarkgray); // make the current empty (aka dark gray)
										coord[x][y].setName("empty");

										coord[x-1][y].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));  // fill it with red
										coord[x-1][y].setName("doneleft"); // and set its status as done 


									}
								}// end of go left
								canside = false;



								for (int y=19; y>=0;y--) for (int x = 9; x>0;x--){

									if (coord[x][y].getName().equals("doneleft")) { // if its done moving left
										// if it was set as falling in the previous block some would just keep going left so its gotta be outside the checker
										coord[x][y].setName("falling"); // set it as falling for the next cycle
									}

								}


								play("key.wav")
								;}}// end of left click


						// done left key version










					} // end of the not falling time, time to move down


					if (startedgame == true) {



						canside = false;
						for (int x = 0; x<10; x++) 
							for ( int y = 19; y>=0; y--) { 

								if (coord[x][y].getName().equals("falling")) { // if its falling

									if (y==19) {canfall = false;} // but its at the 19th row
									else if (
											coord[x][y+1].getName().equals("full") 

											){
										canfall = false;}


								}}

						if (canfall == false) {
							for (int x = 0; x<10; x++)
								for ( int y = 19; y>=0; y--) {
									if (coord[x][y].getName().equals("falling")||coord[x][y].getName().equals("doneleft")||coord[x][y].getName().equals("doneright") ) {

										coord[x][y].setName("full");
										
										// when it cant fall but its not already full or empty it is full now
									}
								}

						}

						for (int x = 0; x<10; x++) 
							for ( int y = 19; y>=0; y--) {

								if (coord[x][y].getName().equals("falling")) {
									coord[x][y].setBackground(mydarkgray);
									coord[x][y].setName("empty");


									coord[x][y+1].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
									coord[x][y+1].setName("falling");}
							}



						for (int y=0; y<20; y++) {

							if(
									(coord[0][y].getName()).equals("full") &&
									(coord[1][y].getName()).equals("full") &&
									(coord[2][y].getName()).equals("full") &&
									(coord[3][y].getName()).equals("full") &&
									(coord[4][y].getName()).equals("full") &&
									(coord[5][y].getName()).equals("full") &&
									(coord[6][y].getName()).equals("full") &&
									(coord[7][y].getName()).equals("full") &&
									(coord[8][y].getName()).equals("full") &&
									(coord[9][y].getName()).equals("full")

									) {
								for (int x=0; x<10; x++) {
									coord[x][y].setBackground(Color.white);
									coord[x][y].setName("empty");

								}
								paused(10);
								for (int x=0; x<10; x++) {
									coord[x][y].setBackground(mydarkgray);
									coord[x][y].setName("empty");

								}
								paused(5);
								for (int x=0; x<10; x++) {
									coord[x][y].setBackground(Color.pink);
									coord[x][y].setName("empty");

								}
								paused(10);



								for (int x=0; x<10; x++) {
									coord[x][y].setBackground(mydarkgray);
									coord[x][y].setName("empty");

								}


								// move everything else down
								for (y =y; y>0; y--) 
									for (int x = 0; x<10; x++){
										if (coord[x][y].getName().equals("full")) {
											coord[x][y].setName("empty");
											coord[x][y].setBackground(mydarkgray);

											futurex = x;
											futurey = y+1;
											coord[futurex][futurey].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
											coord[futurex][futurey].setName("full");}}

								score = score + 20*scoreboost;
								playercoins = playercoins + 1;
								coins.setText("coins: " + Integer.toString(playercoins));
								scorel.setText("score: " + Integer.toString(score) + " coins: " + Integer.toString(playercoins));
								yourscore.setText("YOUR SCORE: " + Integer.toString(score));

								play("clear line.wav");


							}
						};
					}
				}while (canfall == true); //stop the block when it reaches the bottom


				if (coord[4][0].getName().contentEquals("full")|| // blocks spawn at 4 and 5 usually so if its full then the game is over
						coord[5][0].getName().contentEquals("full")){

					gameover = true;
				}


			}	





			yourscore.setText("YOUR SCORE: " + Integer.toString(score));

			length = 0;
			scoreboost = 1;
			
			// restarting meaning to make the whole grid empty

			for (int x=0; x<10; x++)
				for (int y=0; y<20; y++) {

					coord[x][y].setName("empty");
					coord[x][y].setBackground(mydarkgray);

				}


			switchp(main, base, endp);
			// switch to the end 
			paused(50);

		}

	}
}


