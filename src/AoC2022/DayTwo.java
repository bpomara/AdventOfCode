package AoC2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayTwo {
    private static ArrayList<Character> opponentStrategies = new ArrayList<>();
    private static ArrayList<Character> myStrategies = new ArrayList<>();
    public static void fileToArray() {
        try {
            File file = new File("src\\AoC2022\\input\\dayTwo.txt");
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String input = scan.nextLine();
                opponentStrategies.add(input.charAt(0));
                myStrategies.add(input.charAt(2));
            }
            scan.close();
        }catch (FileNotFoundException error) {
            System.err.println("Whoops! File not found.");
        }
    }

    public static void main1(String[] args){
        fileToArray();
        int points = 0;
        for(int i = 0; i < opponentStrategies.size(); i++){
            if(opponentStrategies.get(i) -65 == myStrategies.get(i) -88) {
                //Draw
                points += 3 + (myStrategies.get(i) - 87);
            }else if((opponentStrategies.get(i) -64)%3 == myStrategies.get(i) -88){
                //Win
                points += 6 + (myStrategies.get(i) - 87);
            }else {
                //Loss
                points += (myStrategies.get(i) - 87);
            }
        }
        System.out.println(points);
    }

    public static void main2(String[] args){
        fileToArray();
        int points = 0;
        for(int i = 0; i < opponentStrategies.size(); i++){
            if(myStrategies.get(i) == 'Y') {
                //Draw
                points += 3 + (opponentStrategies.get(i) - 64);
            }else if(myStrategies.get(i) == 'Z'){
                //Win
                points += 6 + ((opponentStrategies.get(i) - 64)%3+1);
            }else {
                //Loss
                points += ((opponentStrategies.get(i) - 63)%3+1);
            }
        }
        System.out.println(points);
    }
}
