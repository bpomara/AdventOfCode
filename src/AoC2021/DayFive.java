package AoC2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayFive {
	static ArrayList<int[]> startList = new ArrayList<>();
	static ArrayList<int[]> endList = new ArrayList<>();

	public static void fileToArray() {
		try {
			File file = new File("src\\AoC2021\\input\\dayFive.txt");
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				String[] input = scan.nextLine().split(" -> ");
				startList.add(new int[]{Integer.parseInt(input[0].substring(0,input[0].indexOf(","))), Integer.parseInt(input[0].substring(input[0].indexOf(",")+1))});
				endList.add(new int[]{Integer.parseInt(input[1].substring(0,input[1].indexOf(","))), Integer.parseInt(input[1].substring(input[1].indexOf(",")+1))});
			}
			scan.close();
		}catch (FileNotFoundException error) {
			System.err.println("Whoops! File not found.");
		}
	}
	public static void main(String[] args){
		fileToArray();
		short[][] floor = new short[1000][1000];
		for (int i = 0; i < startList.size(); i++){
			int minX = Math.min(startList.get(i)[0], endList.get(i)[0]);
			int minY = Math.min(startList.get(i)[1], endList.get(i)[1]);
			int maxX = Math.max(startList.get(i)[0], endList.get(i)[0]);
			int maxY = Math.max(startList.get(i)[1], endList.get(i)[1]);
			if (startList.get(i)[0] == endList.get(i)[0]){
				for(int j = minY; j <= maxY; j++){
					floor[startList.get(i)[0]][j]++;
				}
			}
			else if (startList.get(i)[1] == endList.get(i)[1]){
				for(int j = minX; j <= maxX; j++){
					floor[j][startList.get(i)[1]]++;
				}
			}
			else if ((endList.get(i)[1]-startList.get(i)[1])/(endList.get(i)[0]-startList.get(i)[0]) == 1){
				for(int j = 0; j <= maxX-minX; j++){
					floor[minX+j][minY+j]++;
				}
			}
			else {
				for(int j = 0; j <= maxX-minX; j++){
					floor[minX+j][maxY-j]++;
				}
			}
		}
		int dangerZoneCount = 0;
		for (short[] row : floor){
			for (short tile : row){
				if (tile > 1) dangerZoneCount++;
			}
		}
		System.out.println(dangerZoneCount);
	}
}
