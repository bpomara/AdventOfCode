package AoC2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DayNine {
	public static ArrayList<Long> xmasCode = new ArrayList<>();

	public static void fileToArray() {
		try {
			File file = new File("src\\AoC2020\\inputs\\dayNine.txt");
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				String input = scan.nextLine();
				xmasCode.add(Long.parseLong(input));
			}
			scan.close();
		}catch (FileNotFoundException error) {
			System.err.println("Whoops! File not found.");
		}
	}

	public static void main(String[] args){
		fileToArray();
		findContiguousSet(23278925);
	}

	public static void findFirstFlaw(int preambleLength) {
		outerLoop:
		for (int i = 0; i < xmasCode.size()-preambleLength; i++) {
			long target = xmasCode.get(preambleLength+i);
			List<Long> preamble = xmasCode.subList(i,i+preambleLength);
			for (long num : preamble){
				if (preamble.contains(target-num)) continue outerLoop;
			}
			System.out.println(target);
		}
	}

	public static void findContiguousSet(long target){
		for(int i = 0; i < xmasCode.size()-1; i++){
			long sum = 0;
			for(int j = i; j < xmasCode.size(); j++){
				sum += xmasCode.get(j);
				if(sum == target && j > i){
					long max = Long.MIN_VALUE;
					long min = Long.MAX_VALUE;
					for (int k = i; k <= j; k++){
						if (max < xmasCode.get(k)) max = xmasCode.get(k);
						if (min > xmasCode.get(k)) min = xmasCode.get(k);
					}
					System.out.println(max+min);
					return;
				}else if(sum > target){
					break;
				}
			}
		}
	}
}
