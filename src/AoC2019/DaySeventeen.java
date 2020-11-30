package AoC2019;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DaySeventeen {
    public static char[][] fileToArray() {
        char[][] output = new char[37][61];
        try {
            File file = new File("src\\AoC2019\\daySeventeen.txt");
            Scanner scan = new Scanner(file);
            for (int y = 0; scan.hasNextLine(); y++) {
                String line = scan.nextLine();
                output[y] = line.toCharArray();
            }
            scan.close();
        }catch (FileNotFoundException error) {
            System.err.println("Whoops! File not found.");
        }
        return output;
    }

    public static String planPath(char[][] map) {
        String path = "";
        int direction = 0;
        int distance = 0;
        boolean running = true;
        int[] robot = new int[2];
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++){
                if (map[y][x] == '^'){
                    robot[0] = x;
                    robot[1] = y;
                }
            }
        }
        while (running) {
            switch (direction) {
                case 0:
                    if (map[robot[1] - 1][robot[0]] == '#') {
                        distance++;
                        robot[1]--;
                    } else if (map[robot[1]][robot[0] - 1] == '#') {
                        if (distance > 0) path = path.concat(distance + ",");
                        path = path.concat("L,");
                        direction = 3;
                        distance = 0;
                    } else if (map[robot[1]][robot[0] + 1] == '#') {
                        if (distance > 0) path = path.concat(distance + ",");
                        path = path.concat("R,");
                        direction = 1;
                        distance = 0;
                    } else {
                        if (distance > 0) path = path.concat(String.valueOf(distance));
                        running = false;
                    }
                    break;
                case 1:
                    if (map[robot[1]][robot[0] + 1] == '#') {
                        distance++;
                        robot[0]++;
                    } else if (map[robot[1] - 1][robot[0]] == '#') {
                        if (distance > 0) path = path.concat(distance + ",");
                        path = path.concat("L,");
                        direction = 0;
                        distance = 0;
                    } else if (map[robot[1] + 1][robot[0]] == '#') {
                        if (distance > 0) path = path.concat(distance + ",");
                        path = path.concat("R,");
                        direction = 2;
                        distance = 0;
                    } else {
                        if (distance > 0) path = path.concat(String.valueOf(distance));
                        running = false;
                    }
                    break;
                case 2:
                    if (map[robot[1] + 1][robot[0]] == '#') {
                        distance++;
                        robot[1]++;
                    } else if (map[robot[1]][robot[0] + 1] == '#') {
                        if (distance > 0) path = path.concat(distance + ",");
                        path = path.concat("L,");
                        direction = 1;
                        distance = 0;
                    } else if (map[robot[1]][robot[0] - 1] == '#') {
                        if (distance > 0) path = path.concat(distance + ",");
                        path = path.concat("R,");
                        direction = 3;
                        distance = 0;
                    } else {
                        if (distance > 0) path = path.concat(String.valueOf(distance));
                        running = false;
                    }
                    break;
                case 3:
                    if (map[robot[1]][robot[0] - 1] == '#') {
                        distance++;
                        robot[0]--;
                    } else if (map[robot[1] + 1][robot[0]] == '#') {
                        if (distance > 0) path = path.concat(distance + ",");
                        path = path.concat("L,");
                        direction = 2;
                        distance = 0;
                    } else if (map[robot[1] - 1][robot[0]] == '#') {
                        if (distance > 0) path = path.concat(distance + ",");
                        path = path.concat("R,");
                        direction = 0;
                        distance = 0;
                    } else {
                        if (distance > 0) path = path.concat(String.valueOf(distance));
                        running = false;
                    }
                    break;
                default:
                    System.err.println("Whoops!");
            }
        }
        return path;
    }

    public static long[] convertToArray(String inputList) {
        ArrayList<Long> list = new ArrayList<>();
        String input = inputList.concat(",");
        while (input.contains(",")) {
            list.add(Long.parseLong(input.substring(0,input.indexOf(","))));
            input = input.substring(input.indexOf(",") + 1);
        }
        long[] array = new long[10000];
        for(int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static void compute(long[] input) {
        Scanner scan = new Scanner(System.in);
        String string = "";
        long parameter1;
        long parameter2;
        int jumpBy;
        int opcodeLocation = 0;
        int relativeBase = 0;
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
                if (string.equals("")) {
                    string = scan.next();
                    string = string.concat(String.valueOf((char) 10));
                }
                int asciiValue = string.charAt(0);
                if (string.length() > 1) string = string.substring(1);
                else string = "";
                if (opcodeArray[2] == 0) input[(int) input[opcodeLocation + 1]] = asciiValue;
                else input[relativeBase + (int) input[opcodeLocation + 1]] = asciiValue;
                jumpBy = 2;
            }
            else if (opcodeFunction == 4) {
                if (opcodeArray[2] == 0) parameter1 = input[(int) input[opcodeLocation + 1]];
                else if (opcodeArray[2] == 1) parameter1 = input[opcodeLocation + 1];
                else parameter1 = input[relativeBase+(int) input[opcodeLocation+1]];
                if (parameter1 < 128) System.out.print((char)parameter1);
                else System.out.println(parameter1);
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
        System.out.println(planPath(fileToArray()));
        compute(convertToArray("2,330,331,332,109,3546,1101,0,1182,15,1101,1481,0,24,1001,0,0,570,1006,570,36,102,1,571,0,1001,570,-1,570,1001,24,1,24,1105,1,18,1008,571,0,571,1001,15,1,15,1008,15,1481,570,1006,570,14,21102,58,1,0,1106,0,786,1006,332,62,99,21101,0,333,1,21101,0,73,0,1106,0,579,1101,0,0,572,1101,0,0,573,3,574,101,1,573,573,1007,574,65,570,1005,570,151,107,67,574,570,1005,570,151,1001,574,-64,574,1002,574,-1,574,1001,572,1,572,1007,572,11,570,1006,570,165,101,1182,572,127,1002,574,1,0,3,574,101,1,573,573,1008,574,10,570,1005,570,189,1008,574,44,570,1006,570,158,1105,1,81,21102,1,340,1,1106,0,177,21102,1,477,1,1106,0,177,21101,0,514,1,21102,1,176,0,1105,1,579,99,21102,1,184,0,1106,0,579,4,574,104,10,99,1007,573,22,570,1006,570,165,102,1,572,1182,21102,375,1,1,21101,211,0,0,1106,0,579,21101,1182,11,1,21101,0,222,0,1106,0,979,21102,388,1,1,21102,1,233,0,1106,0,579,21101,1182,22,1,21102,1,244,0,1106,0,979,21101,0,401,1,21102,255,1,0,1106,0,579,21101,1182,33,1,21102,266,1,0,1105,1,979,21102,414,1,1,21102,1,277,0,1105,1,579,3,575,1008,575,89,570,1008,575,121,575,1,575,570,575,3,574,1008,574,10,570,1006,570,291,104,10,21102,1,1182,1,21102,1,313,0,1105,1,622,1005,575,327,1102,1,1,575,21101,0,327,0,1106,0,786,4,438,99,0,1,1,6,77,97,105,110,58,10,33,10,69,120,112,101,99,116,101,100,32,102,117,110,99,116,105,111,110,32,110,97,109,101,32,98,117,116,32,103,111,116,58,32,0,12,70,117,110,99,116,105,111,110,32,65,58,10,12,70,117,110,99,116,105,111,110,32,66,58,10,12,70,117,110,99,116,105,111,110,32,67,58,10,23,67,111,110,116,105,110,117,111,117,115,32,118,105,100,101,111,32,102,101,101,100,63,10,0,37,10,69,120,112,101,99,116,101,100,32,82,44,32,76,44,32,111,114,32,100,105,115,116,97,110,99,101,32,98,117,116,32,103,111,116,58,32,36,10,69,120,112,101,99,116,101,100,32,99,111,109,109,97,32,111,114,32,110,101,119,108,105,110,101,32,98,117,116,32,103,111,116,58,32,43,10,68,101,102,105,110,105,116,105,111,110,115,32,109,97,121,32,98,101,32,97,116,32,109,111,115,116,32,50,48,32,99,104,97,114,97,99,116,101,114,115,33,10,94,62,118,60,0,1,0,-1,-1,0,1,0,0,0,0,0,0,1,12,18,0,109,4,2102,1,-3,587,20101,0,0,-1,22101,1,-3,-3,21101,0,0,-2,2208,-2,-1,570,1005,570,617,2201,-3,-2,609,4,0,21201,-2,1,-2,1106,0,597,109,-4,2106,0,0,109,5,2102,1,-4,630,20102,1,0,-2,22101,1,-4,-4,21101,0,0,-3,2208,-3,-2,570,1005,570,781,2201,-4,-3,653,20102,1,0,-1,1208,-1,-4,570,1005,570,709,1208,-1,-5,570,1005,570,734,1207,-1,0,570,1005,570,759,1206,-1,774,1001,578,562,684,1,0,576,576,1001,578,566,692,1,0,577,577,21101,0,702,0,1105,1,786,21201,-1,-1,-1,1106,0,676,1001,578,1,578,1008,578,4,570,1006,570,724,1001,578,-4,578,21102,731,1,0,1105,1,786,1106,0,774,1001,578,-1,578,1008,578,-1,570,1006,570,749,1001,578,4,578,21102,1,756,0,1105,1,786,1105,1,774,21202,-1,-11,1,22101,1182,1,1,21101,0,774,0,1106,0,622,21201,-3,1,-3,1106,0,640,109,-5,2106,0,0,109,7,1005,575,802,21001,576,0,-6,20102,1,577,-5,1106,0,814,21102,1,0,-1,21102,0,1,-5,21102,0,1,-6,20208,-6,576,-2,208,-5,577,570,22002,570,-2,-2,21202,-5,59,-3,22201,-6,-3,-3,22101,1481,-3,-3,2101,0,-3,843,1005,0,863,21202,-2,42,-4,22101,46,-4,-4,1206,-2,924,21102,1,1,-1,1105,1,924,1205,-2,873,21102,35,1,-4,1105,1,924,2101,0,-3,878,1008,0,1,570,1006,570,916,1001,374,1,374,1202,-3,1,895,1101,0,2,0,2101,0,-3,902,1001,438,0,438,2202,-6,-5,570,1,570,374,570,1,570,438,438,1001,578,558,921,21002,0,1,-4,1006,575,959,204,-4,22101,1,-6,-6,1208,-6,59,570,1006,570,814,104,10,22101,1,-5,-5,1208,-5,35,570,1006,570,810,104,10,1206,-1,974,99,1206,-1,974,1101,0,1,575,21102,973,1,0,1105,1,786,99,109,-7,2105,1,0,109,6,21101,0,0,-4,21102,0,1,-3,203,-2,22101,1,-3,-3,21208,-2,82,-1,1205,-1,1030,21208,-2,76,-1,1205,-1,1037,21207,-2,48,-1,1205,-1,1124,22107,57,-2,-1,1205,-1,1124,21201,-2,-48,-2,1106,0,1041,21102,1,-4,-2,1106,0,1041,21101,0,-5,-2,21201,-4,1,-4,21207,-4,11,-1,1206,-1,1138,2201,-5,-4,1059,1202,-2,1,0,203,-2,22101,1,-3,-3,21207,-2,48,-1,1205,-1,1107,22107,57,-2,-1,1205,-1,1107,21201,-2,-48,-2,2201,-5,-4,1090,20102,10,0,-1,22201,-2,-1,-2,2201,-5,-4,1103,1202,-2,1,0,1105,1,1060,21208,-2,10,-1,1205,-1,1162,21208,-2,44,-1,1206,-1,1131,1105,1,989,21101,0,439,1,1106,0,1150,21102,477,1,1,1106,0,1150,21101,0,514,1,21102,1,1149,0,1105,1,579,99,21101,0,1157,0,1106,0,579,204,-2,104,10,99,21207,-3,22,-1,1206,-1,1138,2101,0,-5,1176,1201,-4,0,0,109,-6,2105,1,0,6,13,27,13,6,1,11,1,27,1,11,1,6,1,11,1,27,1,11,1,6,1,11,1,27,1,11,1,6,1,11,1,27,1,11,1,6,1,11,1,27,1,11,1,6,1,11,1,1,9,9,11,9,1,6,1,11,1,1,1,7,1,9,1,7,1,1,1,9,1,6,1,11,13,7,1,7,1,1,1,9,1,6,1,13,1,7,1,1,1,7,1,7,1,1,1,9,1,6,1,13,1,7,1,1,1,5,11,1,1,9,1,6,1,13,1,7,1,1,1,5,1,1,1,9,1,9,1,6,11,3,1,7,1,1,1,5,1,1,1,9,1,1,9,16,1,3,1,7,1,1,1,5,1,1,1,9,1,1,1,24,1,3,1,7,13,7,1,1,1,24,1,3,1,9,1,5,1,1,1,1,1,7,1,1,1,24,1,3,1,9,9,1,1,7,11,16,1,3,1,15,1,3,1,9,1,7,1,12,9,15,1,3,1,9,1,7,1,16,1,19,1,3,1,9,1,7,1,16,1,19,11,3,1,7,1,16,1,23,1,5,1,3,1,7,1,8,9,23,11,7,1,8,1,37,1,11,1,8,1,37,1,11,1,8,1,37,1,11,1,8,1,37,1,11,1,8,1,37,1,11,1,8,1,37,13,8,1,58,1,58,1,58,1,58,1,50,9,50"));
    }
}
