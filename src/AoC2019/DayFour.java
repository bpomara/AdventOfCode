package AoC2019;

import java.util.ArrayList;

public class DayFour {
    public static int[] intToArray(int input){
        String integer = input+"";
        int[] output = new int[6];
        for (int i = 0; i < 6; i++) {
            int digit = Integer.parseInt(integer.substring(i,i+1));
            output[i] = digit;
        }
        return output;
    }

    public static boolean check(int input) {
        int[] array = intToArray(input);
        ArrayList<Integer> adjacentValues = new ArrayList<>();
        ArrayList<Integer> testedAdjacentValues = new ArrayList<>();
        int lastDigit = 0;
        boolean adjacent = false;
        boolean ascending = true;
        for (int digit : array) {
            if (digit == lastDigit) {
                if (testedAdjacentValues.contains(digit)) {
                    if (adjacentValues.contains(digit)) adjacentValues.remove((Integer) digit);
                }else {
                    adjacentValues.add(digit);
                    testedAdjacentValues.add(digit);
                }
            }
            if (digit < lastDigit) ascending = false;
            lastDigit = digit;
        }
        adjacent = !adjacentValues.isEmpty();
        return (adjacent && ascending);
    }

    public static void main(String[] args) {
        int count = 0;
        for (int i = 168630; i <= 718098; i++){
            if(check(i)) count++;
        }
        System.out.println(count);
    }
}
