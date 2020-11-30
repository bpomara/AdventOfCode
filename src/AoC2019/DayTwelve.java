package AoC2019;

import java.util.ArrayList;
import java.util.Arrays;

public class DayTwelve {
    public static int [][] velocities = new int[4][3];
    //public static int[][] positions = {{-8,-9,-7},{-5,2,-1},{11,8,-14},{1,-4,-11}};
    public static int[][] positions = {{-1,0,2},{2,-10,-7},{4,-8,8},{3,5,-1}};
    public static ArrayList<int[][]> pastLocations= new ArrayList<int[][]>();
    public static int counter = 0;

    public static void step() {
        int [][] intArray = new int[4][3];
        for (int dimension = 0; dimension < 3; dimension++) {
            int[] gravityChanges = new int[4];
            for (int planet = 0; planet < 4; planet++) {
                for (int testPlanet = 0; testPlanet < 4; testPlanet++) {
                    if (positions[planet][dimension] < positions[testPlanet][dimension]) gravityChanges[planet]++;
                    if (positions[planet][dimension] > positions[testPlanet][dimension]) gravityChanges[planet]--;
                }
            }
            for (int planet = 0; planet < 4; planet++) {
                velocities[planet][dimension] += gravityChanges[planet];
                positions[planet][dimension] += velocities[planet][dimension];
                intArray[planet][dimension] = positions[planet][dimension];
            }
        }
        for (int[][] pastLocation : pastLocations) {
            if (Arrays.equals(pastLocation, positions)) System.out.println(counter);
        }
        pastLocations.add(intArray);
    }

    public static void main(String[] args) {
        while (counter < 2270) {
            step();
            counter++;
        }
        step();
    }
}
