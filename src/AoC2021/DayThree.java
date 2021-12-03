package AoC2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayThree {

	public static ArrayList<String> fileToArray() {
		try {
			ArrayList<String> inputList = new ArrayList<>();
			File file = new File("src\\AoC2021\\input\\dayThree.txt");
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				String input = scan.nextLine();
				inputList.add(input);
			}
			scan.close();
			return inputList;
		}catch (FileNotFoundException error) {
			System.err.println("Whoops! File not found.");
			return new ArrayList<>();
		}
	}

	public static int findPower(){
		ArrayList<String> diagnostic = fileToArray();
		StringBuilder gamma = new StringBuilder();
		StringBuilder epsilon = new StringBuilder();
		for (int i = 0; i < diagnostic.get(0).length(); i++){
			int ones = 0;
			int zeros = 0;
			for (String line : diagnostic){
				if (line.charAt(i) == '1')ones++;
				else if (line.charAt(i) == '0')zeros++;
				else System.out.println("Error!");
			}
			if (ones > zeros){
				gamma.append("1");
				epsilon.append("0");
			}else if (zeros > ones){
				gamma.append("0");
				epsilon.append("1");
			}else System.out.println("Error!");
		}
		return Integer.parseInt(gamma.toString(),2)*Integer.parseInt(epsilon.toString(),2);
	}

	public static int getLife(){
		ArrayList<String> oxyList = fileToArray();
		ArrayList<String> co2List = fileToArray();
		for (int i = 0; i < oxyList.get(0).length(); i++){
			int[] oxyCount = new int[2];
			int[] co2Count = new int[2];
			for (String line : oxyList){
				if (line.charAt(i) == '1')oxyCount[1]++;
				else if (line.charAt(i) == '0')oxyCount[0]++;
				else System.out.println("Error!");
			}
			for (String line : co2List){
				if (line.charAt(i) == '1')co2Count[1]++;
				else if (line.charAt(i) == '0')co2Count[0]++;
				else System.out.println("Error!");
			}
			char mostOxy = (oxyCount[0] > oxyCount[1]) ? '0' : '1';
			char leastCo2 = (co2Count[1] < co2Count[0]) ? '1' : '0';
			for (int j = 0; j < oxyList.size() && oxyList.size() > 1; j++){
				if (mostOxy != oxyList.get(j).charAt(i)){
					oxyList.remove(j);
					j--;
				}
			}
			for (int j = 0; j < co2List.size() && co2List.size() > 1; j++){
				if (leastCo2 != co2List.get(j).charAt(i)){
					co2List.remove(j);
					j--;
				}
			}
		}
		return Integer.parseInt(oxyList.get(0),2) * Integer.parseInt(co2List.get(0),2);
	}

	public static void main(String[] args){
		System.out.println(findPower());
		System.out.println(getLife());
	}
}
