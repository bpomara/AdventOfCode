package AoC2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class DayNineteen {
	public static ArrayList<String> receivedMessages = new ArrayList<>();
	public static String[] inputRules;
	public static ArrayList<String>[] rulePossibilities;

	public static void fileToArray() {
		try {
			ArrayList<String> inputList = new ArrayList<>();
			File file = new File("src\\AoC2020\\inputs\\dayNineteen.txt");
			Scanner scan = new Scanner(file);
			while (true) {
				String input = scan.nextLine();
				if (input.isEmpty()) break;
				inputList.add(input);
			}
			//inputRules = new String[inputList.size()];
			//rulePossibilities = new ArrayList[inputList.size()];
			inputRules = new String[50];
			rulePossibilities = new ArrayList[50];
			for (String statement : inputList) {
				inputRules[Integer.parseInt(statement.substring(0,statement.indexOf(":")))] = statement.substring(statement.indexOf(":")+2);
			}
			while (scan.hasNextLine()){
				String input = scan.nextLine();
				receivedMessages.add(input);
			}
			scan.close();
		}catch (FileNotFoundException error) {
			System.err.println("Whoops! File not found.");
		}
	}

	public static void main(String[] args){
		fileToArray();
		createPossibilities(0);
		System.out.println("Step One Complete!");
		int numCorrect = 0;
		for (String message : receivedMessages){
			for (String rule : rulePossibilities[0]){
				if (matches(rule, message)) {
					numCorrect++;
					//System.out.println(message);
					break;
				}
				//System.out.println(message);
			}
		}
		System.out.println(numCorrect);
	}

	public static boolean matches(String rule, String message) {
		if (rule.contains("8") || rule.contains("1")) {
			String[] splitRule = rule.split("8|1");

			return false;
		} else return rule.equals(message);
	}

	public static boolean matches1(String rule, String message) {
		try {
			while (rule.contains("8") || rule.contains("1")) {
				if (message.equals("babbbbaabbbbbabbbbbbaabaaabaaa") && rule.startsWith("babbbbaabb") && rule.endsWith("abaaa")){
					System.out.println("Leep");
				}
				if (message.startsWith(rule.substring(0, Math.min(rule.contains("8") ? rule.indexOf("8") : Integer.MAX_VALUE, rule.contains("1") ? rule.indexOf("1") : Integer.MAX_VALUE)))) {
					message = message.substring(Math.min(rule.contains("8") ? rule.indexOf("8") : Integer.MAX_VALUE, rule.contains("1") ? rule.indexOf("1") : Integer.MAX_VALUE));
					rule = rule.substring(Math.min(rule.contains("8") ? rule.indexOf("8") : Integer.MAX_VALUE, rule.contains("1") ? rule.indexOf("1") : Integer.MAX_VALUE));
				} else {
					return false;
				}
				int counter = 0;
				while (rulePossibilities[42].contains(message.substring(0, rulePossibilities[42].get(0).length()))) {
					message = message.substring(rulePossibilities[42].get(0).length());
					counter++;
				}
				if (rule.charAt(0) == '1') {
					for (int i = 0; i < counter; i++) {
						if (rulePossibilities[31].contains(message.substring(0, rulePossibilities[31].get(0).length())) && message.length() > rule.length()) {
							message = message.substring(rulePossibilities[31].get(0).length());
						} else {
							return false;
						}
					}
				}
				rule = rule.substring(1);
			}
			return rule.equals(message);
		}catch (StringIndexOutOfBoundsException e) {
			System.err.println(e);
			return false;
		}
	}

	public static void createPossibilities(int index) {
		ArrayList<String> possibilities = new ArrayList<>();
		String rule = inputRules[index];
		if (rule.contains("\"")){
			possibilities.add(rule.substring(rule.indexOf("\"")+1,rule.lastIndexOf("\"")));
		}else {
			String[] subruleArray = rule.split(" \\| ");
			for (String subrule : subruleArray){
				ArrayList<String> subrulePossibilities = new ArrayList<>();
				subrulePossibilities.add("");
				String[] numArray = subrule.split(" ");
				for (String num : numArray){
					ArrayList<String> newPossibilities = new ArrayList<>();
					if (Integer.parseInt(num) == index){
						rulePossibilities[Integer.parseInt(num)] = new ArrayList<>(Collections.singletonList(num.substring(0,1)));
					}else if (rulePossibilities[Integer.parseInt(num)] == null){
						createPossibilities(Integer.parseInt(num));
					}
					for (String oldPossibility : subrulePossibilities){
						for (String otherPossibility : rulePossibilities[Integer.parseInt(num)]){
							newPossibilities.add(oldPossibility+otherPossibility);
						}
					}
					subrulePossibilities = newPossibilities;
				}
				possibilities.addAll(subrulePossibilities);
			}
		}
		rulePossibilities[index] = possibilities;
	}

	public static void main1(String[] args){
		fileToArray();
		createPossibilities(0);
		int numCorrect = 0;
		for (String message : receivedMessages){
			if (rulePossibilities[0].contains(message))numCorrect++;
		}
		System.out.println(numCorrect);
	}

	public static void createPossibilities1(int index) {
		ArrayList<String> possibilities = new ArrayList<>();
		String rule = inputRules[index];
		if (rule.contains("\"")){
			possibilities.add(rule.substring(rule.indexOf("\"")+1,rule.lastIndexOf("\"")));
		}else {
			String[] subruleArray = rule.split(" \\| ");
			for (String subrule : subruleArray){
				ArrayList<String> subrulePossibilities = new ArrayList<>();
				subrulePossibilities.add("");
				String[] numArray = subrule.split(" ");
				for (String num : numArray){
					ArrayList<String> newPossibilities = new ArrayList<>();
					if (rulePossibilities[Integer.parseInt(num)] == null){
						createPossibilities(Integer.parseInt(num));
					}
					for (String oldPossibility : subrulePossibilities){
						for (String otherPossibility : rulePossibilities[Integer.parseInt(num)]){
							newPossibilities.add(oldPossibility+otherPossibility);
						}
					}
					subrulePossibilities = newPossibilities;
				}
				possibilities.addAll(subrulePossibilities);
			}
		}
		rulePossibilities[index] = possibilities;
	}
}
