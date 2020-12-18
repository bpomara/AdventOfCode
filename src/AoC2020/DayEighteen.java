package AoC2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DayEighteen {
	public static ArrayList<String> problemList = new ArrayList<>();

	public static void fileToArray() {
		try {
			File file = new File("src\\AoC2020\\inputs\\dayEighteen.txt");
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				String input = scan.nextLine();
				problemList.add(input);
			}
			scan.close();
		}catch (FileNotFoundException error) {
			System.err.println("Whoops! File not found.");
		}
	}

	public static void main(String[] args){
		fileToArray();
		long sum = 0;
		for (String statement : problemList){
			sum += processStatement(statement);
		}
		System.out.println(sum);
	}

	public static long processStatement1(String statement){
		statement += " ";
		while (statement.contains("(")){
			String subStatement = statement.substring(statement.lastIndexOf("(")+1);
			subStatement = subStatement.substring(0,subStatement.indexOf(")"));
			statement = statement.substring(0,statement.lastIndexOf("(")) + processStatement(subStatement) + statement.substring(statement.lastIndexOf("(")+subStatement.length()+2);
		}
		while (statement.contains("+") || statement.contains("*")){
			long num1 = Long.parseLong(statement.substring(0,statement.indexOf(" ")));
			statement = statement.substring(statement.indexOf(" ")+1);
			char operator = statement.charAt(0);
			statement = statement.substring(statement.indexOf(" ")+1);
			long num2 = Long.parseLong(statement.substring(0,statement.indexOf(" ")));
			statement = statement.substring(statement.indexOf(" "));
			long result;
			if (operator == '+'){
				result = num1 + num2;
			}else {
				result = num1 * num2;
			}
			statement = result + statement;
		}
		return Long.parseLong(statement.substring(0,statement.length()-1));
	}

	public static long processStatement(String statement){
		statement += " ";
		while (statement.contains("(")){
			String subStatement = statement.substring(statement.lastIndexOf("(")+1);
			subStatement = subStatement.substring(0,subStatement.indexOf(")"));
			statement = statement.substring(0,statement.lastIndexOf("(")) + processStatement(subStatement) + statement.substring(statement.lastIndexOf("(")+subStatement.length()+2);
		}
		ArrayList<String> statementArray = new ArrayList<>(Arrays.asList(statement.split(" ")));
		while (statementArray.contains("+")){
			long num1 = Long.parseLong(statementArray.get(statementArray.indexOf("+")-1));
			long num2 = Long.parseLong(statementArray.get(statementArray.indexOf("+")+1));
			statementArray.remove(statementArray.indexOf("+")-1);
			statementArray.remove(statementArray.indexOf("+")+1);
			statementArray.set(statementArray.indexOf("+"),(num1+num2)+"");
		}
		while (statementArray.contains("*")){
			long num1 = Long.parseLong(statementArray.get(statementArray.indexOf("*")-1));
			long num2 = Long.parseLong(statementArray.get(statementArray.indexOf("*")+1));
			statementArray.remove(statementArray.indexOf("*")-1);
			statementArray.remove(statementArray.indexOf("*")+1);
			statementArray.set(statementArray.indexOf("*"),(num1*num2)+"");
		}
		return Long.parseLong(statementArray.get(0));
	}
}
