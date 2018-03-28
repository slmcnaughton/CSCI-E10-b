// FifteenPuzzle.java

/**
 *  This program creates the fifteen puzzle. The objective is to get the numbers 
 *  1-15 organized from left to right/top to bottom. There is a shuffle button 
 *  included that creates an authentic shuffle (as opposed to randomly placing numbers
 *  on the board which can lead to unsolvable games).
 *
 * 
 *  @author Seth McNaughton
 *  @version Created 4/13/2014
 */ 

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


class FifteenPuzzle
{
	public static void main (String [] args)
	{
		NewPuzzleFrame frame = new NewPuzzleFrame ("Fifteen Puzzle", 500, 500);
		
		frame.setVisible(true);			//make frame show on screen
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close on exit

		JOptionPane.showMessageDialog (null, "Are you ready for the fifteen" 
					+ " puzzle? \nGet all fifteen boxes aligned from left to right,"
					+ " and top to bottom. \nPress 'Shuffle' to begin!");
	}
}

class NewPuzzleFrame extends JFrame
{
	private JButton buttons [][] = new JButton [4][4];
	private JButton shuffle = new JButton("Shuffle");
	private int row;
	private int column;
	private int targetRow;
	private int targetColumn;


	/**
	 *  Constructs a NewPuzzleFrame of size h x w
	 */
	public NewPuzzleFrame (String name, int h, int w)
	{
		setTitle (name);
		setSize (w,h);
		Font font = new Font ("Helvetica", Font.BOLD, 34);
		setLocationRelativeTo(null) ;		//Starts frame up in center screen

		JPanel grid = new JPanel();
		grid.setLayout (new GridLayout (4,4));

		add (grid, BorderLayout.CENTER);
		add (shuffle, BorderLayout.SOUTH);
		shuffle.addActionListener (new Shuffler() );


		int counter = 1;
		for (row = 0; row < buttons.length; row++ )
		{
			for (column = 0; column < buttons[row].length; column++)
			{
				buttons[row][column] = new JButton();
				buttons[row][column].addActionListener(new Clicker());
				buttons[row][column].setFont(font);
				if (counter == 16)
				{
					buttons[row][column].setText("");
					targetRow = row;
					targetColumn = column;
				}
				else
				{
					buttons[row][column].setText("" + counter);
					counter++;
				}
				grid.add (buttons[row][column]);
			}
		}
	}

	/** The Clicker class is the action listener for the 16 movable buttons.
	 * When a JButton is clicked, it passes the JButton into the swap method.
	 * After the swap method has completed, the winTest method is run to see 
	 * if the click resulted in a win for the user.
	 */
	class Clicker implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			JButton jb = (JButton) e.getSource();
			swap (jb);
		
			int wt = winTest();
			if (wt >= 15)
			{
				JOptionPane.showMessageDialog (null, 
						"Congratulations! You win!");
			}
		}

		/**
		 *  winTest() is a method to see if the game has been won or not by
		 *  testing to see if the text in the buttons[][] is in numerical order.
		 *  Base case, winTest has made it all the way to the 16th block
		 *  which means the prior 15 are all in place. Therefore, a win.
		 */
		public int winTest()
		{
			int counter = 0;
			for (int i = 0; i < 16; i++)
			{
				row = i / 4;
				column = i % 4;
				String counterString = "" + (i + 1);
				String buttonString = buttons[row][column].getText();
				if ( counterString.equals(buttonString))
				{
					counter++;
				}
			}
			return counter;
		}
	}

	/**
	 *  The Shuffler class is the ActionListener for Shuffle Button. It 
	 *  swaps blocks by randomly choosing 1 of the 16 blocks and passes that
	 *  Jbutton in through the swap method. It repeats this process 10,000 times
	 *  (which should be enough to get a solid mixup of pieces).
	 */
	class Shuffler implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			for (int i = 0; i < 10000; i++)
			{
				int randomX = (int)(Math.random()*4);
				int randomY = (int)(Math.random()*4);

				swap (buttons[randomX][randomY]);
			}
		}
	}

	/**
	 *  This method takes an input of a JButton, whether it is from the user
	 * click (Clicker) or from the computer's choice (Shuffler). It then checks
	 * to see if the target (the blank space) is next to the selected JButton.
	 * If the target is indeed next to the JButton, then the swap is acceptable
	 * and the pieces are mixed. If the swap is unacceptable, the method does 
	 * nothing.
	*/
	public void swap (JButton selected)
	{
		int rowHolder = targetRow;
		int columnHolder = targetColumn;
		String contents = "";

		//Identify row,column, and contents of clicked JButton
		for (row = 0; row < buttons.length; row++ )
		{
			for (column = 0; column < buttons[row].length; column++)
			{
				String testText = buttons[row][column].getText();
				if (testText.equals(selected.getText() ) )
				{
					targetRow = row;
					targetColumn = column;
					contents = testText;
				}
			}
		}

		/*
		 * If row matches and the columns are next to each other, or the
		 * column matches and the rows are next to each other, then swap
		 * contents.
		 */
		if(targetRow == rowHolder && 
						(Math.abs(targetColumn - columnHolder) == 1))
		{

			buttons[rowHolder][columnHolder].setText(contents);
			buttons[targetRow][targetColumn].setText("");
		}
		else if (targetColumn == columnHolder && 
						(Math.abs(targetRow - rowHolder) == 1))
		{
			buttons[rowHolder][columnHolder].setText(contents);
			buttons[targetRow][targetColumn].setText("");
		}
		else		//Reset for next click if not swappable
		{
			targetRow = rowHolder;
			targetColumn = columnHolder;
		}
	}
		
}
