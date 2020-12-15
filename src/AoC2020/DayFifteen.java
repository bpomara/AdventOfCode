package AoC2020;

import java.util.ArrayList;

public class DayFifteen {
	public static ArrayList<Integer> memList = new ArrayList<>();
	public static int[] formerIndexes = new int[100000000];

	public static void stringToArray(String input) {
		int counter = 1;
		while (input.contains(",")){
			formerIndexes[Integer.parseInt(input.substring(0,input.indexOf(",")))] = counter;
			counter++;
			memList.add(Integer.parseInt(input.substring(0,input.indexOf(","))));
			input = input.substring(input.indexOf(",")+1);
		}
		memList.add(Integer.parseInt(input));
	}

	public static void main(String[] args){
		stringToArray("2,0,6,12,1,3");
		System.out.println(calculate(30000000));
	}

	public static int calculate(int number){
		int prevNum = memList.get(memList.size()-1);
		int nextNum = 0;
		for (int i = memList.size()+1;i < number+1; i++){
			if (formerIndexes[prevNum] != 0) {
				nextNum = i-1 - formerIndexes[prevNum];
				formerIndexes[prevNum] = i-1;
				prevNum = nextNum;
			}else {
				formerIndexes[prevNum] = i-1;
				prevNum = 0;
			}
		}
		return nextNum;
	}
}
