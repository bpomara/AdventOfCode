package AoC2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayOne {
	public static int[] fileToArray() {
		try {
			ArrayList<Integer> inputList = new ArrayList<>();
			File file = new File("src\\AoC2021\\input\\dayOne.txt");
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				String input = scan.nextLine();
				inputList.add(Integer.parseInt(input));
			}
			scan.close();
			int[] output = new int[inputList.size()];
			for (int i = 0; i < inputList.size(); i++) {
				output[i] = inputList.get(i);
			}
			return output;
		}catch (FileNotFoundException error) {
			System.err.println("Whoops! File not found.");
			return new int[1];
		}
	}
	public static void main(String[] args){
		int[] sonar = fileToArray();
		int totalIncreases = 0;
		for(int i = 3; i < sonar.length; i++){
			if (sonar[i]+sonar[i-1]+sonar[i-2]>sonar[i-1]+sonar[i-2]+sonar[i-3]) {
				totalIncreases++;
			}
		}
		System.out.println(totalIncreases);
	}
}
