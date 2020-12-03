package AoC2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayTwo {
	static ArrayList<Integer> mins = new ArrayList<>();
	static ArrayList<Integer> maxes = new ArrayList<>();
	static ArrayList<Character> keyChars = new ArrayList<>();
	static ArrayList<String> passwords = new ArrayList<>();

	public static void fileToArrayLists() {
		try {
			ArrayList<Integer> inputList = new ArrayList<>();
			File file = new File("src\\AoC2020\\inputs\\dayTwo.txt");
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				String input = scan.nextLine();
				mins.add(Integer.parseInt(input.substring(0,input.indexOf("-"))));
				maxes.add(Integer.parseInt(input.substring(input.indexOf("-")+1,input.indexOf(" "))));
				keyChars.add(input.charAt(input.indexOf(" ")+1));
				passwords.add(input.substring(input.indexOf(":")+2));
			}
			scan.close();
		}catch (FileNotFoundException error) {
			System.err.println("Whoops! File not found.");
		}
	}
/*
Part One:
	public static void main(String[] main){
		fileToArrayLists();
		int numCorrect = 0;
		for(int i = 0; i < passwords.size(); i++){
			int numCharInPass = 0;
			String password = passwords.get(i);
			char keyChar = keyChars.get(i);
			while(password.indexOf(keyChar) != -1){
				numCharInPass++;
				password = password.substring(password.indexOf(keyChar)+1);
			}
			if (numCharInPass >= mins.get(i) && numCharInPass <= maxes.get(i)) numCorrect++;
		}
		System.out.println(numCorrect);
	}

Part Two:
 */
	public static void main(String[] main){
		fileToArrayLists();
		int numCorrect = 0;
		for(int i = 0; i < passwords.size(); i++){
			String password = passwords.get(i);
			char keyChar = keyChars.get(i);
			if (password.charAt(mins.get(i)-1) == keyChar ^ password.charAt(maxes.get(i)-1) == keyChar) numCorrect++;
		}
		System.out.println(numCorrect);
	}
}
