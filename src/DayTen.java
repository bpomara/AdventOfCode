import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayTen {
    public static int[][] listOfAsteroids;

    public static void fileToArray() {
        try {
            ArrayList<Integer> xValues = new ArrayList<>();
            ArrayList<Integer> yValues = new ArrayList<>();
            File file = new File("src\\dayTen.txt");
            Scanner scan = new Scanner(file);
            for (int y = 0; scan.hasNextLine(); y++) {
                String input = scan.nextLine();
                for (int x = 0; x < input.length(); x++){
                    if (input.charAt(x) == '#') {
                        xValues.add(x);
                        yValues.add(y);
                    }
                }
            }
            scan.close();
            listOfAsteroids = new int[xValues.size()][2];
            for (int i = 0; i < xValues.size(); i++) {
                listOfAsteroids[i][0] = xValues.get(i);
                listOfAsteroids[i][1] = yValues.get(i);
            }
        }catch (FileNotFoundException error) {
            System.err.println("Whoops! File not found.");
        }
    }

    public static int findNumInSight(int x, int y) {
        int numInSight = 0;
        boolean gotTop = false;
        boolean gotBottom = false;
        ArrayList<Float> forwardSlopes = new ArrayList<>();
        ArrayList<Float> backSlopes = new ArrayList<>();
        for (int[] asteroid : listOfAsteroids) {
            if (asteroid[0] == x) {
                if (asteroid[1] > y && !gotTop) {
                    gotTop = true;
                    numInSight++;
                }
                else if (asteroid[1] < y && !gotBottom) {
                    gotBottom = true;
                    numInSight++;
                }
            }
            else if (asteroid[0] > x) {
                float slope = (float) (asteroid[1] - y)/(asteroid[0]-x);
                if (!forwardSlopes.contains(slope)){
                    forwardSlopes.add(slope);
                    numInSight++;
                }
            }
            else {
                float slope = (float) (asteroid[1] - y)/(asteroid[0]-x);
                if (!backSlopes.contains(slope)){
                    backSlopes.add(slope);
                    numInSight++;
                }
            }
        }
        return numInSight;
    }

    public static void destroyAsteroids(int x, int y, int target) {
        ArrayList<Double[]> tops = new ArrayList<>();
        ArrayList<Double[]> bottoms= new ArrayList<>();
        ArrayList<Double[]> forwardSlopes = new ArrayList<>();
        ArrayList<Double[]> backSlopes = new ArrayList<>();
        for (int[] asteroid : listOfAsteroids) {
            if (asteroid[0] == x) {
                if (asteroid[1] < y) {
                    tops.add(new Double[]{(double) asteroid[0], (double) asteroid[1]});
                }
                else if (asteroid[1] > y) {
                    bottoms.add(new Double[]{(double) asteroid[0], (double) asteroid[1]});
                }
            }
            else if (asteroid[0] > x) {
                double slope = (double) -(asteroid[1] - y)/(asteroid[0]-x);
                double distance = Math.sqrt((asteroid[1]-y)*(asteroid[1]-y)+(asteroid[0]-x)*(asteroid[0]-x));
                forwardSlopes.add(new Double[] {(double) asteroid[0],(double) asteroid[1], slope, distance});
            }
            else {
                double slope = (double) -(asteroid[1] - y)/(asteroid[0]-x);
                double distance = Math.sqrt((asteroid[1]-y)*(asteroid[1]-y)*(asteroid[0]-x)*(asteroid[0]-x));
                backSlopes.add(new Double[] {(double) asteroid[0],(double) asteroid[1], slope, distance});
            }
        }
        int removed = 1;
        while (!(tops.isEmpty() && bottoms.isEmpty() && forwardSlopes.isEmpty() && backSlopes.isEmpty())) {
            Double[] closestAsteroid = new Double[] {0.,-1.};
            int closestAsteroidId = -1;
            for (int i = 0; i < tops.size(); i++) {
                if (tops.get(i)[1] > closestAsteroid [1]) {
                    closestAsteroid = tops.get(i);
                    closestAsteroidId = i;
                }
            }
            if (closestAsteroidId > -1) {
                if (removed == target) {
                    System.out.println((int) (100.*closestAsteroid[0] + closestAsteroid[1]));
                }
                tops.remove(closestAsteroidId);
                removed++;
            }
            boolean isNextAsteroid = true;
            Double[] lastAsteroid = new Double[] {0.,0.,1000.};
            ArrayList<Double> blocked = new ArrayList<>();
            while (isNextAsteroid) {
                closestAsteroid = new Double[]{0., 0., -1000.};
                closestAsteroidId = -1;
                for (int i = 0; i < forwardSlopes.size(); i++) {
                    boolean isBlocked = false;
                    for (Double blockedSlope : blocked) {
                        if (blockedSlope == (double) forwardSlopes.get(i)[2]) {
                            isBlocked = true;
                            break;
                        }
                    }
                    if (!isBlocked) {
                        if (forwardSlopes.get(i)[2] < lastAsteroid[2]) {
                            if (forwardSlopes.get(i)[2] > closestAsteroid[2]) {
                                closestAsteroid = forwardSlopes.get(i);
                                closestAsteroidId = i;
                            } else if (forwardSlopes.get(i)[2].equals(closestAsteroid[2])) {
                                if (forwardSlopes.get(i)[3] < closestAsteroid[3]) {
                                    closestAsteroid = forwardSlopes.get(i);
                                    closestAsteroidId = i;
                                }
                            }
                        }
                    }
                }
                if (closestAsteroidId > -1) {
                    if (removed == target) {
                        System.out.println(((int) (100.*closestAsteroid[0] + closestAsteroid[1])));
                    }
                    forwardSlopes.remove(closestAsteroidId);
                    blocked.add(closestAsteroid[2]);
                    removed++;
                } else isNextAsteroid = false;
            }
            closestAsteroid = new Double[]{0., 1000.};
            closestAsteroidId = -1;
            for (int i = 0; i < bottoms.size(); i++) {
                if (bottoms.get(i)[1] < closestAsteroid[1]) {
                    closestAsteroid = bottoms.get(i);
                    closestAsteroidId = i;
                }
            }
            if (closestAsteroidId > -1) {
                if (removed == target) {
                    System.out.println((int) (100.*closestAsteroid[0] + closestAsteroid[1]));

                }
                bottoms.remove(closestAsteroidId);
                removed++;
            }

            isNextAsteroid = true;
            lastAsteroid = new Double[]{0., 0., 1000.};
            blocked = new ArrayList<>();
            while (isNextAsteroid) {
                closestAsteroid = new Double[]{0., 0., -1000.};
                closestAsteroidId = -1;
                for (int i = 0; i < backSlopes.size(); i++) {
                    boolean isBlocked = false;
                    for (Double blockedSlope : blocked) {
                        if (blockedSlope == (double) backSlopes.get(i)[2]) {
                            isBlocked = true;
                            break;
                        }
                    }
                    if (!isBlocked) {
                        if (backSlopes.get(i)[2] < lastAsteroid[2]) {
                            if (backSlopes.get(i)[2] > closestAsteroid[2]) {
                                closestAsteroid = backSlopes.get(i);
                                closestAsteroidId = i;
                            } else if (backSlopes.get(i)[2].equals(closestAsteroid[2])) {
                                if (backSlopes.get(i)[3] < closestAsteroid[3]) {
                                    closestAsteroid = backSlopes.get(i);
                                    closestAsteroidId = i;
                                }
                            }
                        }
                    }
                }
                if (closestAsteroidId > -1) {
                    if (removed == target) {
                        System.out.println(((int) (100.*closestAsteroid[0] + closestAsteroid[1])));
                    }
                    blocked.add(closestAsteroid[2]);
                    backSlopes.remove(closestAsteroidId);
                    removed++;
                } else isNextAsteroid = false;
            }

        }
    }

    public static void main(String[] args) {
        fileToArray();
        ArrayList<Integer> listOfSights = new ArrayList<>();
        for (int[] asteroid : listOfAsteroids) {
            listOfSights.add(findNumInSight(asteroid[0],asteroid[1]));
            if (findNumInSight(asteroid[0],asteroid[1]) == 334) System.out.println(asteroid[0] + " " + asteroid[1]);
        }
        int largestNum = 0;
        for (int num : listOfSights) {
            if (num > largestNum) largestNum = num;
        }
        System.out.println(largestNum);
        destroyAsteroids(23, 20, 200);
    }
}
