package AoC2019;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DaySix {
    public static ArrayList<String> orbited = new ArrayList<>();
    public static ArrayList<String> orbiters = new ArrayList<>();
    public static ArrayList<String> pathToYou;
    public static ArrayList<String> pathToSanta;
    public static int distanceToYou;
    public static int distanceToSanta;
    public static void fileToArray() {
        try {
            int total = 0;
            File file = new File("src\\AoC2019\\daySix.txt");
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String input = scan.nextLine();
                orbited.add(input.substring(0,input.indexOf(")")));
                orbiters.add(input.substring(input.indexOf(")") + 1));
            }
            scan.close();
        }catch (FileNotFoundException error) {
            System.err.println(error);
        }
    }
    public static void explore(String id,ArrayList<String> pastPath, int distanceToCOM){
        pastPath.add(id);
        if (id.equals("SAN")){
            distanceToSanta = distanceToCOM;
            pathToSanta = new ArrayList<String>(pastPath);
        }else if(id.equals("YOU")){
            distanceToYou = distanceToCOM;
            pathToYou = new ArrayList<String>(pastPath);
        }
        for (int i = 0; i < orbited.size(); i++) {
            if (orbited.get(i).equals(id)) explore(orbiters.get(i), new ArrayList<String>(pastPath), distanceToCOM + 1);
        }
    }
    public static int diffInPaths(){
        int total = -1;
        for (int i = 0; i < pathToSanta.size() && i < pathToYou.size(); i++) {
            if (pathToYou.get(i).equals(pathToSanta.get(i))) total++;
        }
        return total;
    }
    public static void main(String[] args) {
        fileToArray();
        explore("COM",new ArrayList<String>(),0);
        System.out.println(distanceToSanta+distanceToYou-(diffInPaths()*2)-2);
    }

}
