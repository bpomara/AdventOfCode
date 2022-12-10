package AoC2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayTen {
    private static final ArrayList<String> inputList = new ArrayList<>();
    public static void fileToArray() {
        try {
            File file = new File("src\\AoC2022\\input\\dayTen.txt");
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String input = scan.nextLine();
                inputList.add(input);
            }
            scan.close();
        }catch (FileNotFoundException error) {
            System.err.println("Whoops! File not found.");
        }
    }

    public static void main(String[] args){
        fileToArray();
        int register = 1;
        ArrayList<Integer> registerRecord = new ArrayList<>();
        for (String command : inputList){
            registerRecord.add(register);
            if(command.startsWith("addx")){
                registerRecord.add(register);
                register += Integer.parseInt(command.substring(5));
            }
        }
        int totalStrength = 0;
        for (int pixel = 0; pixel < registerRecord.size(); pixel++){
            if (Math.abs(pixel%40-registerRecord.get(pixel)) <= 1){
                System.out.print("\u001B[40m \u001B[0m");
            }
            else{
                System.out.print(" ");
            }
            if (pixel%40==39)System.out.println();
            if (pixel%40==19)totalStrength+= (pixel+1)*registerRecord.get(pixel);
        }
        System.out.println(totalStrength);
    }
}
