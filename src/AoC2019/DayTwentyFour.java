package AoC2019;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DayTwentyFour {
    static boolean[][] eris = new boolean[5][5];
    static ArrayList<boolean[][]> history = new ArrayList<>();

    static void convertToArray() {
        try {
            File file = new File("src\\AoC2019\\dayTwentyFour.txt");
            Scanner scan = new Scanner(file);
            for (int y = 0; scan.hasNextLine(); y++) {
                String line = scan.nextLine();
                for (int x = 0; !line.equals(""); x++) {
                    eris[y][x] = line.charAt(0) == '#';
                    line = line.substring(1);
                }
            }
            scan.close();
        }catch (FileNotFoundException error) {
            System.err.println("Whoops! File not found.");
        }
    }

    static void step() {
        boolean[][] newEris = new boolean[5][5];
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                int bugsAdjacent = 0;
                if (x > 0) if (eris[y][x-1]) bugsAdjacent++;
                if (y > 0) if (eris[y-1][x]) bugsAdjacent++;
                if (x < 4) if (eris[y][x+1]) bugsAdjacent++;
                if (y < 4) if (eris[y+1][x]) bugsAdjacent++;
                if (eris[y][x]){
                    newEris[y][x] = bugsAdjacent == 1;
                }else {
                    newEris[y][x] = bugsAdjacent == 1 || bugsAdjacent == 2;
                }
            }
        }
        for (int y = 0; y < 5; y++) {
            System.arraycopy(newEris[y], 0, eris[y], 0, 5);
        }
    }

    static boolean check() {
        for (boolean[][] oldEris : history) {
            if(Arrays.deepEquals(oldEris, eris)) {
                return true;
            }
        }
        boolean[][] copy = new boolean[5][5];
        for (int y = 0; y < 5; y++) {
            System.arraycopy(eris[y], 0, copy[y], 0, 5);
        }
        history.add(copy);
        return false;
    }

    static int calculateBiodiversity() {
        int biodiversity = 0;
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                if(eris[y][x]) {
                    biodiversity += Math.pow(2,(5*y+x));
                }
            }
        }
        return biodiversity;
    }

    public static void main(String[] args) {
        convertToArray();
        while (!check()) step();
        System.out.println(calculateBiodiversity());
    }
}
