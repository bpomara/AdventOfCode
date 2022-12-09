package AoC2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayNine {
    private static final ArrayList<Character> commandDirections = new ArrayList<>();
    private static final ArrayList<Integer> commandLengths = new ArrayList<>();

    public static void fileToArray() {
        try {
            File file = new File("src\\AoC2022\\input\\dayNine.txt");
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String input = scan.nextLine();
                commandDirections.add(input.charAt(0));
                commandLengths.add(Integer.parseInt(input.substring(2)));
            }
            scan.close();
        }catch (FileNotFoundException error) {
            System.err.println("Whoops! File not found.");
        }
    }


    public static void main(String[] args){
        fileToArray();
        ArrayList<String> pastTailLocations = new ArrayList<>();
        int[][] knotLocations = new int[10][2];
        for (int i = 0; i < commandDirections.size(); i++){
            for(int j = 0; j < commandLengths.get(i); j++) {
                switch (commandDirections.get(i)) {
                    case 'R' -> knotLocations[0][0]++;
                    case 'L' -> knotLocations[0][0]--;
                    case 'U' -> knotLocations[0][1]++;
                    case 'D' -> knotLocations[0][1]--;
                }
                for (int k = 1; k < knotLocations.length; k++) {
                    int diffX = knotLocations[k-1][0]-knotLocations[k][0];
                    int diffY = knotLocations[k-1][1]-knotLocations[k][1];
                    if (Math.sqrt(Math.pow(diffX,2)+Math.pow(diffY,2)) > 1.5){
                        if (diffX != 0)knotLocations[k][0] += diffX / Math.abs(diffX);
                        if (diffY != 0)knotLocations[k][1] += diffY / Math.abs(diffY);
                    }
                }
                if(!pastTailLocations.contains(knotLocations[knotLocations.length-1][0]+","+knotLocations[knotLocations.length-1][1])){
                    pastTailLocations.add(knotLocations[knotLocations.length-1][0]+","+knotLocations[knotLocations.length-1][1]);
                }
            }
        }
        System.out.println(pastTailLocations.size());
    }
}
