package AoC2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayFifteen {
    private static final ArrayList<int[]> sensorLocations = new ArrayList<>();
    private static final ArrayList<int[]> beaconLocations = new ArrayList<>();
    public static void fileToArrays() {
        try {
            File file = new File("src\\AoC2022\\input\\dayFifteen.txt");
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String input = scan.nextLine();
                sensorLocations.add(new int[]{Integer.parseInt(input.substring(input.indexOf("x=")+2,input.indexOf(","))),Integer.parseInt(input.substring(input.indexOf("y=")+2,input.indexOf(":")))});
                beaconLocations.add(new int[]{Integer.parseInt(input.substring(input.lastIndexOf("x=")+2,input.lastIndexOf(","))),Integer.parseInt(input.substring(input.lastIndexOf("y=")+2))});
            }
            scan.close();
        }catch (FileNotFoundException error) {
            System.err.println("Whoops! File not found.");
        }
    }

    public static void main1(String[] args){
        fileToArrays();
        int testRow = 2000000;
        ArrayList<int[]> coveredAreas = new ArrayList<>();
        int leftScan = Integer.MAX_VALUE;
        int rightScan = Integer.MIN_VALUE;
        for(int s = 0; s < sensorLocations.size(); s++){
            int sbDistance = Math.abs(sensorLocations.get(s)[0]-beaconLocations.get(s)[0])+Math.abs(beaconLocations.get(s)[1]-sensorLocations.get(s)[1]);
            if (Math.abs(testRow - sensorLocations.get(s)[1]) <= sbDistance) {
                int leftBoundary = sensorLocations.get(s)[0] - (sbDistance - Math.abs(testRow - sensorLocations.get(s)[1]));
                int rightBoundary = sensorLocations.get(s)[0] + (sbDistance - Math.abs(testRow - sensorLocations.get(s)[1]));
                coveredAreas.add(new int[]{leftBoundary, rightBoundary});
                if(leftBoundary < leftScan) leftScan = leftBoundary;
                if(rightBoundary > rightScan) rightScan = rightBoundary;
            }
        }
        ArrayList<Integer> beaconsOnTestRow = new ArrayList<>();
        for(int[] beacon : beaconLocations){
            if (beacon[1] == testRow && !beaconsOnTestRow.contains(beacon[0])){
                beaconsOnTestRow.add(beacon[0]);
            }
        }
        int totalCovered = -beaconsOnTestRow.size();
        outerLoop:
        for(int i = leftScan; i <= rightScan; i++){
            for(int[] boundaries : coveredAreas){
                if (i >= boundaries[0] && i <= boundaries[1]){
                    totalCovered++;
                    continue outerLoop;
                }
            }
        }
        System.out.println(totalCovered);
    }

    public static void main(String[] args){
        fileToArrays();
        for (int y = 0; y <= 4000000; y++) {
            ArrayList<int[]> coveredAreas = new ArrayList<>();
            for (int s = 0; s < sensorLocations.size(); s++) {
                int sbDistance = Math.abs(sensorLocations.get(s)[0] - beaconLocations.get(s)[0]) + Math.abs(beaconLocations.get(s)[1] - sensorLocations.get(s)[1]);
                if (Math.abs(y - sensorLocations.get(s)[1]) <= sbDistance) {
                    int leftBoundary = Math.max(0,sensorLocations.get(s)[0] - (sbDistance - Math.abs(y - sensorLocations.get(s)[1])));
                    int rightBoundary = Math.min(4000000,sensorLocations.get(s)[0] + (sbDistance - Math.abs(y - sensorLocations.get(s)[1])));
                    coveredAreas.add(new int[]{leftBoundary, rightBoundary});
                }
            }
            for(int i = 1; i < coveredAreas.size(); i++){
                if((coveredAreas.get(i)[0] >= coveredAreas.get(0)[0]-1 && coveredAreas.get(i)[0] <= coveredAreas.get(0)[1]+1) || (coveredAreas.get(i)[1] >= coveredAreas.get(0)[0]-1 && coveredAreas.get(i)[1] <= coveredAreas.get(0)[1]+1) || (coveredAreas.get(0)[0] >= coveredAreas.get(i)[0]-1 && coveredAreas.get(0)[0] <= coveredAreas.get(i)[1]+1) || (coveredAreas.get(0)[1] >= coveredAreas.get(i)[0]-1 && coveredAreas.get(0)[1] <= coveredAreas.get(i)[1]+1)){
                    int leftBoundary = Math.min(coveredAreas.get(i)[0],coveredAreas.get(0)[0]);
                    int rightBoundary = Math.max(coveredAreas.get(i)[1],coveredAreas.get(0)[1]);
                    coveredAreas.set(0, new int[]{leftBoundary,rightBoundary});
                    coveredAreas.remove(i);
                    i = 0;
                }
            }
            if(coveredAreas.size() > 1){
                if(coveredAreas.get(0)[1] < 4000000){
                    System.out.println((coveredAreas.get(0)[1]+1)*4);
                    System.out.println(y);
                    System.out.println(4000000L*(coveredAreas.get(0)[1]+1)+y);
                }else{
                    System.out.println((coveredAreas.get(0)[0]-1)*4);
                    System.out.println(y);
                    System.out.println(4000000L*(coveredAreas.get(0)[1]+1)+y);
                }
            }
        }
    }
}
