import java.util.ArrayList;
import java.util.Arrays;

public class DaySeven {
    static int[] intCode;
    static int[][] intCodes = new int[5][];
    static int[][] possiblePhaseSettings = new int[120][5];
    static int[] opCodeLocations = new int[5];
    static int[] currentPhaseSettings = new int[5];
    static boolean[] hasPhaseSetting = new boolean[5];
    static int lastOutput;
    static boolean running = true;

    public static void reset() {
        running = true;
        hasPhaseSetting = new boolean[5];
        opCodeLocations = new int[5];
        Arrays.fill(intCodes,intCode);
    }

    public static void convertToArray(String inputList) {
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
        intCode = array;

    }

    public static int compute(int ampNum, int input) {
        int[] code = intCodes[ampNum].clone();
        int phaseSetting = currentPhaseSettings[ampNum];
        int parameter1;
        int parameter2;
        int jumpBy;
        int opcodeLocation = opCodeLocations[ampNum];
        int[] opcodeArray = intToArray(code[opcodeLocation]);
        int opcodeFunction = opcodeArray[3] * 10 + opcodeArray[4];
        while (opcodeFunction != 99) {
            opcodeArray = intToArray(code[opcodeLocation]);
            opcodeFunction = opcodeArray[3] * 10 + opcodeArray[4];
            if (opcodeFunction == 1) {
                if (opcodeArray[2] == 0) parameter1 = code[code[opcodeLocation + 1]];
                else parameter1 = code[opcodeLocation + 1];
                if (opcodeArray[1] == 0) parameter2 = code[code[opcodeLocation + 2]];
                else parameter2 = code[opcodeLocation + 2];
                code[code[opcodeLocation + 3]] = parameter1 + parameter2;
                jumpBy = 4;
            }
            else if (opcodeFunction == 2) {
                if (opcodeArray[2] == 0) parameter1 = code[code[opcodeLocation + 1]];
                else parameter1 = code[opcodeLocation + 1];
                if (opcodeArray[1] == 0) parameter2 = code[code[opcodeLocation + 2]];
                else parameter2 = code[opcodeLocation + 2];
                code[code[opcodeLocation + 3]] = parameter1 * parameter2;
                jumpBy = 4;
            }
            else if (opcodeFunction == 3) {
                if (hasPhaseSetting[ampNum]) {
                    code[code[opcodeLocation + 1]] = input;
                }
                else {
                    code[code[opcodeLocation + 1]] = phaseSetting;
                    hasPhaseSetting[ampNum] = true;
                }
                jumpBy = 2;
            }
            else if (opcodeFunction == 4) {
                if (opcodeArray[2] == 0) parameter1 = code[code[opcodeLocation + 1]];
                else parameter1 = code[opcodeLocation + 1];
                if (ampNum == 4) {
                    lastOutput = parameter1;
                }
                intCodes[ampNum] = code;
                opCodeLocations[ampNum] = opcodeLocation + 2;
                return parameter1;
            }
            else if (opcodeFunction == 5) {
                if (opcodeArray[2] == 0) parameter1 = code[code[opcodeLocation + 1]];
                else parameter1 = code[opcodeLocation + 1];
                if (opcodeArray[1] == 0) parameter2 = code[code[opcodeLocation + 2]];
                else parameter2 = code[opcodeLocation + 2];
                if (parameter1 != 0) {
                    opcodeLocation = parameter2;
                    jumpBy = 0;
                }else {
                    jumpBy = 3;
                }
            }
            else if (opcodeFunction == 6) {
                if (opcodeArray[2] == 0) parameter1 = code[code[opcodeLocation + 1]];
                else parameter1 = code[opcodeLocation + 1];
                if (opcodeArray[1] == 0) parameter2 = code[code[opcodeLocation + 2]];
                else parameter2 = code[opcodeLocation + 2];
                if (parameter1 == 0) {
                    opcodeLocation = parameter2;
                    jumpBy = 0;
                }else {
                    jumpBy = 3;
                }
            }
            else if (opcodeFunction == 7) {
                if (opcodeArray[2] == 0) parameter1 = code[code[opcodeLocation + 1]];
                else parameter1 = code[opcodeLocation + 1];
                if (opcodeArray[1] == 0) parameter2 = code[code[opcodeLocation + 2]];
                else parameter2 = code[opcodeLocation + 2];
                if (parameter1 < parameter2) {
                    code[code[opcodeLocation + 3]] = 1;
                }else {
                    code[code[opcodeLocation + 3]] = 0;
                }
                jumpBy = 4;
            }
            else if (opcodeFunction == 8) {
                if (opcodeArray[2] == 0) parameter1 = code[code[opcodeLocation + 1]];
                else parameter1 = code[opcodeLocation + 1];
                if (opcodeArray[1] == 0) parameter2 = code[code[opcodeLocation + 2]];
                else parameter2 = code[opcodeLocation + 2];
                if (parameter1 == parameter2) {
                    code[code[opcodeLocation + 3]] = 1;
                }else {
                    code[code[opcodeLocation + 3]] = 0;
                }
                jumpBy = 4;
            }
            else if (opcodeFunction == 99) {
                running = false;
                jumpBy = 0;
            }
            else {
                System.out.println("Error in opcode" + code[opcodeLocation]);
                jumpBy = 1;
            }
            opcodeLocation = opcodeLocation + jumpBy;
        }
        running = false;
        return lastOutput;
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

    public static void createPossibleSettings() {
        int[] something = {0,0,0,-1};
        for (int j = 0; j < 120; j++) {
            something[something.length - 1]++;
            for (int i = 1; i <= something.length; i++) {
                if (something[something.length - i] > i) {
                    something[something.length - i] = 0;
                    something[something.length - (i + 1)]++;
                }
            }
            int[] possiblePhaseSetting = new int[something.length + 1];
            ArrayList<Integer> possibleInts = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                possibleInts.add(i+5);
            }
            for (int i = 0; i < something.length; i++) {
                possiblePhaseSetting[i] = possibleInts.get(something[i]);
                possibleInts.remove(something[i]);
            }
            possiblePhaseSetting[possiblePhaseSetting.length - 1] = possibleInts.get(0);
            possiblePhaseSettings[j] = possiblePhaseSetting;
        }
    }

    public static void main(String[] args) {
        convertToArray("3,8,1001,8,10,8,105,1,0,0,21,34,51,64,81,102,183,264,345,426,99999,3,9,102,2,9,9,1001,9,4,9,4,9,99,3,9,101,4,9,9,102,5,9,9,1001,9,2,9,4,9,99,3,9,101,3,9,9,1002,9,5,9,4,9,99,3,9,102,3,9,9,101,3,9,9,1002,9,4,9,4,9,99,3,9,1002,9,3,9,1001,9,5,9,1002,9,5,9,101,3,9,9,4,9,99,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,1,9,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,99,3,9,101,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,101,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,99,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,1,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,99");
        createPossibleSettings();
        ArrayList<Integer> outputs = new ArrayList<>();
        for (int[] phaseSettings : possiblePhaseSettings) {
            currentPhaseSettings = phaseSettings;
            reset();
            int i = 0;
            int input = 0;
            while (running) {
                input = compute(i % 5, input);
                i++;
            }
            //System.out.println(input + " " + phaseSettings[0] + " " + currentPhaseSettings[0]);
            outputs.add(input);
        }
        int largestOutput = outputs.get(0);
        for (int output : outputs) {
            if (output > largestOutput) largestOutput = output;
        }
        System.out.println(largestOutput);
    }

}


