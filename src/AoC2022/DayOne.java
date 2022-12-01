package AoC2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DayOne {
    public static ArrayList<Integer> fileToArray() {
        try {
            ArrayList<Integer> inputList = new ArrayList<>();
            File file = new File("src\\AoC2022\\input\\dayOne.txt");
            Scanner scan = new Scanner(file);
            int elfTotal = 0;
            while (scan.hasNextLine()) {
                String input = scan.nextLine();
                if (input.isEmpty()) {
                    inputList.add(elfTotal);
                    elfTotal = 0;
                }else{
                    elfTotal += Integer.parseInt(input);
                }
            }
            scan.close();
            return inputList;
        }catch (FileNotFoundException error) {
            System.err.println("Whoops! File not found.");
            return new ArrayList<>();
        }
    }

    public static void main(String[] args){
        ArrayList<Integer> elfTotals = fileToArray();
        Collections.sort(elfTotals);
        Collections.reverse(elfTotals);
        System.out.println(elfTotals.get(0)+elfTotals.get(1)+elfTotals.get(2));
    }
}
