package AoC2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayThirteen {
	public static int timestamp;
	public static int[] busIDs;

	public static void fileToArray() {
		try {
			ArrayList<Integer> inputList = new ArrayList<>();
			File file = new File("src\\AoC2020\\inputs\\dayThirteen.txt");
			Scanner scan = new Scanner(file);
			timestamp = Integer.parseInt(scan.nextLine());
			String input = scan.nextLine();
			while (input.contains(",")) {
				String number = input.substring(0,input.indexOf(","));
				if (number.equals("x")){
					inputList.add(-1);
				}else {
					inputList.add(Integer.parseInt(number));
				}
				input = input.substring(input.indexOf(",")+1);
			}
			inputList.add(Integer.parseInt(input));
			scan.close();
			busIDs = new int[inputList.size()];
			for (int i = 0; i < inputList.size(); i++) {
				busIDs[i] = inputList.get(i);
			}
		}catch (FileNotFoundException error) {
			System.err.println("Whoops! File not found.");
		}
	}

	public static void main(String[] args){
		fileToArray();
		long time = 0;
		outerLoop:
		while (true) {
			//System.out.println(time);
			long jumper = 1;
			for (int i = 0; i < busIDs.length; i++){
				if (busIDs[i]>0){
					if((time+i)%busIDs[i]!=0) {
						time += jumper;
						continue outerLoop;
					}else{
						if (jumper % busIDs[i] != 0) jumper *=busIDs[i];
					}
				}
			}
			break;
		}
		System.out.println(time);
	}
/*
	public static void main(String[] args){
		fileToArray();
		int original = timestamp;
		while (true) {
			for (int id : busIDs){
				if(timestamp % id == 0){
					System.out.println(timestamp + " " + id + " " + (timestamp-original)*id);
					return;
				}
			}
			timestamp++;
		}
	}

 */
}
