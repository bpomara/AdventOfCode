import java.util.ArrayList;

public class DayNineteen {
    public static long[] program;
    public static int[][] map = new int[10000][10000];

    public static long[] convertToArray(String inputList) {
        ArrayList<Long> list = new ArrayList<>();
        String input = inputList.concat(",");
        while (input.contains(",")) {
            list.add(Long.parseLong(input.substring(0,input.indexOf(","))));
            input = input.substring(input.indexOf(",") + 1);
        }
        long[] array = new long[list.size()];
        for(int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static int compute(int x, int y) {
        int output = -1;
        boolean readX = false;
        int coordinate;
        long parameter1;
        long parameter2;
        int jumpBy;
        int opcodeLocation = 0;
        int relativeBase = 0;
        long[] input = new long[1000];
        System.arraycopy(program, 0, input, 0, program.length);
        int[] opcodeArray = intToArray(input[opcodeLocation]);
        int opcodeFunction = opcodeArray[3] * 10 + opcodeArray[4];
        while (opcodeFunction != 99) {
            opcodeArray = intToArray(input[opcodeLocation]);
            opcodeFunction = opcodeArray[3] * 10 + opcodeArray[4];
            if (opcodeFunction == 1) {
                if (opcodeArray[2] == 0) parameter1 = input[(int) input[opcodeLocation + 1]];
                else if (opcodeArray[2] == 1) parameter1 = input[opcodeLocation + 1];
                else parameter1 = input[relativeBase+(int) input[opcodeLocation+1]];
                if (opcodeArray[1] == 0) parameter2 = input[(int) input[opcodeLocation + 2]];
                else if (opcodeArray[1] == 1) parameter2 = input[opcodeLocation + 2];
                else parameter2 = input[relativeBase+(int) input[opcodeLocation+2]];
                if (opcodeArray[0] == 0)input[(int) input[opcodeLocation + 3]] = parameter1 + parameter2;
                else input[relativeBase+(int) input[opcodeLocation + 3]] = parameter1 + parameter2;
                jumpBy = 4;
            }
            else if (opcodeFunction == 2) {
                if (opcodeArray[2] == 0) parameter1 = input[(int) input[opcodeLocation + 1]];
                else if (opcodeArray[2] == 1) parameter1 = input[opcodeLocation + 1];
                else parameter1 = input[relativeBase+(int) input[opcodeLocation+1]];
                if (opcodeArray[1] == 0) parameter2 = input[(int) input[opcodeLocation + 2]];
                else if (opcodeArray[1] == 1) parameter2 = input[opcodeLocation + 2];
                else parameter2 = input[relativeBase+(int) input[opcodeLocation+2]];
                if (opcodeArray[0] == 0)input[(int) input[opcodeLocation + 3]] = parameter1 * parameter2;
                else input[relativeBase+(int) input[opcodeLocation + 3]] = parameter1 * parameter2;
                jumpBy = 4;
            }
            else if (opcodeFunction == 3) {
                if (readX) coordinate = y;
                else {
                    coordinate = x;
                    readX = true;
                }
                if (opcodeArray[2] == 0) input[(int) input[opcodeLocation + 1]] = coordinate;
                else input[relativeBase + (int) input[opcodeLocation + 1]] = coordinate;
                jumpBy = 2;
            }
            else if (opcodeFunction == 4) {
                if (opcodeArray[2] == 0) parameter1 = input[(int) input[opcodeLocation + 1]];
                else if (opcodeArray[2] == 1) parameter1 = input[opcodeLocation + 1];
                else parameter1 = input[relativeBase+(int) input[opcodeLocation+1]];
                output = (int) parameter1;
                jumpBy = 2;
            }
            else if (opcodeFunction == 5) {
                if (opcodeArray[2] == 0) parameter1 = input[(int) input[opcodeLocation + 1]];
                else if (opcodeArray[2] == 1) parameter1 = input[opcodeLocation + 1];
                else parameter1 = input[relativeBase+(int) input[opcodeLocation+1]];
                if (opcodeArray[1] == 0) parameter2 = input[(int) input[opcodeLocation + 2]];
                else if (opcodeArray[1] == 1) parameter2 = input[opcodeLocation + 2];
                else parameter2 = input[relativeBase+(int) input[opcodeLocation+2]];
                if (parameter1 != 0) {
                    opcodeLocation = (int) parameter2;
                    jumpBy = 0;
                }else {
                    jumpBy = 3;
                }
            }
            else if (opcodeFunction == 6) {
                if (opcodeArray[2] == 0) parameter1 = input[(int) input[opcodeLocation + 1]];
                else if (opcodeArray[2] == 1) parameter1 = input[opcodeLocation + 1];
                else parameter1 = input[relativeBase+(int) input[opcodeLocation+1]];
                if (opcodeArray[1] == 0) parameter2 = input[(int) input[opcodeLocation + 2]];
                else if (opcodeArray[1] == 1) parameter2 = input[opcodeLocation + 2];
                else parameter2 = input[relativeBase+(int) input[opcodeLocation+2]];
                if (parameter1 == 0) {
                    opcodeLocation = (int) parameter2;
                    jumpBy = 0;
                }else {
                    jumpBy = 3;
                }
            }
            else if (opcodeFunction == 7) {
                if (opcodeArray[2] == 0) parameter1 = input[(int) input[opcodeLocation + 1]];
                else if (opcodeArray[2] == 1) parameter1 = input[opcodeLocation + 1];
                else parameter1 = input[relativeBase+(int) input[opcodeLocation+1]];
                if (opcodeArray[1] == 0) parameter2 = input[(int) input[opcodeLocation + 2]];
                else if (opcodeArray[1] == 1) parameter2 = input[opcodeLocation + 2];
                else parameter2 = input[relativeBase+(int) input[opcodeLocation+2]];
                if (parameter1 < parameter2) {
                    if (opcodeArray[0] == 0) input[(int) input[opcodeLocation + 3]] = 1;
                    else input[relativeBase+(int) input[opcodeLocation + 3]] = 1;
                }else {
                    if (opcodeArray[0] == 0) input[(int) input[opcodeLocation + 3]] = 0;
                    else input[relativeBase+(int) input[opcodeLocation + 3]] = 0;
                }
                jumpBy = 4;
            }
            else if (opcodeFunction == 8) {
                if (opcodeArray[2] == 0) parameter1 = input[(int) input[opcodeLocation + 1]];
                else if (opcodeArray[2] == 1) parameter1 = input[opcodeLocation + 1];
                else parameter1 = input[relativeBase+(int) input[opcodeLocation+1]];
                if (opcodeArray[1] == 0) parameter2 = input[(int) input[opcodeLocation + 2]];
                else if (opcodeArray[1] == 1) parameter2 = input[opcodeLocation + 2];
                else parameter2 = input[relativeBase+(int) input[opcodeLocation+2]];
                if (parameter1 == parameter2) {
                    if (opcodeArray[0] == 0)input[(int) input[opcodeLocation + 3]] = 1;
                    else input[relativeBase+(int) input[opcodeLocation + 3]] = 1;
                }else {
                    if (opcodeArray[0] == 0)input[(int) input[opcodeLocation + 3]] = 0;
                    else input[relativeBase+(int) input[opcodeLocation + 3]] = 0;
                }
                jumpBy = 4;
            }
            else if (opcodeFunction == 9) {
                if (opcodeArray[2] == 0) parameter1 = input[(int) input[opcodeLocation + 1]];
                else if (opcodeArray[2] == 1) parameter1 = input[opcodeLocation + 1];
                else parameter1 = input[relativeBase+(int) input[opcodeLocation+1]];
                relativeBase += parameter1;
                jumpBy = 2;
            }
            else if (opcodeFunction == 99) {
                jumpBy =0;
            }
            else {
                System.out.println("Error in opcode" + input[opcodeLocation]);
                jumpBy = 1;
            }
            opcodeLocation = opcodeLocation + jumpBy;
        }
        return output;
    }

    public static int[] intToArray(long input){
        String integer;
        if (input < 10)integer = "0000"+input;
        else if (input < 100)integer = "000"+input;
        else if (input < 1000)integer = "00"+input;
        else if (input < 10000)integer = "0"+input;
        else integer = ""+input;
        int[] output = new int[5];
        for (int i = 0; i < 5; i++) {
            int digit = (int) Long.parseLong(integer.substring(i,i+1));
            output[i] = digit;
        }
        return output;
    }

    public static void main(String[] args) {
        int answer = 0;
        program = convertToArray("109,424,203,1,21101,11,0,0,1105,1,282,21101,0,18,0,1106,0,259,1202,1,1,221,203,1,21101,0,31,0,1105,1,282,21102,1,38,0,1106,0,259,20101,0,23,2,22102,1,1,3,21101,1,0,1,21101,0,57,0,1106,0,303,1202,1,1,222,21002,221,1,3,21001,221,0,2,21102,1,259,1,21101,80,0,0,1105,1,225,21102,1,117,2,21102,1,91,0,1105,1,303,1202,1,1,223,20102,1,222,4,21101,0,259,3,21101,0,225,2,21101,225,0,1,21101,118,0,0,1105,1,225,21001,222,0,3,21101,20,0,2,21102,1,133,0,1105,1,303,21202,1,-1,1,22001,223,1,1,21101,0,148,0,1106,0,259,2101,0,1,223,20102,1,221,4,21001,222,0,3,21101,0,16,2,1001,132,-2,224,1002,224,2,224,1001,224,3,224,1002,132,-1,132,1,224,132,224,21001,224,1,1,21102,195,1,0,105,1,108,20207,1,223,2,21002,23,1,1,21102,-1,1,3,21101,0,214,0,1105,1,303,22101,1,1,1,204,1,99,0,0,0,0,109,5,1201,-4,0,249,22102,1,-3,1,22101,0,-2,2,21202,-1,1,3,21102,1,250,0,1106,0,225,22102,1,1,-4,109,-5,2105,1,0,109,3,22107,0,-2,-1,21202,-1,2,-1,21201,-1,-1,-1,22202,-1,-2,-2,109,-3,2106,0,0,109,3,21207,-2,0,-1,1206,-1,294,104,0,99,21202,-2,1,-2,109,-3,2105,1,0,109,5,22207,-3,-4,-1,1206,-1,346,22201,-4,-3,-4,21202,-3,-1,-1,22201,-4,-1,2,21202,2,-1,-1,22201,-4,-1,1,21201,-2,0,3,21101,343,0,0,1105,1,303,1105,1,415,22207,-2,-3,-1,1206,-1,387,22201,-3,-2,-3,21202,-2,-1,-1,22201,-3,-1,3,21202,3,-1,-1,22201,-3,-1,2,21201,-4,0,1,21101,0,384,0,1105,1,303,1105,1,415,21202,-4,-1,-4,22201,-4,-3,-4,22202,-3,-2,-2,22202,-2,-4,-4,22202,-3,-2,-3,21202,-4,-1,-2,22201,-3,-2,1,22101,0,1,-4,109,-5,2105,1,0");
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                map[y][x] = compute(x,y);
            }
            System.out.println("A" + y);
        }
        for (int y = 0; y < map.length - 99; y++) {
            for (int x = 0; x < map[0].length - 99; x++) {
                if (map[y][x] == 1 && map[y+99][x] == 1 && map[y][x+99] == 1){
                    answer = x*10000+y;
                    System.out.println(answer);
                    return;
                }
            }
            System.out.println("B" + y);
        }
    }
}
