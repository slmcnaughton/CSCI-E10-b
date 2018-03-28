// CaesarCipher.java

/**
 *  This program can encrypt or decrypt a Caesar Cipher by shifting the 
 *  capital letters up or down in the alphabet. The program then outputs the 
 *  result to a new file that the user chooses.
 *
 *
 *  @author Seth McNaughton
 *  @version Created 3/24/2014
 */ 

import java.util.*;
import java.io.*;
import java.lang.*;

class CaesarCipher
{
	public static void main (String [] args)
	{
		System.out.println("Welcome to CaesarCipher!\n");
		while(true)
		{
			System.out.print("Enter 1 to encipher, or 2 to decipher (-1 to exit):  ");
			Scanner scan = new Scanner (System.in);
			int choice = scan.nextInt();
			if (choice == 1)
			{
				System.out.print("What shift should I use: ");
				Scanner shiftScan = new Scanner (System.in);
				int shift = shiftScan.nextInt();

				System.out.print("What is the input file name: ");

				Scanner fileScan = new Scanner (System.in);
				String fileName = fileScan.nextLine();

				System.out.println(caesarEncipher(fileName, shift));
			}
			else if (choice == 2)
			{
				System.out.print("What shift should I use: ");
				Scanner shiftScan = new Scanner (System.in);
				int shift = shiftScan.nextInt();

				System.out.print("What is the input file name: ");

				Scanner fileScan = new Scanner (System.in);
				String fileName = fileScan.nextLine();

				System.out.println(caesarDecipher(fileName, shift));
			}
			else if (choice == -1)
			{
				break;
			}
			else
			{
				System.out.println("That wasn't an option. Please try again.");
			}
		}
		

	}

	public static String caesarEncipher (String fileName, int shift)
	{
		try 
		{
			Scanner inFile = new Scanner (new File (fileName));

			System.out.print("What is the output file name? ");
			Scanner out = new Scanner (System.in);
			String output = out.nextLine();
			PrintWriter outFile = new PrintWriter (output);

			while (inFile.hasNextLine())
			{
				String s = inFile.nextLine();
				StringBuilder builder = new StringBuilder("");

				for (int i = 0; i < s.length(); i++)
				{
					char myChar = s.charAt(i);
					if (myChar >= 'A' && myChar <= 'Z')
					{
						myChar += shift;
						if (myChar > 'Z')
						{
							myChar = (char)(myChar - 26);
						}
						else if (myChar < 'A')
						{
							myChar = (char)(myChar + 26);
						}
					}
					builder.append(myChar);
				}
				outFile.println(builder);

			}
			inFile.close();
			outFile.close();

			return "DONE!";
			
		}
		catch (FileNotFoundException e)
		{
		   System.out.println ("The file cannot be found. \n");	   
		}
		catch (NoSuchElementException e)
		{
		   System.out.println ("Ooops! - The file is empty! \n");
		}
		return "Please try again.";
	}

	public static String caesarDecipher (String fileName, int shift)
	{
		return caesarEncipher(fileName,-shift);
	}
}


