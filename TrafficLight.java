// TrafficLight.java

/**
 *  cscie10b
 *  Problem Set 6: Part 1
 *
 *  This program creates a traffic light look alike. It grows and shrinks as the 
 *  window changes. The stoplight is always centered.
 *
 *  
 *
 *  @author Seth McNaughton
 *  @version Created 4/13/2014
 */ 

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class TrafficLight
{
	int panelHeight;
	int panelWidth;
	int diameter;
	myDrawingPanel mdp;
	
	public static void main (String [] args)
	{
		TrafficLight t = new TrafficLight ();
		t.drawIt();
	}

	public void drawIt()
	{
		JFrame f = new JFrame ("Traffic Light");
		f.setSize (203,600);
		mdp = new myDrawingPanel();
		f.add (mdp);
		f.setVisible (true);
		f.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		
		f.addComponentListener(new Resizer());
	}

	//Resizes circles, depending on panel size
	class Resizer extends ComponentAdapter
	{
		public void componentResized(ComponentEvent e)
		{
			panelHeight = mdp.getSize().height;
			panelWidth = mdp.getSize().width;
			diameter = Math.min (panelHeight/3, panelWidth);
			mdp.repaint();
		}
	}

	class myDrawingPanel extends JPanel
	{
		public void paintComponent (Graphics g)
		{
			int d = diameter;
			int c = (panelWidth - d)/2;
			
			g.setColor (Color.RED);
			g.fillOval (c,0,d,d);

			g.setColor (Color.YELLOW);
			g.fillOval (c,d,d,d);

			g.setColor (Color.GREEN);
			g.fillOval (c,2*d,d,d);

			g.setColor (Color.BLACK);
			g.fillRect (0,d,panelWidth,2);

			g.setColor (Color.BLACK);
			g.fillRect (0,2*d,panelWidth,2);
		}
	}
}


