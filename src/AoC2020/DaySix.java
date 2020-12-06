package AoC2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DaySix {
	public static ArrayList<ArrayList<String>> familyList = new ArrayList<>();

	public static void fileToArray() {
		try {
			File file = new File("src\\AoC2020\\inputs\\daySix.txt");
			Scanner scan = new Scanner(file);
			ArrayList<String> family = new ArrayList<>();
			while (scan.hasNextLine()) {
				String input = scan.nextLine();
				if (input.equals("") || input.equals("END")){
					familyList.add(family);
					family = new ArrayList<>();
				}else {
					family.add(input);
				}
			}
			scan.close();
		}catch (FileNotFoundException error) {
			System.err.println("Whoops! File not found.");
		}
	}

	public static void main(String[] args) {
		fileToArray();
		ArrayList<String> familyAnswers = new ArrayList<>();
		for (ArrayList<String> family : familyList) {
			String familyAnswer = "abcdefghijklmnopqrstuvwxyz";
			for (String person : family) {
				int i = 0;
				while(i < familyAnswer.length()) {
					if (person.indexOf(familyAnswer.charAt(i)) == -1){
						familyAnswer = familyAnswer.substring(0,i)+familyAnswer.substring(i+1);
					}else {
						i++;
					}
				}
			}
			familyAnswers.add(familyAnswer);
		}
		int sum = 0;
		for (String family : familyAnswers) {
			sum += family.length();
			System.out.println(family.length());
		}
		System.out.println(sum);
	}
}
