// MailLayout.java

/**
 *  This program creates a look-alike to an email message. It outputs the file to
 *  a file called "Outbox.txt"
 *
 *  
 *
 *  @author Seth McNaughton
 *  @version Created 4/13/2014
 */ 

import java.awt.*;			//Colors, Fonts, etc.
import javax.swing.*;		//Swing components
import java.io.*;			//File I/O
import java.awt.event.*;	//ActionListener, etc.

class MailLayout
{
	public static void main (String [] args)
	{
		NewMessageFrame frame = new NewMessageFrame ("New Message", 600, 800);
		frame.setVisible(true);			//make frame show on screen
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close on exit
	}
}

class NewMessageFrame extends JFrame
{
		//Email message JLabels (To, cc, bcc, subject, from)
		private	JLabel label1 = new JLabel ("To:");
		private JLabel label2 = new JLabel ("Cc:");
		private JLabel label3 = new JLabel ("Bcc:");
		private JLabel label4 = new JLabel ("Subject:");
		private JLabel label5 = new JLabel ("From:");
		//Email Message fields (to, cc, bcc, subject, from (with a dropdown))
		private JTextField field1 = new JTextField("",60);
		private JTextField field2 = new JTextField("",60);
		private JTextField field3 = new JTextField("",60);
		private JTextField subject = new JTextField("",60);
		private JComboBox<String> dropdown = new JComboBox<String>();
		//Message Space Text Area
		JTextArea messageSpace = new JTextArea(4,20);
		//Send Button
		private JButton send = new JButton("Send");


		public NewMessageFrame (String name, int h, int w)
		{
			setTitle (name);
			setSize (w,h);
			Font font = new Font ("Helvetica", Font.BOLD, 14);

			//Email message JLabels (To, cc, bcc, subject, from)
			label1.setFont (font);
			label2.setFont (font);
			label3.setFont (font);
			label4.setFont (font);
			label5.setFont (font);

			//SubjectListener for Window Title
			SubjectListener s1 = new SubjectListener();
			subject.addFocusListener(s1);
			
			
			dropdown.addItem ("Micky Mouse <disney@yahoo.com>");
			dropdown.addItem ("Alter Ego <sillyemail2@gmail.com>");
			dropdown.addItem ("Free Stuff <spamspamspam@hotmail.com>");
			dropdown.setFont(font);

			send.setFont(font);
			ButtonListener b1 = new ButtonListener ();
			send.addActionListener(b1);

			//Create North Panels: 
			//JLabels on the Left, TextFields in the Center, inside of  mailInfo 
			JPanel left = new JPanel();
			left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
			JPanel right = new JPanel();
			right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
			JPanel mailInfo = new JPanel();

			//Add Left JLabels and Right JTextFields to North MailInfo Panel
			left.add (label1);
			left.add (label2);
			left.add (label3);
			left.add (label4);
			left.add (label5);
			label1.setAlignmentX(Component.RIGHT_ALIGNMENT);
			label2.setAlignmentX(Component.RIGHT_ALIGNMENT);
			label3.setAlignmentX(Component.RIGHT_ALIGNMENT);
			label4.setAlignmentX(Component.RIGHT_ALIGNMENT);
			label5.setAlignmentX(Component.RIGHT_ALIGNMENT);

			right.add (field1);
			right.add (field2);
			right.add (field3);
			right.add (subject);
			right.add (dropdown);

			mailInfo.add (left, BorderLayout.WEST);
			mailInfo.add (right, BorderLayout.CENTER);

			//Add MailInfo, MessageSpace, and Send Button to JFrame
			add (mailInfo, BorderLayout.NORTH);
			add (messageSpace, BorderLayout.CENTER);
			add (send, BorderLayout.SOUTH);
		
	}

	//Class SubjectListener - Changes the title of the email window
	class SubjectListener implements FocusListener
	{
		public void focusGained (FocusEvent fe) {}
		public void focusLost (FocusEvent fe)
		{
			setTitle (subject.getText());
		}
	}
		

	// Class ButtonListener for Send Button - 'Sends Message'
	class ButtonListener implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			writeTextFile(messageSpace, "outbox.txt");
			System.out.println("Sent.");
		}

		/**
		* writeTextFile() writes the contents of the email to a file 
		* @param fileName -- a String giving the file's name
		* @param messageText -- a JTextArea where the email text is stored
		*/

		private void writeTextFile (JTextArea messageText, String fileName)
		{
			try
			{
				PrintWriter outStream = new PrintWriter (new File (fileName));
				outStream.print (messageText.getText());
				outStream.close();
				JOptionPane.showMessageDialog (null, "Message 'sent.'");
			}
			catch (IOException e)
			{
				messageText.setText("IOERROR: " + e.getMessage() + "\n");
				e.printStackTrace();
			}
		}
	}
}



