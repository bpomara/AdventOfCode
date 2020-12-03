package AoC2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayOne {
	public static int[] fileToArray() {
		try {
			ArrayList<Integer> inputList = new ArrayList<>();
			File file = new File("src\\AoC2020\\inputs\\dayOne.txt");
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
		int[] expenses = fileToArray();
		for(int i = 0; i < expenses.length; i++){
			for (int j = i+1; j < expenses.length; j++){
				for (int k = j+1; k < expenses.length; k++) {
					if (expenses[i] + expenses[j] + expenses[k] == 2020) {
						System.out.println(expenses[i]);
						System.out.println(expenses[j]);
						System.out.println(expenses[k]);
						System.out.println(expenses[i] + expenses[j] + expenses[k]);
						System.out.println(expenses[i] * expenses[j] * expenses[k]);
						System.out.println();
					}
				}
			}
		}
	}
}
