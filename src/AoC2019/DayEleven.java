package AoC2019;

import java.util.ArrayList;
import java.util.Scanner;

public class DayEleven {
    public static int WIDTH = 100;
    public static int HEIGHT = 100;
    public static boolean[][] testHull = new boolean[WIDTH][HEIGHT];
    public static int[][] hull = new int[WIDTH][HEIGHT];

    public static long[] convertToArray(String inputList) {
        ArrayList<Long> list = new ArrayList<>();
        String input = inputList.concat(",");
        while (input.contains(",")) {
            list.add(Long.parseLong(input.substring(0,input.indexOf(","))));
            input = input.substring(input.indexOf(",") + 1);
        }
        long[] array = new long[1200];
        for(int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static void compute(long[] input) {
        int[] robot = {WIDTH/2, HEIGHT/2};
        testHull[robot[0]][robot[1]] = true;
        hull[robot[0]][robot[1]] = 1;
        boolean paint = true;
        int direction = 0;
        Scanner scan = new Scanner(System.in);
        long parameter1;
        long parameter2;
        int jumpBy;
        int opcodeLocation = 0;
        int relativeBase = 0;
        int[] opcodeArray = intToArray(input[opcodeLocation]);
        int opcodeFunction = opcodeArray[3] * 10 + opcodeArray[4];
        while (opcodeFunction != 99) {
            //System.out.println(input[opcodeLocation]);
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
                if (opcodeArray[2] == 0) input[(int) input[opcodeLocation + 1]] = hull[robot[0]][robot[1]];
                else input[relativeBase + (int) input[opcodeLocation + 1]] = hull[robot[0]][robot[1]];
                jumpBy = 2;
            }
            else if (opcodeFunction == 4) {
                if (opcodeArray[2] == 0) parameter1 = input[(int) input[opcodeLocation + 1]];
                else if (opcodeArray[2] == 1) parameter1 = input[opcodeLocation + 1];
                else parameter1 = input[relativeBase+(int) input[opcodeLocation+1]];
                if (paint) {
                    hull[robot[0]][robot[1]] = (int) parameter1;
                    testHull[robot[0]][robot[1]] = true;
                }else {
                    switch (direction) {
                        case 0:
                            if (parameter1 == 0) {
                                direction = 1;
                                robot[0]--;
                            }else if (parameter1 == 1){
                                direction = 3;
                                robot[0]++;
                            }else {
                                System.err.println("Whoops!");
                            }
                            break;
                        case 1:
                            if (parameter1 == 0) {
                                direction = 2;
                                robot[1]--;
                            }else if (parameter1 == 1){
                                direction = 0;
                                robot[1]++;
                            }else {
                                System.err.println("Whoops!");
                            }
                            break;
                        case 2:
                            if (parameter1 == 0) {
                                direction = 3;
                                robot[0]++;
                            }else if (parameter1 == 1){
                                direction = 1;
                                robot[0]--;
                            }else {
                                System.err.println("Whoops!");
                            }
                            break;
                        case 3:
                            if (parameter1 == 0) {
                                direction = 0;
                                robot[1]++;
                            }else if (parameter1 == 1){
                                direction = 2;
                                robot[1]--;
                            }else {
                                System.err.println("Whoops!");
                            }
                            break;
                        default:
                            System.err.println("Whoops!");
                    }

                }
                paint = ! paint;
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
                return;
            }
            else {
                System.out.println("Error in opcode" + input[opcodeLocation]);
                jumpBy = 1;
            }
            opcodeLocation = opcodeLocation + jumpBy;
        }
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
        int counter = 0;
        compute(convertToArray("3,8,1005,8,330,1106,0,11,0,0,0,104,1,104,0,3,8,102,-1,8,10,1001,10,1,10,4,10,108,0,8,10,4,10,1001,8,0,28,1,1103,17,10,1006,0,99,1006,0,91,1,102,7,10,3,8,1002,8,-1,10,101,1,10,10,4,10,108,1,8,10,4,10,1002,8,1,64,3,8,102,-1,8,10,1001,10,1,10,4,10,108,0,8,10,4,10,102,1,8,86,2,4,0,10,1006,0,62,2,1106,13,10,3,8,1002,8,-1,10,1001,10,1,10,4,10,1008,8,0,10,4,10,101,0,8,120,1,1109,1,10,1,105,5,10,3,8,102,-1,8,10,1001,10,1,10,4,10,108,1,8,10,4,10,1002,8,1,149,1,108,7,10,1006,0,40,1,6,0,10,2,8,9,10,3,8,102,-1,8,10,1001,10,1,10,4,10,1008,8,1,10,4,10,1002,8,1,187,1,1105,10,10,3,8,102,-1,8,10,1001,10,1,10,4,10,1008,8,1,10,4,10,1002,8,1,213,1006,0,65,1006,0,89,1,1003,14,10,3,8,102,-1,8,10,1001,10,1,10,4,10,108,0,8,10,4,10,102,1,8,244,2,1106,14,10,1006,0,13,3,8,102,-1,8,10,1001,10,1,10,4,10,108,0,8,10,4,10,1001,8,0,273,3,8,1002,8,-1,10,1001,10,1,10,4,10,108,1,8,10,4,10,1001,8,0,295,1,104,4,10,2,108,20,10,1006,0,94,1006,0,9,101,1,9,9,1007,9,998,10,1005,10,15,99,109,652,104,0,104,1,21102,937268450196,1,1,21102,1,347,0,1106,0,451,21101,387512636308,0,1,21102,358,1,0,1105,1,451,3,10,104,0,104,1,3,10,104,0,104,0,3,10,104,0,104,1,3,10,104,0,104,1,3,10,104,0,104,0,3,10,104,0,104,1,21101,0,97751428099,1,21102,1,405,0,1105,1,451,21102,1,179355806811,1,21101,416,0,0,1106,0,451,3,10,104,0,104,0,3,10,104,0,104,0,21102,1,868389643008,1,21102,439,1,0,1105,1,451,21102,1,709475853160,1,21102,450,1,0,1105,1,451,99,109,2,22102,1,-1,1,21101,0,40,2,21101,482,0,3,21102,1,472,0,1105,1,515,109,-2,2106,0,0,0,1,0,0,1,109,2,3,10,204,-1,1001,477,478,493,4,0,1001,477,1,477,108,4,477,10,1006,10,509,1101,0,0,477,109,-2,2105,1,0,0,109,4,2101,0,-1,514,1207,-3,0,10,1006,10,532,21101,0,0,-3,21202,-3,1,1,22101,0,-2,2,21101,1,0,3,21101,0,551,0,1105,1,556,109,-4,2106,0,0,109,5,1207,-3,1,10,1006,10,579,2207,-4,-2,10,1006,10,579,22102,1,-4,-4,1105,1,647,21201,-4,0,1,21201,-3,-1,2,21202,-2,2,3,21101,0,598,0,1106,0,556,22101,0,1,-4,21102,1,1,-1,2207,-4,-2,10,1006,10,617,21101,0,0,-1,22202,-2,-1,-2,2107,0,-3,10,1006,10,639,22102,1,-1,1,21102,1,639,0,105,1,514,21202,-2,-1,-2,22201,-4,-2,-4,109,-5,2105,1,0"));
        for (boolean[] list : testHull) {
            for (boolean painted : list) {
                if (painted) counter++;
            }
        }
        //System.out.println(counter);
        for (int i = 0; i < WIDTH; i++){
            for (int j = 0; j < HEIGHT; j++){
                if (hull[i][j] == 0) System.out.print("\u001B[41m" + " " + "\u001B[0m");
                if (hull[i][j] == 1) System.out.print("\u001B[42m" + " " + "\u001B[0m");
            }
            System.out.println();
        }
    }
}
