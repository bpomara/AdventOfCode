package AoC2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DayFourteen {
    private static int maxY = 0;
    public static boolean[][] fileToArray() {
        boolean[][] output = new boolean[200][700];
        try {
            File file = new File("src\\AoC2022\\input\\dayFourteen.txt");
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String input = scan.nextLine();
                String[] coordinates = input.split(" -> ");
                for(int i = 0; i < coordinates.length-1; i++){
                    int startX = Integer.parseInt(coordinates[i].substring(0,coordinates[i].indexOf(",")));
                    int startY = Integer.parseInt(coordinates[i].substring(coordinates[i].indexOf(",")+1));
                    int endX = Integer.parseInt(coordinates[i+1].substring(0,coordinates[i+1].indexOf(",")));
                    int endY = Integer.parseInt(coordinates[i+1].substring(coordinates[i+1].indexOf(",")+1));
                    if (startX == endX){
                        for (int y = Math.min(startY,endY); y <= Math.max(startY,endY); y++){
                            output[y][startX] = true;
                        }
                    }else{
                        for (int x = Math.min(startX,endX); x <= Math.max(startX,endX); x++){
                            output[startY][x] = true;
                        }
                    }
                    if(endY > maxY)maxY = endY;
                    if(startY > maxY)maxY = startY;
                }
            }
            scan.close();
        }catch (FileNotFoundException error) {
            System.err.println("Whoops! File not found.");
        }
        return output;
    }

    public static void main(String[] args){
        boolean[][] map = fileToArray();
        int sandCount = 0;
        int[] sandCoordinates = new int[]{0,500};
        while(true){
            if (sandCoordinates[0] > maxY){
                map[sandCoordinates[0]][sandCoordinates[1]] = true;
                sandCoordinates = new int[]{0,500};
                sandCount++;
            }else if (!map[sandCoordinates[0]+1][sandCoordinates[1]]){
                sandCoordinates[0]++;
            }else if (!map[sandCoordinates[0]+1][sandCoordinates[1]-1]){
                sandCoordinates[0]++;
                sandCoordinates[1]--;
            }else if (!map[sandCoordinates[0]+1][sandCoordinates[1]+1]){
                sandCoordinates[0]++;
                sandCoordinates[1]++;
            }else{
                map[sandCoordinates[0]][sandCoordinates[1]] = true;
                sandCount++;
                if (sandCoordinates[0] == 0) break;
                sandCoordinates = new int[]{0,500};
            }
        }
        System.out.println(sandCount);
    }
}
