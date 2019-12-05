import java.util.ArrayList;
import java.util.Scanner;

public class DayFive {
    public static int[] convertToArray(String inputList) {
        ArrayList<Integer> list = new ArrayList<>();
        String input = inputList.concat(",");
        while (input.contains(",")) {
            list.add(Integer.parseInt(input.substring(0,input.indexOf(","))));
            input = input.substring(input.indexOf(",") + 1);
        }
        int[] array = new int[list.size()];
        for(int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }
    public static void compute(int[] input) {
        Scanner scan = new Scanner(System.in);
        int parameter1;
        int parameter2;
        int jumpBy;
        int opcodeLocation = 0;
        int[] opcodeArray = intToArray(input[opcodeLocation]);
        int opcodeFunction = opcodeArray[3] * 10 + opcodeArray[4];
        while (opcodeFunction != 99) {
            opcodeArray = intToArray(input[opcodeLocation]);
            opcodeFunction = opcodeArray[3] * 10 + opcodeArray[4];
            if (opcodeFunction == 1) {
                if (opcodeArray[2] == 0) parameter1 = input[input[opcodeLocation + 1]];
                else parameter1 = input[opcodeLocation + 1];
                if (opcodeArray[1] == 0) parameter2 = input[input[opcodeLocation + 2]];
                else parameter2 = input[opcodeLocation + 2];
                input[input[opcodeLocation + 3]] = parameter1 + parameter2;
                jumpBy = 4;
            }
            else if (opcodeFunction == 2) {
                if (opcodeArray[2] == 0) parameter1 = input[input[opcodeLocation + 1]];
                else parameter1 = input[opcodeLocation + 1];
                if (opcodeArray[1] == 0) parameter2 = input[input[opcodeLocation + 2]];
                else parameter2 = input[opcodeLocation + 2];
                input[input[opcodeLocation + 3]] = parameter1 * parameter2;
                jumpBy = 4;
            }
            else if (opcodeFunction == 3) {
                parameter1 = 0;
                parameter2 = 0;
                input[input[opcodeLocation + 1]] = scan.nextInt();
                jumpBy = 2;
            }
            else if (opcodeFunction == 4) {
                if (opcodeArray[2] == 0) parameter1 = input[input[opcodeLocation + 1]];
                else parameter1 = input[opcodeLocation + 1];
                parameter2 = 0;
                System.out.println(parameter1);
                jumpBy = 2;
            }
            else if (opcodeFunction == 5) {
                if (opcodeArray[2] == 0) parameter1 = input[input[opcodeLocation + 1]];
                else parameter1 = input[opcodeLocation + 1];
                if (opcodeArray[1] == 0) parameter2 = input[input[opcodeLocation + 2]];
                else parameter2 = input[opcodeLocation + 2];
                if (parameter1 != 0) {
                    opcodeLocation = parameter2;
                    jumpBy = 0;
                }else {
                    jumpBy = 3;
                }
            }
            else if (opcodeFunction == 6) {
                if (opcodeArray[2] == 0) parameter1 = input[input[opcodeLocation + 1]];
                else parameter1 = input[opcodeLocation + 1];
                if (opcodeArray[1] == 0) parameter2 = input[input[opcodeLocation + 2]];
                else parameter2 = input[opcodeLocation + 2];
                if (parameter1 == 0) {
                    opcodeLocation = parameter2;
                    jumpBy = 0;
                }else {
                    jumpBy = 3;
                }
            }
            else if (opcodeFunction == 7) {
                if (opcodeArray[2] == 0) parameter1 = input[input[opcodeLocation + 1]];
                else parameter1 = input[opcodeLocation + 1];
                if (opcodeArray[1] == 0) parameter2 = input[input[opcodeLocation + 2]];
                else parameter2 = input[opcodeLocation + 2];
                if (parameter1 < parameter2) {
                    input[input[opcodeLocation + 3]] = 1;
                }else {
                    input[input[opcodeLocation + 3]] = 0;
                }
                jumpBy = 4;
            }
            else if (opcodeFunction == 8) {
                if (opcodeArray[2] == 0) parameter1 = input[input[opcodeLocation + 1]];
                else parameter1 = input[opcodeLocation + 1];
                if (opcodeArray[1] == 0) parameter2 = input[input[opcodeLocation + 2]];
                else parameter2 = input[opcodeLocation + 2];
                if (parameter1 == parameter2) {
                    input[input[opcodeLocation + 3]] = 1;
                }else {
                    input[input[opcodeLocation + 3]] = 0;
                }
                jumpBy = 4;
            }
            else if (opcodeFunction == 99) {
                return;
            }
            else {
                System.out.println("Error in opcode" + input[opcodeLocation]);
                jumpBy = 1;
            }
            opcodeLocation = opcodeLocation + jumpBy;
        }
    }

    public static int[] intToArray(int input){
        String integer;
        if (input < 10)integer = "0000"+input;
        else if (input < 100)integer = "000"+input;
        else if (input < 1000)integer = "00"+input;
        else if (input < 10000)integer = "0"+input;
        else integer = ""+input;
        int[] output = new int[5];
        for (int i = 0; i < 5; i++) {
            int digit = Integer.parseInt(integer.substring(i,i+1));
            output[i] = digit;
        }
        return output;
    }

    public static void main(String[] args) {
        compute(convertToArray("3,225,1,225,6,6,1100,1,238,225,104,0,1102,79,14,225,1101,17,42,225,2,74,69,224,1001,224,-5733,224,4,224,1002,223,8,223,101,4,224,224,1,223,224,223,1002,191,83,224,1001,224,-2407,224,4,224,102,8,223,223,101,2,224,224,1,223,224,223,1101,18,64,225,1102,63,22,225,1101,31,91,225,1001,65,26,224,101,-44,224,224,4,224,102,8,223,223,101,3,224,224,1,224,223,223,101,78,13,224,101,-157,224,224,4,224,1002,223,8,223,1001,224,3,224,1,224,223,223,102,87,187,224,101,-4698,224,224,4,224,102,8,223,223,1001,224,4,224,1,223,224,223,1102,79,85,224,101,-6715,224,224,4,224,1002,223,8,223,1001,224,2,224,1,224,223,223,1101,43,46,224,101,-89,224,224,4,224,1002,223,8,223,101,1,224,224,1,223,224,223,1101,54,12,225,1102,29,54,225,1,17,217,224,101,-37,224,224,4,224,102,8,223,223,1001,224,3,224,1,223,224,223,1102,20,53,225,4,223,99,0,0,0,677,0,0,0,0,0,0,0,0,0,0,0,1105,0,99999,1105,227,247,1105,1,99999,1005,227,99999,1005,0,256,1105,1,99999,1106,227,99999,1106,0,265,1105,1,99999,1006,0,99999,1006,227,274,1105,1,99999,1105,1,280,1105,1,99999,1,225,225,225,1101,294,0,0,105,1,0,1105,1,99999,1106,0,300,1105,1,99999,1,225,225,225,1101,314,0,0,106,0,0,1105,1,99999,107,226,226,224,1002,223,2,223,1006,224,329,101,1,223,223,1108,677,226,224,1002,223,2,223,1006,224,344,101,1,223,223,7,677,226,224,102,2,223,223,1006,224,359,101,1,223,223,108,226,226,224,1002,223,2,223,1005,224,374,101,1,223,223,8,226,677,224,1002,223,2,223,1006,224,389,101,1,223,223,1108,226,226,224,102,2,223,223,1006,224,404,101,1,223,223,1007,677,677,224,1002,223,2,223,1006,224,419,101,1,223,223,8,677,677,224,1002,223,2,223,1005,224,434,1001,223,1,223,1008,226,226,224,102,2,223,223,1005,224,449,1001,223,1,223,1008,226,677,224,102,2,223,223,1006,224,464,101,1,223,223,1107,677,677,224,102,2,223,223,1006,224,479,101,1,223,223,107,677,677,224,1002,223,2,223,1005,224,494,1001,223,1,223,1107,226,677,224,1002,223,2,223,1005,224,509,101,1,223,223,1108,226,677,224,102,2,223,223,1006,224,524,101,1,223,223,7,226,226,224,1002,223,2,223,1005,224,539,101,1,223,223,108,677,677,224,1002,223,2,223,1005,224,554,101,1,223,223,8,677,226,224,1002,223,2,223,1005,224,569,1001,223,1,223,1008,677,677,224,102,2,223,223,1006,224,584,101,1,223,223,107,226,677,224,102,2,223,223,1005,224,599,1001,223,1,223,7,226,677,224,102,2,223,223,1005,224,614,101,1,223,223,1007,226,226,224,1002,223,2,223,1005,224,629,101,1,223,223,1107,677,226,224,1002,223,2,223,1006,224,644,101,1,223,223,108,226,677,224,102,2,223,223,1006,224,659,101,1,223,223,1007,677,226,224,102,2,223,223,1006,224,674,101,1,223,223,4,223,99,226"));
        //compute(convertToArray("3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99"));
    }
}
