import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayFourteen {
    static ArrayList<ArrayList<String>> reagentNames;
    static ArrayList<ArrayList<Long>> reagentNums;
    static ArrayList<String> productNames;
    static ArrayList<Long> productNums;
    static ArrayList<String> requirementNames;
    static ArrayList<Long> requirementNums;

    static void convertToEquations() {
        try {
            reagentNames = new ArrayList<>();
            reagentNums = new ArrayList<>();
            productNames = new ArrayList<>();
            productNums = new ArrayList<>();
            requirementNames = new ArrayList<>();
            requirementNums = new ArrayList<>();
            File file = new File("src\\dayFourteen.txt");
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String equation = scan.nextLine();
                ArrayList<String> reactionNames = new ArrayList<>();
                ArrayList<Long> reactionNums = new ArrayList<>();
                while (equation.contains(",")) {
                    reactionNums.add(Long.parseLong(equation.substring(0, equation.indexOf(" "))));
                    equation = equation.substring(equation.indexOf(" ") + 1);
                    reactionNames.add(equation.substring(0,equation.indexOf(",")));
                    equation = equation.substring(equation.indexOf(",") + 2);
                }
                reactionNums.add(Long.parseLong(equation.substring(0, equation.indexOf(" "))));
                equation = equation.substring(equation.indexOf(" ") + 1);
                reactionNames.add(equation.substring(0,equation.indexOf(" ")));
                equation = equation.substring(equation.indexOf(" ") + 4);
                reagentNames.add(reactionNames);
                reagentNums.add(reactionNums);
                productNums.add(Long.parseLong(equation.substring(0, equation.indexOf(" "))));
                equation = equation.substring(equation.indexOf(" ") + 1);
                productNames.add(equation);
            }
            scan.close();
        } catch (FileNotFoundException error) {
            System.err.println("Whoops!");
        }
    }

    static long oreNeeded() {
        while (!(requirementNames.size() == 1 && requirementNames.get(0).equals("ORE"))) {
            for (int requiredID = 0; requiredID < requirementNames.size(); requiredID++) {
                boolean collectedAll = true;
                for (ArrayList<String> equation : reagentNames){
                    for (String chemical : equation) {
                        if (chemical.equals(requirementNames.get(requiredID))) {
                            collectedAll = false;
                            break;
                        }
                    }
                }
                if (collectedAll) {
                    int reactionID = productNames.indexOf(requirementNames.get(requiredID));
                    long reactionCoefficient = (long) Math.ceil((double)requirementNums.get(requiredID)/productNums.get(reactionID));
                    for (int newReqID = 0; newReqID < reagentNames.get(reactionID).size(); newReqID++) {
                        String newChem = reagentNames.get(reactionID).get(newReqID);
                        long amountOfChem = reagentNums.get(reactionID).get(newReqID) * reactionCoefficient;
                        if (requirementNames.contains(newChem)) {
                            requirementNums.set(requirementNames.indexOf(newChem),requirementNums.get(requirementNames.indexOf(newChem))+amountOfChem);
                        }
                        else {
                            requirementNames.add(newChem);
                            requirementNums.add(amountOfChem);
                        }
                    }
                    requirementNames.remove(requiredID);
                    requirementNums.remove(requiredID);
                    productNums.remove(reactionID);
                    productNames.remove(reactionID);
                    reagentNums.remove(reactionID);
                    reagentNames.remove(reactionID);
                }
            }
        }
        return requirementNums.get(0);
    }

    public static void main(String[] args) {
        long oreNeeds = 0;
        long fuelProduced = 3000000;
        while (oreNeeds < (long) 1000000*1000000) {
            fuelProduced++;
            convertToEquations();
            requirementNames.add("FUEL");
            requirementNums.add(fuelProduced);
            oreNeeds = oreNeeded();
        }
        System.out.println(fuelProduced - 1);
    }
}
