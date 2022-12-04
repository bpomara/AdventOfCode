package AoC2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayFour {
    private static final ArrayList<int[]> groupList = new ArrayList<>();
    public static void fileToArray() {
        try {
            File file = new File("src\\AoC2022\\input\\dayFour.txt");
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String input = scan.nextLine();
                int[] idIndex = {   Integer.parseInt(input.substring(0,input.indexOf("-"))),
                                    Integer.parseInt(input.substring(input.indexOf("-")+1,input.indexOf(","))),
                                    Integer.parseInt(input.substring(input.indexOf(",")+1,input.lastIndexOf("-"))),
                                    Integer.parseInt(input.substring(input.lastIndexOf("-")+1))};
                groupList.add(idIndex);
            }
            scan.close();
        }catch (FileNotFoundException error) {
            System.err.println("Whoops! File not found.");
        }
    }

    public static void main(String[] args){
        fileToArray();
        int subsets = 0;
        int overlaps = 0;
        for (int[] group : groupList){
            if ((group[0]<=group[2]&&group[1]>=group[3]) || (group[0]>=group[2]&&group[1]<=group[3])) {
                subsets++;
                overlaps++;
            }else if((group[1]>=group[2] && group[0]<=group[2]) || (group[3]>=group[0] && group[3]<=group[1])){
                overlaps++;
            }
        }
        System.out.println(subsets);
        System.out.println(overlaps);
    }
}