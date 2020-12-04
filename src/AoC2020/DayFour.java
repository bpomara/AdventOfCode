package AoC2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayFour {

	public static void main(String[] args) {
		ArrayList<String> fileList = new ArrayList<>();
		try {
			File file = new File("src\\AoC2020\\inputs\\dayFour.txt");
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				fileList.add(scan.next());
			}
			scan.close();
		}catch (FileNotFoundException error) {
			System.err.println("Whoops! File not found.");
		}
		boolean[] completeness = new boolean[8];
		int valids = 0;
		for (String entry : fileList){
			if (entry.contains(":")) {
				String label = entry.substring(0, entry.indexOf(':'));
				String data = entry.substring(entry.indexOf(':')+1);
				switch (label) {
					case "byr":
						if (Integer.parseInt(data)>=1920 && Integer.parseInt(data)<=2002){
							completeness[0] = true;
						}
						break;
					case "iyr":
						if (Integer.parseInt(data)>=2010 && Integer.parseInt(data)<=2020) {
							completeness[1] = true;
						}
						break;
					case "eyr":
						if (Integer.parseInt(data)>=2020 && Integer.parseInt(data)<=2030) {
							completeness[2] = true;
						}
						break;
					case "hgt":
						if (data.endsWith("cm") && Integer.parseInt(data.substring(0,data.length()-2))>=150 && Integer.parseInt(data.substring(0,data.length()-2))<=193){
							completeness[3] = true;
						}else if (data.endsWith("in") && Integer.parseInt(data.substring(0,data.length()-2))>=59 && Integer.parseInt(data.substring(0,data.length()-2))<=76){
							completeness[3] = true;
						}
						break;
					case "hcl":
						if (data.length() == 7 && data.charAt(0) == '#' && data.toLowerCase().equals(data)){
							try{
								Integer.parseInt(data.substring(1),16);
								completeness[4] = true;
							}catch(NumberFormatException ignored){

							}
						}
						break;
					case "ecl":
						switch (data) {
							case "amb", "blu", "brn", "gry", "grn", "hzl", "oth" -> completeness[5] = true;
						}
						break;
					case "pid":
						if (data.length() == 9) {
							completeness[6] = true;
						}
						break;
					case "cid":
						completeness[7] = true;
						break;
					default:
						System.out.println("Whoops!");
				}
			}else {
				if (completeness[0] && completeness[1] && completeness[2] && completeness[3] && completeness[4] && completeness[5] && completeness[6]){
					valids++;
				}
				completeness = new boolean[8];
			}
		}
		System.out.println(valids);
	}
}
