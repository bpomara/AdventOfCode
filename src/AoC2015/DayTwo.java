package AoC2015;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayTwo {
	public static void main(String[] args) {
		int[] dimentions;
		int totalPaper = 0;
		int totalRibbon = 0;
		try {
			File file = new File("src\\AoC2015\\inputs\\dayTwo.txt");
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				String input = scan.nextLine();
				dimentions = new int[]{Integer.parseInt(input.substring(0, input.indexOf("x"))), Integer.parseInt(input.substring(input.indexOf("x") + 1, input.lastIndexOf("x"))), Integer.parseInt(input.substring(input.lastIndexOf("x") + 1))};
				int max = Math.max(dimentions[0],Math.max(dimentions[1],dimentions[2]));
				totalPaper += (2*dimentions[0]*dimentions[1]+2*dimentions[1]*dimentions[2]+2*dimentions[2]*dimentions[0])+(dimentions[0]*dimentions[1]*dimentions[2]/max);
				totalRibbon += (dimentions[0]*dimentions[1]*dimentions[2])+(2*dimentions[0]+2*dimentions[1]+2*dimentions[2]-2*max);
			}
			System.out.println(totalPaper);
			System.out.println(totalRibbon);
			scan.close();
		}catch (FileNotFoundException error) {
			System.err.println("Whoops! File not found.");
		}
	}
}
