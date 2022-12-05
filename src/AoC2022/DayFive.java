package AoC2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayFive {
    private static ArrayList<Character>[] stacks;
    private static final ArrayList<Integer> quantities = new ArrayList<>();
    private static final ArrayList<Integer> origins = new ArrayList<>();
    private static final ArrayList<Integer> destinations = new ArrayList<>();
    public static void fileToArray() {
        try {
            File file = new File("src\\AoC2022\\input\\dayFive.txt");
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String input = scan.nextLine();
                if (!input.contains("[")){
                    scan.nextLine();
                    break;
                }
                if (stacks == null){
                    stacks = new ArrayList[(input.length()+1)/4];
                    for(int i = 0; i < stacks.length; i++){
                        stacks[i] = new ArrayList<>();
                    }
                }
                for (int i = 0; i < stacks.length; i++){
                    if (input.charAt(i*4+1) != ' ')stacks[i].add(0,input.charAt(i*4+1));
                }
            }
            while (scan.hasNextLine()) {
                String input = scan.nextLine();
                quantities.add(Integer.parseInt(input.substring(5,input.indexOf(" from"))));
                origins.add(Integer.parseInt(input.substring(input.indexOf(" to")-1,input.indexOf(" to")))-1);
                destinations.add(Integer.parseInt(input.substring(input.length()-1))-1);
            }
            scan.close();
        }catch (FileNotFoundException error) {
            System.err.println("Whoops! File not found.");
        }
    }

    public static void main1(String[] args){
        fileToArray();
        for(int i = 0; i < quantities.size(); i++){
            for (int j = 0; j <quantities.get(i); j++){
                char crate = stacks[origins.get(i)].remove(stacks[origins.get(i)].size()-1);
                stacks[destinations.get(i)].add(crate);
            }
        }
        for (ArrayList stack : stacks){
            System.out.print(stack.get(stack.size()-1));
        }
    }

    public static void main(String[] args){
        fileToArray();
        for(int i = 0; i < quantities.size(); i++){
            int stackSize = stacks[destinations.get(i)].size();
            for (int j = 0; j <quantities.get(i); j++){
                char crate = stacks[origins.get(i)].remove(stacks[origins.get(i)].size()-1);
                stacks[destinations.get(i)].add(stackSize,crate);
            }
        }
        for (ArrayList stack : stacks){
            System.out.print(stack.get(stack.size()-1));
        }
    }
}
