package AoC2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DaySeventeen {
	public static int mapSize = 50;
	public static boolean[][][][] pocketMap = new boolean[mapSize][mapSize][mapSize][mapSize];


	public static void fileToArray() {
		try {
			File file = new File("src\\AoC2020\\inputs\\daySeventeen.txt");
			Scanner scan = new Scanner(file);
			for (int y = 0; scan.hasNextLine(); y++) {
				String input = scan.nextLine();
				for (int x = 0; x < input.length(); x++){
					pocketMap[mapSize/2+x][mapSize/2+y][mapSize/2][mapSize/2] = input.charAt(x) == '#';
				}
			}
			scan.close();
		}catch (FileNotFoundException error) {
			System.err.println("Whoops! File not found.");
		}
	}

	public static void main(String[] args){
		fileToArray();
		for (int i = 0; i < 6; i++) {
			boolean[][][][] nextMap = new boolean[mapSize][mapSize][mapSize][mapSize];
			for (int w = 1; w < mapSize-1; w++) {
				for (int z = 1; z < mapSize-1; z++) {
					for (int y = 1; y < mapSize-1; y++) {
						for (int x = 1; x < mapSize-1; x++) {
							nextMap[x][y][z][w] = nextCube(x, y, z, w);
						}
					}
				}
			}
			pocketMap = nextMap;
		}
		int numberActive = 0;
		for (int w = 0; w < mapSize; w++) {
			for (int z = 0; z < mapSize; z++) {
				for (int y = 0; y < mapSize; y++) {
					for (int x = 0; x < mapSize; x++) {
						if (pocketMap[x][y][z][w]) numberActive++;
					}
				}
			}
		}
		System.out.println(numberActive);
	}

	public static boolean nextCube(int x, int y, int z, int w) {
		int activeNeighbors = 0;
		for (int wDiff = -1; wDiff < 2; wDiff++){
			for (int zDiff = -1; zDiff < 2; zDiff++){
				for (int yDiff = -1; yDiff < 2; yDiff++){
					for (int xDiff = -1; xDiff < 2; xDiff++){
						if (pocketMap[x+xDiff][y+yDiff][z+zDiff][w+wDiff]) activeNeighbors++;
					}
				}
			}
		}
		if (pocketMap[x][y][z][w]){
			return activeNeighbors == 3 || activeNeighbors == 4;
		}else{
			return activeNeighbors == 3;
		}
	}
}
