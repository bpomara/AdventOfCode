package AoC2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayThree {
    private static final ArrayList<String> bagList = new ArrayList<>();
    public static void fileToArray() {
        try {
            File file = new File("src\\AoC2022\\input\\dayThree.txt");
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String input = scan.nextLine();
                bagList.add(input);
            }
            scan.close();
        }catch (FileNotFoundException error) {
            System.err.println("Whoops! File not found.");
        }
    }

    private static char commonLetter(String comp1, String comp2){
        for (int i = 0; i < comp1.length(); i++){
            if (comp2.contains(""+comp1.charAt(i))){
                return comp1.charAt(i);
            }
        }
        return '?';
    }

    private static char commonLetter(String bag1, String bag2, String bag3){
        for (int i = 0; i < bag1.length(); i++){
            if (bag2.contains(""+bag1.charAt(i)) && bag3.contains(""+bag1.charAt(i))){
                return bag1.charAt(i);
            }
        }
        return '?';
    }
    public static void main1(String[] args){
        fileToArray();
        int prioritySum = 0;
        for (String bag : bagList) {
            char mistake = commonLetter(bag.substring(0, bag.length() / 2), bag.substring(bag.length() / 2));
            System.out.println(mistake);
            prioritySum += Character.isUpperCase(mistake) ? mistake - 64 + 26 : mistake - 96;
        }
        System.out.println(prioritySum);
    }

    public static void main(String[] args){
        fileToArray();
        int prioritySum = 0;
        for (int i = 0; i < bagList.size(); i+=3) {
            char badge = commonLetter(bagList.get(i), bagList.get(i+1),bagList.get(i+2));
            System.out.println(badge);
            prioritySum += Character.isUpperCase(badge) ? badge - 64 + 26 : badge - 96;
        }
        System.out.println(prioritySum);
    }
}
