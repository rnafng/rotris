package rotris;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class Decor2 {
	
	// number 2 because i ditched the first one 
	public static JPanel decor2 = new JPanel();
	
	static Rectangle screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();

	static int screenx = (int) (screen.getWidth());

	static int randomx[] = new int[10];
	public static JPanel flashes[][] = new JPanel[20][20];
	static int randomy[] = new int[10];

	static int screeny = (int) (screen.getHeight());
	public static Color mydarkgray = Color.getHSBColor(0f, 0f, 0.1f);

	static GridBagLayout coordGrid = new GridBagLayout(); 
	static GridBagConstraints gridCon = new GridBagConstraints();

	static JPanel getdecor2() {



		decor2.setPreferredSize(new Dimension ((int) (screenx*0.313125), (int)(screeny*0.15)));
		decor2.setLayout(coordGrid);


		decor2.setBackground(mydarkgray);

		for (int x=0; x<20; x++)

			for (int y =0; y<20; y++) {

				flashes[x][y] = new JPanel();

				gridCon.weightx = 1;

				gridCon.weighty = 1;
				gridCon.gridx = x; 
				gridCon.gridy = y;
				flashes[x][y].setBackground(mydarkgray);
				decor2.add (flashes[x][y], gridCon);

			}




		decor2.repaint();
		return decor2;}


	static void repaintdecor2() {


		for (int x=0; x<10; x++) {

			flashes[randomx[x]][randomy[x]].setBackground(mydarkgray);

			

			randomx[x] = (int)(Math.random()*19);
			randomy[x] = (int)(Math.random()*19);

			flashes[randomx[x]][randomy[x]].setBackground(Color.white);

			decor2.repaint();
		}
	}
	
	static JPanel getnewdecor2() {
		
		return decor2;
	}



}
