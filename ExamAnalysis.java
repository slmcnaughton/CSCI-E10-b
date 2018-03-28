// ExamAnalysis.java

/**
 *  This program quickly scores and analyzes a multiple choice test. The program takes
 *  a user-typed answer key and asks for the file location of the multiple choice exams.
 *  It then scores the test and provides a question by question report of the results.
 *
 *  @author Seth McNaughton
 *  @version Created 3/24/2014
 */ 

import java.util.*;
import java.io.*;

class ExamAnalysis
{
	final static String [] HEADERS = { "   A*     B      C      D      E      Blank",
								"   A      B*     C      D      E      Blank",
								"   A      B      C*     D      E      Blank",
								"   A      B      C      D*     E      Blank",
								"   A      B      C      D      E*     Blank",};
	public static ArrayList<String> studentResponses = new ArrayList<String> ();


	public static void main (String [] args)
	{
		System.out.println("I hope you are ready to begin ...\n\n");
		System.out.print("Please type the correct answers to the exam questions,\n"
								+ " one right after the other:   ");
		Scanner scan = new Scanner (System.in);
		String answerKey = scan.nextLine().toUpperCase();

    	getData(answerKey);
		studentResults(answerKey);
		questionAnalysis(answerKey);
	}



	public static void getData (String key)
	{
		int student = 0;
		Scanner keyboard = new Scanner (System.in);
		int count = 0;
		while (true)
		{
			if (count > 2)
			{
				System.out.println("There seems to be a problem finding your file.\n"
							+ "Please locate the file and run the program again.\n" );
				break;
			}
			try
			{
				count++;
				System.out.print("What is the name of the file containing each student's \n"
								+ " responses to the " + key.length() + " questions?   ");
				String fileName = keyboard.nextLine();
				Scanner in = new Scanner (new File (fileName));

				System.out.println("\n");

				while (in.hasNext())
				{
					student++;
					String temp = in.nextLine();
					System.out.println("Student #" + student + "'s responses:  " + temp);

					int spaceAdder = temp.length();

					if (spaceAdder < key.length())
					{
						for (int i = 0; i < (key.length() - spaceAdder); i++)
						{
							temp = temp + " ";
						}
					}

					studentResponses.add(temp);
				}
				System.out.println ("We have reached \"end of file!\"\n");
				System.out.println("Thank you for the data on " + student + " students. "
									+ "Here's the analysis: \n\n");
				break;
			}
			catch (FileNotFoundException e)
		    {
		       System.out.println ("Please try again - The file name could not be found. \n");
		    }
		    catch (NoSuchElementException e)
		    {
		       System.out.println ("Ooops! - The file seems to be empty! Please try again. \n");
		    }
		}
	}


	public static void studentResults (String key)
	{
		String headStudent = "Student #";
		String headCorrect = "Correct";
		String headIncorrect = "Incorrect";
		String headBlank = "Blank";

		System.out.printf("%12s %11s %13s %9s %n", headStudent, headCorrect,
							 headIncorrect, headBlank);
		System.out.printf("%12s %11s %13s %9s %n", "~~~~~~~~~", "~~~~~~~", 
							"~~~~~~~~~", "~~~~~");

		int studentNumber = 0;
		for (String s : studentResponses)
		{
			int correct = 0;
			int incorrect = 0;
			int blank = 0;
			for (int i = 0; i < s.length(); i++)
			{
				if (s.charAt(i) == ' ')
				{
					blank++;
				}
				else if (s.charAt(i) == key.charAt(i))
				{
					correct++;
				}
				else
				{
					incorrect++;
				}
			}
			if (key.length()-s.length() > 0) blank = blank + key.length()-s.length();

			studentNumber++;
			System.out.printf("%8s %12s %12s %11s %n", studentNumber, correct, 
								incorrect, blank);
		}
		System.out.println("\n\n");
	}



	public static void questionAnalysis(String key)
	{
		System.out.println("Question Analysis (* marks the correct response)");
		System.out.println("~~~~~~~~~~~~~~~~~\n");
		for (int i = 0; i < key.length(); i++)
		{
			System.out.println("Question #" + (i+1) + ":\n");
			int header = key.charAt(i) - 'A';
			System.out.println(HEADERS[header] + "\n");

			int A = 0;
			int B = 0;
			int C = 0;
			int D = 0;
			int E = 0;
			int blank = 0;
			for (String s : studentResponses)
			{
				switch (s.charAt(i))
				{
				case 'A': A++; break;
				case 'B': B++; break;
				case 'C': C++; break;
				case 'D': D++; break;
				case 'E': E++; break;
				case ' ': blank++; break;
				default: blank++; break;
				}
			}
			System.out.printf("%4s %6s %6s %6s %6s %7s %n%n", A, B, C, D, E, blank);
			System.out.printf(" %.1f%%   %.1f%%   %.1f%%   %.1f%%   %.1f%%   "
							+ "%.1f%%   %n%n%n%n", 
								100.*A/studentResponses.size(),
								100.*B/studentResponses.size(),
								100.*C/studentResponses.size(),
								100.*D/studentResponses.size(),
								100.*E/studentResponses.size(),
								100.*blank/studentResponses.size());
		}
	}

	
}
