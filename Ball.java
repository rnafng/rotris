package RetroPingPong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Ball extends JPanel implements ActionListener
{
	Timer timer = new Timer(5,this);
	double x=350, velocityy=-2, velocityx=-2;
	double y = (double)(1 + Math.random() * 490);
	
	public void paintComponent(Graphics graphics)
	{
		super.paintComponent(graphics);
		Graphics2D g1 = (Graphics2D)graphics;
		Ellipse2D ball = new Ellipse2D.Double(x,y,30,30);
		
		setForeground(Color.white);
		g1.fill(ball);
		timer.start();
	}

	public void actionPerformed(ActionEvent ActionEventOne)
	{
		if(x<0 || x>660)
		{
			velocityx = -velocityx;
		}

		if(y<0 || y>430)
		{
			velocityy = -velocityy;
		}
		
		x = velocityx + x;
		y = velocityy + y;
		repaint();
	}
}