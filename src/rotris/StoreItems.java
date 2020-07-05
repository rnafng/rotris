package rotris;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class StoreItems {

	
	static Color mydarkgray = Color.getHSBColor(0f, 0f, 0.1f);

	public static int storekey;

	KeyListener side = new KeyListener()
	{	public void keyTyped(KeyEvent e) {	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		storekey = e.getKeyCode();


	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub		

	};

	};


	static int count = 0;
	static int buttonclicked =0;

	static JButton storeb[] = new JButton[7];
	static JLabel storel[] = new JLabel[7];
	static JPanel storep[] = new JPanel [7];
	


	static MouseListener mouse[] = new MouseListener[4];
	static Rectangle screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();


	static int screenx = (int) (screen.getWidth());
	static int screeny = (int) (screen.getHeight());
	 static Border emptyb = BorderFactory.createEmptyBorder((int)screeny/90 , (int)(screeny/90) , (int)(screeny/90), (int)(screeny/90)); // empty border 
	 static Border emptytop = BorderFactory.createEmptyBorder((int)screeny/17 , (int)(screeny/90) , (int)(screeny/90), (int)(screeny/90)); // empty border 

	static Font storefont = new Font("Arial Black", Font.BOLD, (int)(screeny/50));


	static void makemice() {




		mouse[1] = new MouseListener() {
			public void mouseclicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub



			}

			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

				System.out.println(1);

				buttonclicked = 1;
			}


			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}


			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		};



		mouse[2] = new MouseListener() {
			public void mouseclicked(MouseEvent e) {
				// TODO Auto-generated method stub



			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub



			}

			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

				buttonclicked = 2;
				System.out.println(2);
			}


			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}


			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		};


		mouse[3] = new MouseListener() {
			public void mouseclicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub



			}

			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

				System.out.println(3);
				buttonclicked = 3;
			}


			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}


			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		};


	}

	static  void makebutton(String text) throws  Exception{

		count = count+1;

		storep[count] = new JPanel();
		storep[count].setBackground(mydarkgray);
		storeb[count] = new JButton();
		storel[count] = new JLabel();

		storel[count].setForeground(Color.white);
		storeb[count].setBackground(Color.getHSBColor(0.12f, 0.3f, 0.7f));
		storeb[count].add(storel[count]);
		storeb[count].setBorder(emptyb);
		storep[count].add(storeb[count]);
		storep[count].setBorder(emptytop);
		storeb[count].addMouseListener(mouse[count]);
		storel[count].setFont(storefont);
		storel[count].setText(text);}


	static void makepanel(String text) throws  Exception{

		count = count+1;
		storep[count] = new JPanel();
		storel[count] = new JLabel();
		storel[count].setForeground(Color.white);
		storep[count].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
		storep[count].add(storel[count]);
		storel[count].setText(text);
		storel[count].setFont(storefont);

		storep[count].revalidate();}


	static JButton getbutton(int thecount) {
		count = thecount;
		return storeb[count];}

	static JPanel getpanel(int thecount) {
		count = thecount;
		return storep[count];}

	static JLabel getlabel(int thecount) {

		return storel[count];
	}



	static void resetbutton() {
		buttonclicked = 0;


	}
	static int getbuttonclicked() {

		return buttonclicked;

	}

	static int getkey() {

		return storekey;
	}

	static void setFont(Font f) {

		for(int x=0; x<7; x++) {
			storel[x].setFont(f);

		}


	}



}
