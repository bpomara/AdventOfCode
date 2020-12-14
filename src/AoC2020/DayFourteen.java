package AoC2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class DayFourteen {
	public static ArrayList<String> masks = new ArrayList<>();
	public static ArrayList<ArrayList<String>> memAddresses = new ArrayList<>();
	public static ArrayList<ArrayList<Integer>> values = new ArrayList<>();
	public static ArrayList<Long> memoryIndex = new ArrayList<>();
	public static ArrayList<Integer> memoryValues = new ArrayList<>();

	public static void fileToArray() {
		try {
			File file = new File("src\\AoC2020\\inputs\\dayFourteen.txt");
			Scanner scan = new Scanner(file);
			ArrayList<String> memAddressList = new ArrayList<>();
			ArrayList<Integer> valueList = new ArrayList<>();
			while (scan.hasNextLine()) {
				String input = scan.nextLine();
				if (input.startsWith("mas")){
					masks.add(input.substring(input.indexOf("=")+2));
					if (masks.size() != 1) {
						memAddresses.add(memAddressList);
						values.add(valueList);
						memAddressList = new ArrayList<>();
						valueList = new ArrayList<>();
					}
				}else {
					String memAddress = Integer.toBinaryString(Integer.parseInt(input.substring(input.indexOf("[")+1,input.indexOf("]"))));
					while (memAddress.length()<36){
						memAddress = "0"+memAddress;
					}
					memAddressList.add(memAddress);
					valueList.add(Integer.parseInt(input.substring(input.indexOf("=")+2)));
				}
			}
			memAddresses.add(memAddressList);
			values.add(valueList);
			scan.close();
		}catch (FileNotFoundException error) {
			System.err.println("Whoops! File not found.");
		}
	}

	public static void main(String[] args){
		fileToArray();
		for (int i = 0; i < masks.size(); i++){
			String mask = masks.get(i);
			for (int j = 0; j < values.get(i).size(); j++){
				String output = "";
				String address = memAddresses.get(i).get(j);
				for (int k = 0; k < mask.length(); k++){
					if (mask.charAt(k) == '0'){
						output += address.charAt(k);
					}else{
						output += mask.charAt(k);
					}
				}
				ArrayList<String> combos = new ArrayList<>();
				ArrayList<String> newCombos = new ArrayList<>();
				combos.add(output);
				for (int k = 0; k < combos.get(0).length(); k++){
					if (combos.get(0).charAt(k) == 'X'){
						for (String combo : combos){
							newCombos.add(combo.substring(0,k)+"0"+combo.substring(k+1));
							newCombos.add(combo.substring(0,k)+"1"+combo.substring(k+1));
						}
						combos = newCombos;
						newCombos = new ArrayList<>();
					}
				}
				for (String combo : combos) {
					long index = Long.parseLong(combo,2);
					if (!memoryIndex.contains(index)){
						memoryIndex.add(index);
						memoryValues.add(values.get(i).get(j));
					}else {
						memoryValues.set(memoryIndex.indexOf(index), values.get(i).get(j));
					}
					//memory[Integer.parseInt(combo,2)] = values.get(i).get(j);
				}
			}
		}
		long sum = 0;
		for (long num : memoryValues){
			sum+=num;
		}
		System.out.println(sum);
	}
}
/*
Part One:
public class DayFourteen {
	public static ArrayList<String> masks = new ArrayList<>();
	public static ArrayList<ArrayList<Integer>> memAddresses = new ArrayList<>();
	public static ArrayList<ArrayList<String>> values = new ArrayList<>();
	public static long[] memory = new long[100000];

	public static void fileToArray() {
		try {
			File file = new File("src\\AoC2020\\inputs\\dayFourteen.txt");
			Scanner scan = new Scanner(file);
			ArrayList<Integer> memAddressList = new ArrayList<>();
			ArrayList<String> valueList = new ArrayList<>();
			while (scan.hasNextLine()) {
				String input = scan.nextLine();
				if (input.startsWith("mas")){
					masks.add(input.substring(input.indexOf("=")+2));
					if (masks.size() != 1) {
						memAddresses.add(memAddressList);
						values.add(valueList);
						memAddressList = new ArrayList<>();
						valueList = new ArrayList<>();
					}
				}else {
					memAddressList.add(Integer.parseInt(input.substring(input.indexOf("[")+1,input.indexOf("]"))));
					String value = Integer.toBinaryString(Integer.parseInt(input.substring(input.indexOf("=")+2)));
					while (value.length()<36){
						value = "0"+value;
					}
					valueList.add(value);
				}
			}
			memAddresses.add(memAddressList);
			values.add(valueList);
			scan.close();
		}catch (FileNotFoundException error) {
			System.err.println("Whoops! File not found.");
		}
	}

	public static void main(String[] args){
		fileToArray();
		for (int i = 0; i < masks.size(); i++){
			String mask = masks.get(i);
			for (int j = 0; j < values.get(i).size(); j++){
				String output = "";
				String value = values.get(i).get(j);
				for (int k = 0; k < mask.length(); k++){
					if (mask.charAt(k) == 'X'){
						output += value.charAt(k);
					}else{
						output += mask.charAt(k);
					}
				}
				memory[memAddresses.get(i).get(j)] = Long.parseLong(output,2);
			}
		}
		long sum = 0;
		for (long num : memory){
			sum+=num;
		}
		System.out.println(sum);
	}
}
 */