package AoC2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DaySeven {
	public static ArrayList<String> bags = new ArrayList<>();
	public static ArrayList<ArrayList<String>> bagsContents = new ArrayList<>();
	public static ArrayList<ArrayList<Integer>> contentsAmounts = new ArrayList<>();

	public static void fileToArrayList() {
		try {
			File file = new File("src\\AoC2020\\inputs\\daySeven.txt");
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				String input = scan.nextLine();
				bags.add(input.substring(0,input.indexOf(" bags")));
				input = input.substring(input.indexOf("contain")+8);
				ArrayList<String> bagContents = new ArrayList<>();
				ArrayList<Integer> contentAmounts = new ArrayList<>();
				if (!input.contains("no other")){
					while (input.contains(",")) {
						contentAmounts.add(Integer.parseInt(input.substring(0,input.indexOf(" "))));
						input = input.substring(input.indexOf(" ")+1);
						bagContents.add(input.substring(0,input.indexOf(" bag")));
						input = input.substring(input.indexOf(",")+2);
					}
					contentAmounts.add(Integer.parseInt(input.substring(0,input.indexOf(" "))));
					input = input.substring(input.indexOf(" ")+1);
					bagContents.add(input.substring(0,input.indexOf(" bag")));
				}
				bagsContents.add(bagContents);
				contentsAmounts.add(contentAmounts);

			}
			scan.close();
		}catch (FileNotFoundException error) {
			System.err.println("Whoops! File not found.");
		}
	}

	public static void main(String[] args){
		fileToArrayList();
		System.out.println(findGoldContents());
	}

	public static int findOuterGold() {
		ArrayList<String> answers = new ArrayList<>();
		answers.add("shiny gold");
		int oldLength = 0;
		while(oldLength < answers.size()) {
			oldLength = answers.size();
			for (int i = 0; i < bags.size(); i++){
				for (int j = 0; j < answers.size(); j++){
					if (bagsContents.get(i).contains(answers.get(j)) && !answers.contains(bags.get(i))){
						answers.add(bags.get(i));
					}
				}
			}
		}
		answers.remove("shiny gold");
		return answers.size();
	}

	public static int findGoldContents() {
		int counter = 0;
		ArrayList<Integer> thisLevelNums;
		ArrayList<String> thisLevelColors;
		ArrayList<Integer> nextLevelNums  = new ArrayList<>();
		ArrayList<String> nextLevelColors  = new ArrayList<>();
		nextLevelNums.add(1);
		nextLevelColors.add("shiny gold");
		while (!nextLevelNums.isEmpty()){
			thisLevelNums = nextLevelNums;
			thisLevelColors = nextLevelColors;
			nextLevelNums = new ArrayList<>();
			nextLevelColors = new ArrayList<>();
			for (int i = 0; i < thisLevelColors.size();i++){
				nextLevelColors.addAll(bagsContents.get(bags.indexOf(thisLevelColors.get(i))));
				for (int innerNum : contentsAmounts.get(bags.indexOf(thisLevelColors.get(i)))){
					nextLevelNums.add(innerNum*thisLevelNums.get(i));
				}
			}
			for (int num : nextLevelNums){
				counter += num;
			}
		}
		return counter;
	}
}
