package AoC2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayFive {
	public static ArrayList<String> codesList;
	public static boolean[] seatmap = new boolean[1024];

	public static void fileToArray() {
		try {
			codesList = new ArrayList<>();
			File file = new File("src\\AoC2020\\inputs\\dayFive.txt");
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				String input = scan.nextLine();
				codesList.add(input);
			}
			scan.close();
		}catch (FileNotFoundException error) {
			System.err.println("Whoops! File not found.");
		}
	}
	/*
	Part One:
	public static void main(String[] args){
		fileToArray();
		int max = 0;
		for (String code : codesList){
			String binary = "";
			for (int i = 0; i < code.length(); i++){
				if (code.charAt(i) == 'B' || code.charAt(i) == 'R') binary+="1";
				else binary+="0";
			}
			int seatID = Integer.parseInt(binary, 2);
			if (seatID > max) max = seatID;
		}
		System.out.println(max);
	}
	Part Two:
	 */
	public static void main(String[] args){
		fileToArray();
		fillPlane();
		for(int i = 1; i < seatmap.length-1;i++){
			if(seatmap[i-1] && !seatmap[i] && seatmap[i+1]) System.out.println(i);
		}
	}

	public static void fillPlane(){
		for (String code : codesList){
			String binary = "";
			for (int i = 0; i < code.length(); i++){
				if (code.charAt(i) == 'B' || code.charAt(i) == 'R') binary+="1";
				else binary+="0";
			}
			int seatID = Integer.parseInt(binary, 2);
			seatmap[seatID] = true;
		}
	}
}
