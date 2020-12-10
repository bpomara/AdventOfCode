package AoC2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class DayTen {
	public static ArrayList<Integer> adapters = new ArrayList<>();
	public static int[] combos = {1,1,2,4,7,13,24};

	public static void fileToArray() {
		try {
			File file = new File("src\\AoC2020\\inputs\\dayTen.txt");
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				String input = scan.nextLine();
				adapters.add(Integer.parseInt(input));
			}
			scan.close();
			Collections.sort(adapters);
			adapters.add(adapters.get(adapters.size()-1)+3);
		}catch (FileNotFoundException error) {
			System.err.println("Whoops! File not found.");
		}
	}
	/*
	Part One:
	public static void main(String[] args){
		fileToArray();
		int[] differencesTab = new int[3];
		int previousNum = 0;
		for(int adapter : adapters){
			int difference = adapter - previousNum;
			previousNum = adapter;
			differencesTab[difference-1]++;
		}
		System.out.println(differencesTab[0]);
		System.out.println(differencesTab[1]);
		System.out.println(differencesTab[2]);
	}
	Part Two:
	 */

	public static void main(String[] args){
		fileToArray();
		int previousNum = 0;
		int oneCounter = 0;
		long answer = 1;
		for(int adapter : adapters){
			int difference = adapter - previousNum;
			if (difference == 1){
				oneCounter++;
			}else if (difference == 3){
				answer *= combos[oneCounter];
				oneCounter = 0;
			}
			previousNum = adapter;
		}
		System.out.println(answer);
	}
}
