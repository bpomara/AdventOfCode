package AoC2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayEleven {
	public static boolean[][] existence; // [y][x]
	public static boolean[][] occupied; // [y][x]

	public static void fileToArray() {
		try {
			ArrayList<String> inputList = new ArrayList<>();
			File file = new File("src\\AoC2020\\inputs\\dayEleven.txt");
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				String input = scan.nextLine();
				inputList.add(input);
			}
			scan.close();
			existence = new boolean[inputList.size()][inputList.get(0).length()];
			occupied = new boolean[inputList.size()][inputList.get(0).length()];
			for (int i = 0; i < inputList.size(); i++) {
				for (int j = 0; j < inputList.get(i).length(); j++){
					existence[i][j] = inputList.get(i).charAt(j) == 'L';
				}
			}
		}catch (FileNotFoundException error) {
			System.err.println("Whoops! File not found.");
		}
	}

	public static void main(String[] args){
		fileToArray();
		while(step());
		int count = 0;
		for (boolean[] line : occupied) {
			for (boolean seat : line) {
				if (seat) count++;
			}
		}
		System.out.println(count);
	}

	public static boolean step() {
		boolean change = false;
		boolean [][] newOccupied = new boolean[occupied.length][];
		for(int i = 0; i < occupied.length; i++)
			newOccupied[i] = occupied[i].clone();
		for (int y = 0; y < occupied.length; y++){
			for (int x = 0; x < occupied[0].length; x++){
				if (!occupied[y][x] && numAdjacent(y,x) == 0 && existence[y][x]){
					newOccupied[y][x] = true;
					change = true;
				}else if (occupied[y][x] && numAdjacent(y,x) >= 5 && existence[y][x]){
					newOccupied[y][x] = false;
					change = true;
				}
			}
		}
		occupied = newOccupied;
		return change;
	}

	public static int numAdjacentPartOne(int y, int x) {
		int adjacent = 0;
		if (y > 0 && x > 0 && occupied[y-1][x-1]) adjacent++;
		if (y > 0 && occupied[y-1][x]) adjacent++;
		if (y > 0 && x < occupied[0].length-1 && occupied[y-1][x+1]) adjacent++;
		if (x > 0 && occupied[y][x-1]) adjacent++;
		if (x < occupied[0].length-1 && occupied[y][x+1]) adjacent++;
		if (y < occupied.length-1 && x > 0 && occupied[y+1][x-1]) adjacent++;
		if (y < occupied.length-1 && occupied[y+1][x]) adjacent++;
		if (y < occupied.length-1 && x < occupied[0].length-1 && occupied[y+1][x+1]) adjacent++;
		return adjacent;
	}

	public static int numAdjacent(int y, int x) {
		int adjacent = 0;

		int distance = 1;
		while(y-distance >= 0 && x-distance >= 0 &&!existence[y-distance][x-distance])distance++;
		if (y-distance >= 0 && x-distance >= 0 && occupied[y-distance][x-distance]) adjacent++;

		distance = 1;
		while(y-distance >= 0  &&!existence[y-distance][x])distance++;
		if (y-distance >= 0  && occupied[y-distance][x]) adjacent++;

		distance = 1;
		while(y-distance >= 0 && x+distance < existence[0].length &&!existence[y-distance][x+distance])distance++;
		if (y-distance >= 0 && x+distance < existence[0].length && occupied[y-distance][x+distance]) adjacent++;

		distance = 1;
		while(x-distance >= 0 &&!existence[y][x-distance])distance++;
		if (x-distance >= 0 && occupied[y][x-distance]) adjacent++;

		distance = 1;
		while(x+distance < existence[0].length &&!existence[y][x+distance])distance++;
		if (x+distance < existence[0].length && occupied[y][x+distance]) adjacent++;

		distance = 1;
		while(y+distance < existence.length && x-distance >= 0 &&!existence[y+distance][x-distance])distance++;
		if (y+distance < existence.length && x-distance >= 0 && occupied[y+distance][x-distance]) adjacent++;

		distance = 1;
		while(y+distance < existence.length  &&!existence[y+distance][x])distance++;
		if (y+distance < existence.length && occupied[y+distance][x]) adjacent++;

		distance = 1;
		while(y+distance < existence.length && x+distance < existence[0].length &&!existence[y+distance][x+distance])distance++;
		if (y+distance < existence.length && x+distance < existence[0].length && occupied[y+distance][x+distance]) adjacent++;

		return adjacent;
	}
}
