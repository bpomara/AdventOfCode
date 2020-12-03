package AoC2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayThree {
	static boolean[][] map;

	public static void fileToArray() {
		try {
			ArrayList<boolean[]> mapList = new ArrayList<>();
			File file = new File("src\\AoC2020\\inputs\\dayThree.txt");
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				String input = scan.nextLine();
				boolean[] line = new boolean[input.length()];
				for (int i = 0; i < input.length(); i++) {
					line[i] = input.charAt(i) == '#';
				}
				mapList.add(line);
			}
			scan.close();
			map = new boolean[mapList.size()][mapList.get(0).length];
			for (int i = 0; i < mapList.size(); i++) {
				map[i] = mapList.get(i);
			}
		}catch (FileNotFoundException error) {
			System.err.println("Whoops! File not found.");
		}
	}

	public static void main(String[] args) {
		fileToArray();
		long output = 1;
		output *= findHitTrees(1,1);
		output *= findHitTrees(3,1);
		output *= findHitTrees(5,1);
		output *= findHitTrees(7,1);
		output *= findHitTrees(1,2);
		System.out.println(output);

	}

	public static int findHitTrees(int right, int down) {
		int[] position = {0,0}; // y,x
		int hitTrees = 0;
		while (position[0] < map.length-down){
			position[0]+=down;
			position[1]+=right;
			if(map[position[0]][position[1]%(map[0].length)])
				hitTrees++;
		}
		return hitTrees;
	}
}
