package AoC2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DaySixteen {
	public static ArrayList<String> ruleNames = new ArrayList<>();
	public static ArrayList<int[]> rules = new ArrayList<>();
	public static ArrayList<ArrayList<Integer>> otherTickets = new ArrayList<>();
	public static ArrayList<Integer> myTicket = new ArrayList<>();

	public static void fileToArray() {
		try {
			File file = new File("src\\AoC2020\\inputs\\daySixteen.txt");
			Scanner scan = new Scanner(file);
			while (true) {
				String input = scan.nextLine();
				if (input.equals(""))break;
				ruleNames.add(input.substring(0,input.indexOf(":")));
				int[] rule = {Integer.parseInt(input.substring(input.indexOf(":")+2,input.indexOf("-"))),Integer.parseInt(input.substring(input.indexOf("-")+1,input.indexOf(" or"))),Integer.parseInt(input.substring(input.indexOf("or ")+3,input.lastIndexOf("-"))),Integer.parseInt(input.substring(input.lastIndexOf("-")+1))};
				rules.add(rule);
			}
			scan.nextLine();
			String input = scan.nextLine();
			while (input.contains(",")){
				myTicket.add(Integer.parseInt(input.substring(0,input.indexOf(","))));
				input = input.substring(input.indexOf(",")+1);
			}
			myTicket.add(Integer.parseInt(input));
			scan.nextLine();
			scan.nextLine();
			while (scan.hasNextLine()) {
				input = scan.nextLine();
				ArrayList<Integer> ticket = new ArrayList<>();
				while (input.contains(",")){
					ticket.add(Integer.parseInt(input.substring(0,input.indexOf(","))));
					input = input.substring(input.indexOf(",")+1);
				}
				ticket.add(Integer.parseInt(input));
				otherTickets.add(ticket);
			}
			scan.close();
		}catch (FileNotFoundException error) {
			System.err.println("Whoops! File not found.");
		}
	}

	public static void discardGarbage() {
		ArrayList<ArrayList<Integer>> garbageCan = new ArrayList<>();
		for (ArrayList<Integer> ticket : otherTickets){
			boolean isGarbage = false;
			for (int field : ticket){
				boolean isGarbageField = true;
				for (int[] rule : rules) {
					if ((field >= rule[0] && field <= rule[1]) || (field >= rule[2] && field <= rule[3])) {
						isGarbageField = false;
						break;
					}
				}
				if (isGarbageField) {
					isGarbage = true;
					break;
				}
			}
			if (isGarbage) {
				garbageCan.add(ticket);
			}
		}
		for (ArrayList<Integer> ticket : garbageCan){
			otherTickets.remove(ticket);
		}
	}

	public static void main(String[] args){
		fileToArray();
		discardGarbage();
		String[] columnMeanings = new String[otherTickets.get(0).size()];
		while(!rules.isEmpty()) {
			for (int column = 0; column < columnMeanings.length; column++) {
				int[] usedRule = new int[1];
				int numWorked = 0;
				ruleLoop:
				for (int[] rule : rules) {
					for (ArrayList<Integer> ticket : otherTickets) {
						int field = ticket.get(column);
						if (!((field >= rule[0] && field <= rule[1]) || (field >= rule[2] && field <= rule[3]))) {
							continue ruleLoop;
						}
					}
					numWorked++;
					usedRule = rule;
				}
				if (numWorked == 1) {
					columnMeanings[column] = ruleNames.get(rules.indexOf(usedRule));
					ruleNames.remove(rules.indexOf(usedRule));
					rules.remove(usedRule);
				}
			}
		}
		System.out.println(Arrays.toString(columnMeanings));
		long answer = 1;
		for(int i = 0; i < columnMeanings.length; i++){
			if (columnMeanings[i].contains("departure")){
				answer*=myTicket.get(i);
			}
		}
		System.out.println(answer);
	}
}
