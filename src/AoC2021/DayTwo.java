package AoC2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayTwo {
	static String[] commands;
	static int[] values;
	public static void fileToArrays() {
		try {
			ArrayList<String> commandList = new ArrayList<>();
			ArrayList<Integer> valueList = new ArrayList<>();
			File file = new File("src\\AoC2021\\input\\dayTwo.txt");
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				String input = scan.nextLine();
				commandList.add(input.substring(0,input.indexOf(" ")));
				valueList.add(Integer.parseInt(input.substring(input.indexOf(" ")+1)));
			}
			scan.close();
			commands = new String[commandList.size()];
			values = new int[valueList.size()];
			for (int i = 0; i < valueList.size(); i++) {
				commands[i] = commandList.get(i);
				values[i] = valueList.get(i);
			}
		}catch (FileNotFoundException error) {
			System.err.println("Whoops! File not found.");
		}
	}
	public static void main(String[] args){
		fileToArrays();
		int depth = 0;
		int horizontal = 0;
		int aim = 0;
		for(int i = 0; i < commands.length; i++){
			switch (commands[i]){
				case "up":
					aim -= values[i];
					break;
				case "down":
					aim += values[i];
					break;
				case "forward":
					horizontal += values[i];
					depth += values[i]*aim;
					break;
				default:
					System.out.println("Ruh-roh!");
			}
		}
		System.out.println(depth*horizontal);
	}
}
