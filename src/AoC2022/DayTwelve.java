package AoC2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DayTwelve {
    private static int[][] heightMap;
    private static int[][] distanceMap;
    private static int[] startLocation;
    private static int[] endLocation;
    public static void fileToArray() {
        try {
            File file = new File("src\\AoC2022\\input\\dayTwelve.txt");
            Scanner scan = new Scanner(file);
            ArrayList<int[]> inputList = new ArrayList<>();
            while (scan.hasNextLine()) {
                String input = scan.nextLine();
                int[] row = new int[input.length()];
                for (int i = 0; i < input.length(); i++){
                    if(input.charAt(i) == 'S'){
                        startLocation = new int[]{inputList.size(), i};
                        row[i] = 1;
                    }else if(input.charAt(i) == 'E'){
                        endLocation = new int[]{inputList.size(), i};
                        row[i] = 26;
                    }else{
                        row[i] = input.charAt(i)-96;
                    }
                }
                inputList.add(row);
            }
            scan.close();
            heightMap = new int[inputList.size()][inputList.get(0).length];
            distanceMap = new int[inputList.size()][inputList.get(0).length];
            for (int y = 0; y < inputList.size(); y++){
                heightMap[y] = inputList.get(y);
                Arrays.fill(distanceMap[y], -1);
            }
            distanceMap[endLocation[0]][endLocation[1]] = 0;
        }catch (FileNotFoundException error) {
            System.err.println("Whoops! File not found.");
        }
    }

    public static void main(String[] args){
        fileToArray();
        ArrayList<int[]> currentRound = new ArrayList<>();
        ArrayList<int[]> nextRound = new ArrayList<>();
        currentRound.add(endLocation);
        while (!currentRound.isEmpty()){
            for (int[] space : currentRound){
                if (space[0] > 0 && distanceMap[space[0]-1][space[1]] < 0 && heightMap[space[0]-1][space[1]]+1 >= heightMap[space[0]][space[1]]){
                    distanceMap[space[0]-1][space[1]] = distanceMap[space[0]][space[1]] + 1;
                    nextRound.add(new int[]{space[0]-1,space[1]});
                }
                if (space[1] > 0 && distanceMap[space[0]][space[1]-1] < 0 && heightMap[space[0]][space[1]-1]+1 >= heightMap[space[0]][space[1]]){
                    distanceMap[space[0]][space[1]-1] = distanceMap[space[0]][space[1]] + 1;
                    nextRound.add(new int[]{space[0],space[1]-1});
                }
                if (space[0] < heightMap.length-1 && distanceMap[space[0]+1][space[1]] < 0 && heightMap[space[0]+1][space[1]]+1 >= heightMap[space[0]][space[1]]){
                    distanceMap[space[0]+1][space[1]] = distanceMap[space[0]][space[1]] + 1;
                    nextRound.add(new int[]{space[0]+1,space[1]});
                }
                if (space[1] < heightMap[0].length-1 && distanceMap[space[0]][space[1]+1] < 0 && heightMap[space[0]][space[1]+1]+1 >= heightMap[space[0]][space[1]]){
                    distanceMap[space[0]][space[1]+1] = distanceMap[space[0]][space[1]] + 1;
                    nextRound.add(new int[]{space[0],space[1]+1});
                }
            }
            currentRound = nextRound;
            nextRound = new ArrayList<>();
        }
        System.out.println(distanceMap[startLocation[0]][startLocation[1]]);
        int minHike = Integer.MAX_VALUE;
        for (int y = 0; y < heightMap.length; y++){
            for (int x = 0; x < heightMap[y].length; x++){
                if (heightMap[y][x] == 1 && distanceMap[y][x] < minHike && distanceMap[y][x] >= 0) minHike = distanceMap[y][x];
            }
        }
        System.out.println(minHike);
    }
}
