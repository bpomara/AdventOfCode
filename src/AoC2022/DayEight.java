package AoC2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DayEight {
    private static byte[][] map;
    public static void fileToArray() {
        try {
            File file = new File("src\\AoC2022\\input\\dayEight.txt");
            Scanner scan = new Scanner(file);
            for (int y = 0; scan.hasNextLine(); y++) {
                String input = scan.nextLine();
                if (map == null) map = new byte[input.length()][input.length()];
                for(int x = 0; x < input.length(); x++){
                    map[y][x] = Byte.parseByte(input.substring(x,x+1));
                }
            }
            scan.close();
        }catch (FileNotFoundException error) {
            System.err.println("Whoops! File not found.");
        }
    }

    public static void main1(String[] args){
        fileToArray();
        boolean[][] visibleTreeMap = new boolean[map.length][map[0].length];
        for (int y = 0; y < map.length; y++){
            byte tallestHeight = -1;
            for(int x = 0; x < map[y].length; x++){
                if(map[y][x] > tallestHeight){
                    tallestHeight = map[y][x];
                    visibleTreeMap[y][x] = true;
                }
            }
        }
        for (int y = 0; y < map.length; y++){
            byte tallestHeight = -1;
            for(int x = map[y].length-1; x >= 0; x--){
                if(map[y][x] > tallestHeight){
                    tallestHeight = map[y][x];
                    visibleTreeMap[y][x] = true;
                }
            }
        }
        for (int x = 0; x < map[0].length; x++){
            byte tallestHeight = -1;
            for(int y = 0; y < map.length; y++){
                if(map[y][x] > tallestHeight){
                    tallestHeight = map[y][x];
                    visibleTreeMap[y][x] = true;
                }
            }
        }
        for (int x = 0; x < map[0].length; x++){
            byte tallestHeight = -1;
            for(int y = map.length-1; y >= 0; y--){
                if(map[y][x] > tallestHeight){
                    tallestHeight = map[y][x];
                    visibleTreeMap[y][x] = true;
                }
            }
        }
        int numVisible = 0;
        for(boolean[] row : visibleTreeMap){
            for (boolean tree : row) if (tree) numVisible++;
        }
        System.out.println(numVisible);
    }

    public static void main(String[] args){
        fileToArray();
        int maxScore = 0;
        for (int y = 0; y < map.length; y++){
            for (int x = 0; x < map[y].length; x++){
                int scenicScore = 1;
                int otherTrees = 0;
                for (int a = x+1; a < map[y].length; a++){
                    otherTrees++;
                    if (map[y][a] >= map[y][x]) break;
                }
                scenicScore *= otherTrees;
                otherTrees = 0;
                for (int a = x-1; a >= 0; a--){
                    otherTrees++;
                    if (map[y][a] >= map[y][x]) break;
                }
                scenicScore *= otherTrees;
                otherTrees = 0;
                for (int b = y+1; b < map.length; b++){
                    otherTrees++;
                    if (map[b][x] >= map[y][x]) break;
                }
                scenicScore *= otherTrees;
                otherTrees = 0;
                for (int b = y-1; b >= 0; b--){
                    otherTrees++;
                    if (map[b][x] >= map[y][x]) break;
                }
                scenicScore *= otherTrees;
                if (scenicScore > maxScore) maxScore = scenicScore;
            }
        }
        System.out.println(maxScore);
    }
}
