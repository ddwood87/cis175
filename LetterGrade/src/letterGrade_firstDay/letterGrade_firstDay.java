package letterGrade_firstDay;
import java.util.Scanner;

/**
 * @author dwood - ddwood2
 * CIS175 - Fall 2022
 * Aug 27, 2022
 */

public class letterGrade_firstDay {

	/**
	 * @param args
	 * Get user input of grade values, calculate letter grade,
	 * print list of letter grades.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] gradesInput;
		String[] letterGrades;		
		String outputFormat;
		
		// Get user input, output array list of grade values
		gradesInput = getUserInput();
		
		// input grade value array, output grade letter array
		letterGrades = findLetterGrades(gradesInput);
		// print both arrays in format.
		outputFormat = formatOutput(gradesInput, letterGrades);
		System.out.println(outputFormat);
	}
	
	/**
	 * @return int array of grade values input by user.
	 */
	private static int[] getUserInput() {
		Scanner scan = new Scanner(System.in);
		String inputString;
		int count = 0;
		int[] grades;
		String initialMessage = "How many scores would you like to enter? "; 
		String errorMessage = "Please enter a number of scores: ";
		String nextGradeMessage = "Enter a score: ";
		String nextGradeError = "Scores must be numbers 0 - 100.";
		System.out.print(initialMessage);
		boolean loopExit = false;
		do {
			inputString = scan.nextLine();
			if(inputString.isBlank()) {
				System.out.print(errorMessage);
				continue;
			}
			try{
				count = Integer.parseInt(inputString);
				loopExit = true;
			}catch(NumberFormatException e) {
				System.out.print(errorMessage);
			}			
		}while(loopExit == false);
		
		grades = new int[count];
		for(int i = 0; i < count; i++) {
			int g = -1;
			System.out.print(nextGradeMessage);			
			loopExit = false;
			do {
				inputString = scan.nextLine();
				if(inputString.isBlank()) {
					System.out.println(nextGradeError);
					System.out.print(nextGradeMessage);
					continue;
				}
				try{
					g = Integer.parseInt(inputString);
				}catch(NumberFormatException e) {
					System.out.println(nextGradeError);
					System.out.print(nextGradeMessage);
					continue;
				}
				if(g < 0 || g > 100){
					System.out.println(nextGradeError);
					System.out.print(nextGradeMessage);
				}else { loopExit = true; }
			}while(loopExit == false);
			grades[i] = g;
		}
		scan.close();
		return grades;
	}
	
	/**
	 * @param int array gradesInput
	 * @return String array containing letter grades
	 * 			with indices keyed to input array.
	 */
	private static String[] findLetterGrades(int[] gradesInput) {
		String[] letterOutput = new String[gradesInput.length];
		for(int i = 0; i < gradesInput.length; i++) {
			int g = gradesInput[i];
			String letter = "F";
			if(g >= 60) {
				letter = "D";
				if(g >= 70) {
					letter = "C";
					if(g >= 80) {
						letter = "B";
						if(g >= 90) {
							letter = "A";
						}
					}
				}
			}
			letterOutput[i] = letter;
		}
		return letterOutput;
	}

	/**
	 * @param int array grades
	 * @param String array letters
	 * @return String formatted for console output.
	 */
	private static String formatOutput(int[] grades, String[] letters) {
		int count = grades.length;
		String header1 = "Score";
		String header2 = "Grade";
		int dashLength = 8;
		String head = "\n" + header1 + " " + addDashes(dashLength) + " " + header2;
		String output = head + "\n";
		for(int i = 0; i < count; i++) {
			String g = Integer.toString(grades[i]);
			String l = letters[i];
			String line = centerOnHeader(header1, g) + " " + addDashes(dashLength) + " " + centerOnHeader(header2, l) + "\n";
			output += line;
		}
		return output;
	}
		
	/**
	 * @param int dashes
	 * @return String of certain number of dashes
	 */
	private static String addDashes(int dashes) {
		String out = "";
		for(int i = 0; i < dashes; i++) {
			out += "-";
		}
		return out;
	}
	
	/**
	 * @param String header
	 * @param String str
	 * @return String of the same number of characters as header,
	 * 			with str centered between spaces.
	 */
	private static String centerOnHeader(String header, String str) {
		String out = "";
		int lengthDiff = header.length() - str.length();
		for (int i = 0; i < lengthDiff/2; i++) {
			out += " ";
		}
		if(lengthDiff%2 != 0) {
			out += " ";
		}
		out += str;
		for (int i = 0; i < lengthDiff/2; i++) {
			out += " ";
		}
		return out;
	}
}
