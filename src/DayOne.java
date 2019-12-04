import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DayOne {
    public static void main(String[] args) {
        try {
            int total = 0;
            File file = new File("src\\dayOne.txt");
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                int lastFuel = Integer.parseInt(scan.nextLine());
                int modTotal = -lastFuel;
                while (lastFuel != 0) {
                    int nextFuel = Math.floorDiv(lastFuel, 3) - 2;
                    if (nextFuel < 0) nextFuel = 0;
                    modTotal = modTotal + lastFuel;
                    lastFuel = nextFuel;
                }
                total = total + modTotal;
            }
            System.out.println(total);
            scan.close();
        } catch (FileNotFoundException error) {
            System.err.println(error);
        }
    }
}
