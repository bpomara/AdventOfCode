package AoC2019;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayTwentyTwoREVERSE {
    static long cardPosition = 2020;
    final static long deckSize = 119315717514047L;
    static void reverse(){
        cardPosition = deckSize - cardPosition - 1;
    }
    static void cut(int input){
        cardPosition += input;
        if (cardPosition < 0) cardPosition += deckSize;
    }
    static void increment(int input){
        while(cardPosition%input != 0) cardPosition += deckSize;
        cardPosition /= input;
    }
    public static void main(String[] args){
        ArrayList<String> commands = new ArrayList<>();
        try {
            File file = new File("src\\AoC2019\\dayTwentyTwo.txt");
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) commands.add(0,scan.nextLine());
            scan.close();
        }catch (FileNotFoundException error) {
            System.err.println("Whoops! File not found.");
        }
        for (long i = 0; i < 100; i++) {
            for (String command : commands) {
                if (command.equals("deal into new stack")) reverse();
                else if (command.substring(0, 3).equals("cut")) cut(Integer.parseInt(command.substring(4)));
                else if (command.substring(0, 19).equals("deal with increment"))
                    increment(Integer.parseInt(command.substring(20)));
                else System.out.println("Uh-oh! Incorrect direction");

            }
            System.out.println(cardPosition);
        }
    }
}
