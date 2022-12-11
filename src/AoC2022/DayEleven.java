package AoC2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DayEleven {
    private static ArrayList<ArrayList<Long>> hoardList = new ArrayList<>();
    private static ArrayList<String> operationList = new ArrayList<>();
    private static ArrayList<Integer> testList = new ArrayList<>();
    private static ArrayList<Integer> trueMonkeys = new ArrayList<>();
    private static ArrayList<Integer> falseMonkeys = new ArrayList<>();
    private static long[] handleCounts;
    private static long tests = 1;


    public static void fileToArrays() {
        try {
            File file = new File("src\\AoC2022\\input\\dayEleven.txt");
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String input = scan.nextLine();
                input = input.trim();
                if (input.startsWith("Starting items:")){
                    String[] splitInput = input.substring(16).split(", ");
                    ArrayList<Long> newHoard = new ArrayList<>();
                    for (String item : splitInput){
                        newHoard.add(Long.parseLong(item));
                    }
                    hoardList.add(newHoard);
                }else if(input.startsWith("Operation:")){
                    operationList.add(input.substring(21));
                }else if(input.startsWith("Test:")){
                    testList.add(Integer.parseInt(input.substring(19)));
                }else if(input.startsWith("If true:")){
                    trueMonkeys.add(Integer.parseInt(input.substring(25)));
                }else if(input.startsWith("If false:")){
                    falseMonkeys.add(Integer.parseInt(input.substring(26)));
                }
            }
            scan.close();
            handleCounts = new long[hoardList.size()];
            for(int test :testList){
                tests *= test;
            }
        }catch (FileNotFoundException error) {
            System.err.println("Whoops! File not found.");
        }
    }

    public static void main(String[] args){
        fileToArrays();
        for(int round = 0; round < 10000; round++){
            if (round%1000 == 0)System.out.println(round);
            for (int monkey = 0; monkey < hoardList.size(); monkey++){
                while(!hoardList.get(monkey).isEmpty()){
                    long item = hoardList.get(monkey).remove(0);
                    if (operationList.get(monkey).contains("old")){
                        item *= item;
                    }else if (operationList.get(monkey).charAt(0) == '*'){
                        item *= Integer.parseInt(operationList.get(monkey).substring(2));
                    }else {
                        item += Integer.parseInt(operationList.get(monkey).substring(2));
                    }
                    //item /= 3;
                    while (item > tests){
                        item -= tests;
                    }
                    if (item%testList.get(monkey) == 0){
                        hoardList.get(trueMonkeys.get(monkey)).add(item);
                    }else{
                        hoardList.get(falseMonkeys.get(monkey)).add(item);
                    }
                    handleCounts[monkey]++;
                }
            }
        }
        Arrays.sort(handleCounts);
        System.out.println(handleCounts[handleCounts.length-1]*handleCounts[handleCounts.length-2]);
    }
}
