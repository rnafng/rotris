package rotris;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
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
import javax.swing.JLabel;
import javax.swing.border.Border;

public class StoreButtons {

	
	static int count = 0;
	static JButton storeb[] = new JButton[9];
	static JLabel storel[] = new JLabel[9];
	static Rectangle screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
	static Font thefont;
	
	static int screenx = (int) (screen.getWidth());
	static int screeny = (int) (screen.getHeight());

	
	
	
	static
	MouseListener mousetest = new MouseListener() {



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

	
	static void setFont() throws FileNotFoundException, FontFormatException, IOException {
		
		 Path fontpath1 = Paths.get("Fipps-Regular.otf"); 

		 
		 
		 File thefont1 = new File(fontpath1.toString()); // get the file path from the path

		Font pixel = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(thefont1)).deriveFont(Font.PLAIN, (int)(screeny/50)) ;


	     thefont = pixel;
		Path fontpath = Paths.get("Fipps-Regular.otf"); 
		
		
	

	}
	static void makebutton(String text) throws  Exception{
		
		count = count+1;
		storeb[count] = new JButton();
		storel[count] = new JLabel();
		storel[count].setForeground(Color.white);
		storel[count].setFont(thefont);
		storeb[count].setBackground(Color.getHSBColor(0.0f, 0.3f, 0.7f));
		storeb[count].addMouseListener(mousetest);
		storeb[count].add(storel[count]);
		storel[count].setText(text);}
	
	static JButton getbutton(int thecount) {
		count = thecount;
		return storeb[count];}
	
	

}
