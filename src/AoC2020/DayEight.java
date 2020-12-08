package AoC2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayEight {
	public static ArrayList<String> originalCommands = new ArrayList<>();
	public static ArrayList<Integer>originalValues = new ArrayList<>();
	public static ArrayList<String> commands = new ArrayList<>();
	public static ArrayList<Integer> values = new ArrayList<>();

	public static void fileToArrayList() {
		try {
			File file = new File("src\\AoC2020\\inputs\\dayEight.txt");
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				String input = scan.nextLine();
				originalCommands.add(input.substring(0,input.indexOf(" ")));
				originalValues.add(Integer.valueOf(input.substring(input.indexOf(" ")+1)));
			}
			scan.close();
		}catch (FileNotFoundException error) {
			System.err.println("Whoops! File not found.");
		}
	}

	public static void main(String[] args){
		fileToArrayList();
		for(int i = 0; i < originalCommands.size(); i++){
			commands = new ArrayList<>(originalCommands);
			values = new ArrayList<>(originalValues);
			if (commands.get(i).equals("nop")){
				commands.set(i,"jmp");
			}else if (commands.get(i).equals("jmp")){
				commands.set(i,"nop");
			}
			if (finishes()){
				System.out.println("Found it!");
				return;
			}
		}
	}

	public static boolean finishes() {
		boolean[] visited = new boolean[commands.size()];
		int accumulator = 0;
		int position = 0;
		while (position != commands.size() && !visited[position]) {
			visited[position] = true;
			switch (commands.get(position)) {
				case "acc" -> {
					accumulator += values.get(position);
					position++;
				}
				case "jmp" -> position += values.get(position);
				case "nop" -> position++;
			}
		}
		System.out.println(accumulator);
		return position == commands.size();
	}
}
