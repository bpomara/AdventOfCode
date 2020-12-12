package AoC2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayTwelve {
	public static char[] commands;
	public static int[] values;

	public static void fileToArray() {
		try {
			ArrayList<String> inputList = new ArrayList<>();
			File file = new File("src\\AoC2020\\inputs\\dayTwelve.txt");
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				String input = scan.nextLine();
				inputList.add(input);
			}
			scan.close();
			commands = new char[inputList.size()];
			values = new int[inputList.size()];
			for (int i = 0; i < inputList.size(); i++) {
				commands[i] = inputList.get(i).charAt(0);
				values[i] = Integer.parseInt(inputList.get(i).substring(1));
			}
		}catch (FileNotFoundException error) {
			System.err.println("Whoops! File not found.");
		}
	}

	public static void main(String[] args){
		fileToArray();
		partTwo();
	}

	public static void partOne(){
		int[] position = new int[2];
		int orientation = 0;
		for(int i = 0; i < commands.length; i++){
			switch (commands[i]) {
				case 'N' -> position[1] += values[i];
				case 'S' -> position[1] -= values[i];
				case 'E' -> position[0] += values[i];
				case 'W' -> position[0] -= values[i];
				case 'L' -> orientation += values[i];
				case 'R' -> orientation -= values[i];
				case 'F' -> {
					position[0] += (int) Math.cos(Math.toRadians(orientation)) * values[i];
					position[1] += (int) Math.sin(Math.toRadians(orientation)) * values[i];
				}
			}
			orientation %= 360;
		}
		System.out.println();
		System.out.println(Math.abs(position[0])+Math.abs(position[1]));
	}

	public static void partTwo(){
		int[] position = new int[2];
		int temp;
		int[] waypoint = {10, 1};
		for(int i = 0; i < commands.length; i++){
			switch(commands[i]){
				case 'N':
					waypoint[1] += values[i];
					break;
				case 'S':
					waypoint[1] -= values[i];
					break;
				case 'E':
					waypoint[0] += values[i];
					break;
				case 'W':
					waypoint[0] -= values[i];
					break;
				case 'L':
					switch (values[i]) {
						case 90 -> {
							temp = waypoint[0];
							waypoint[0] = waypoint[1];
							waypoint[1] = temp;
							waypoint[0] *= -1;
						}
						case 180 -> {
							waypoint[0] *= -1;
							waypoint[1] *= -1;
						}
						case 270 -> {
							temp = waypoint[0];
							waypoint[0] = waypoint[1];
							waypoint[1] = temp;
							waypoint[1] *= -1;
						}
					}
					break;
				case 'R':
					switch (values[i]) {
						case 90 -> {
							temp = waypoint[0];
							waypoint[0] = waypoint[1];
							waypoint[1] = temp;
							waypoint[1] *= -1;
						}
						case 180 -> {
							waypoint[0] *= -1;
							waypoint[1] *= -1;
						}
						case 270 -> {
							temp = waypoint[0];
							waypoint[0] = waypoint[1];
							waypoint[1] = temp;
							waypoint[0] *= -1;
						}
					}
					break;
				case 'F':
					position[0] += waypoint[0]*values[i];
					position[1] += waypoint[1]*values[i];
			}
			System.out.println(position[0]+" "+position[1]);
			System.out.println(waypoint[0]+" "+waypoint[1]);
			System.out.println();
		}
		System.out.println(Math.abs(position[0])+Math.abs(position[1]));
	}
}
