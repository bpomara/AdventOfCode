package AoC2019;

import java.util.ArrayList;
import java.util.Scanner;

public class DayTwentyOne {
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
                    string = scan.nextLine();
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
        compute(convertToArray("109,2050,21102,966,1,1,21102,13,1,0,1106,0,1378,21102,20,1,0,1106,0,1337,21102,1,27,0,1105,1,1279,1208,1,65,748,1005,748,73,1208,1,79,748,1005,748,110,1208,1,78,748,1005,748,132,1208,1,87,748,1005,748,169,1208,1,82,748,1005,748,239,21101,1041,0,1,21102,73,1,0,1105,1,1421,21102,78,1,1,21102,1041,1,2,21101,88,0,0,1105,1,1301,21102,68,1,1,21102,1041,1,2,21101,0,103,0,1106,0,1301,1101,0,1,750,1106,0,298,21102,82,1,1,21101,0,1041,2,21102,125,1,0,1106,0,1301,1101,0,2,750,1106,0,298,21101,0,79,1,21102,1041,1,2,21102,1,147,0,1105,1,1301,21102,1,84,1,21102,1041,1,2,21101,0,162,0,1105,1,1301,1101,0,3,750,1105,1,298,21102,65,1,1,21101,1041,0,2,21102,184,1,0,1105,1,1301,21101,0,76,1,21102,1041,1,2,21101,199,0,0,1106,0,1301,21102,1,75,1,21102,1,1041,2,21102,214,1,0,1106,0,1301,21102,1,221,0,1106,0,1337,21102,10,1,1,21102,1041,1,2,21102,236,1,0,1106,0,1301,1106,0,553,21101,0,85,1,21102,1041,1,2,21102,254,1,0,1106,0,1301,21101,0,78,1,21102,1041,1,2,21102,1,269,0,1106,0,1301,21102,1,276,0,1106,0,1337,21101,0,10,1,21102,1,1041,2,21102,1,291,0,1106,0,1301,1102,1,1,755,1106,0,553,21102,32,1,1,21102,1,1041,2,21102,1,313,0,1106,0,1301,21102,1,320,0,1105,1,1337,21101,327,0,0,1105,1,1279,2102,1,1,749,21101,65,0,2,21102,1,73,3,21101,0,346,0,1106,0,1889,1206,1,367,1007,749,69,748,1005,748,360,1101,0,1,756,1001,749,-64,751,1106,0,406,1008,749,74,748,1006,748,381,1102,1,-1,751,1105,1,406,1008,749,84,748,1006,748,395,1101,-2,0,751,1105,1,406,21102,1,1100,1,21102,1,406,0,1106,0,1421,21101,0,32,1,21101,0,1100,2,21101,421,0,0,1106,0,1301,21102,1,428,0,1105,1,1337,21101,0,435,0,1105,1,1279,2102,1,1,749,1008,749,74,748,1006,748,453,1101,-1,0,752,1106,0,478,1008,749,84,748,1006,748,467,1102,1,-2,752,1106,0,478,21102,1,1168,1,21102,478,1,0,1105,1,1421,21101,0,485,0,1105,1,1337,21101,10,0,1,21102,1168,1,2,21102,1,500,0,1105,1,1301,1007,920,15,748,1005,748,518,21102,1,1209,1,21101,0,518,0,1106,0,1421,1002,920,3,529,1001,529,921,529,1001,750,0,0,1001,529,1,537,102,1,751,0,1001,537,1,545,101,0,752,0,1001,920,1,920,1105,1,13,1005,755,577,1006,756,570,21101,1100,0,1,21101,570,0,0,1105,1,1421,21102,1,987,1,1106,0,581,21101,1001,0,1,21101,0,588,0,1106,0,1378,1101,0,758,594,101,0,0,753,1006,753,654,20102,1,753,1,21101,610,0,0,1106,0,667,21101,0,0,1,21101,621,0,0,1105,1,1463,1205,1,647,21102,1,1015,1,21102,1,635,0,1106,0,1378,21102,1,1,1,21101,0,646,0,1106,0,1463,99,1001,594,1,594,1106,0,592,1006,755,664,1101,0,0,755,1106,0,647,4,754,99,109,2,1102,726,1,757,22102,1,-1,1,21101,9,0,2,21101,697,0,3,21102,1,692,0,1106,0,1913,109,-2,2105,1,0,109,2,1001,757,0,706,1202,-1,1,0,1001,757,1,757,109,-2,2105,1,0,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,255,63,159,95,191,127,223,0,170,181,42,172,156,141,244,237,232,166,114,189,78,126,219,236,143,196,34,228,50,71,117,121,107,190,86,154,94,207,248,245,87,246,49,69,202,253,70,174,198,247,60,238,163,139,43,188,57,99,103,243,203,239,171,102,221,47,183,51,140,155,101,137,76,136,111,119,233,212,77,186,168,199,187,153,249,100,206,251,252,85,234,116,152,61,182,229,122,39,162,222,68,197,124,169,120,175,254,226,218,215,125,201,227,217,241,79,98,93,46,123,55,242,113,235,53,184,92,167,185,109,35,173,138,106,204,110,177,62,250,230,56,58,142,205,214,84,108,179,118,54,231,59,213,216,220,115,157,178,200,38,158,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,20,73,110,112,117,116,32,105,110,115,116,114,117,99,116,105,111,110,115,58,10,13,10,87,97,108,107,105,110,103,46,46,46,10,10,13,10,82,117,110,110,105,110,103,46,46,46,10,10,25,10,68,105,100,110,39,116,32,109,97,107,101,32,105,116,32,97,99,114,111,115,115,58,10,10,58,73,110,118,97,108,105,100,32,111,112,101,114,97,116,105,111,110,59,32,101,120,112,101,99,116,101,100,32,115,111,109,101,116,104,105,110,103,32,108,105,107,101,32,65,78,68,44,32,79,82,44,32,111,114,32,78,79,84,67,73,110,118,97,108,105,100,32,102,105,114,115,116,32,97,114,103,117,109,101,110,116,59,32,101,120,112,101,99,116,101,100,32,115,111,109,101,116,104,105,110,103,32,108,105,107,101,32,65,44,32,66,44,32,67,44,32,68,44,32,74,44,32,111,114,32,84,40,73,110,118,97,108,105,100,32,115,101,99,111,110,100,32,97,114,103,117,109,101,110,116,59,32,101,120,112,101,99,116,101,100,32,74,32,111,114,32,84,52,79,117,116,32,111,102,32,109,101,109,111,114,121,59,32,97,116,32,109,111,115,116,32,49,53,32,105,110,115,116,114,117,99,116,105,111,110,115,32,99,97,110,32,98,101,32,115,116,111,114,101,100,0,109,1,1005,1262,1270,3,1262,20102,1,1262,0,109,-1,2105,1,0,109,1,21101,0,1288,0,1106,0,1263,20102,1,1262,0,1102,0,1,1262,109,-1,2105,1,0,109,5,21102,1310,1,0,1105,1,1279,21201,1,0,-2,22208,-2,-4,-1,1205,-1,1332,21201,-3,0,1,21102,1,1332,0,1105,1,1421,109,-5,2106,0,0,109,2,21101,0,1346,0,1105,1,1263,21208,1,32,-1,1205,-1,1363,21208,1,9,-1,1205,-1,1363,1106,0,1373,21101,0,1370,0,1105,1,1279,1105,1,1339,109,-2,2106,0,0,109,5,1202,-4,1,1386,20101,0,0,-2,22101,1,-4,-4,21101,0,0,-3,22208,-3,-2,-1,1205,-1,1416,2201,-4,-3,1408,4,0,21201,-3,1,-3,1106,0,1396,109,-5,2105,1,0,109,2,104,10,22102,1,-1,1,21101,0,1436,0,1106,0,1378,104,10,99,109,-2,2105,1,0,109,3,20002,594,753,-1,22202,-1,-2,-1,201,-1,754,754,109,-3,2105,1,0,109,10,21101,5,0,-5,21101,1,0,-4,21102,0,1,-3,1206,-9,1555,21102,3,1,-6,21102,5,1,-7,22208,-7,-5,-8,1206,-8,1507,22208,-6,-4,-8,1206,-8,1507,104,64,1106,0,1529,1205,-6,1527,1201,-7,716,1515,21002,0,-11,-8,21201,-8,46,-8,204,-8,1106,0,1529,104,46,21201,-7,1,-7,21207,-7,22,-8,1205,-8,1488,104,10,21201,-6,-1,-6,21207,-6,0,-8,1206,-8,1484,104,10,21207,-4,1,-8,1206,-8,1569,21101,0,0,-9,1106,0,1689,21208,-5,21,-8,1206,-8,1583,21102,1,1,-9,1105,1,1689,1201,-5,716,1589,20101,0,0,-2,21208,-4,1,-1,22202,-2,-1,-1,1205,-2,1613,22102,1,-5,1,21101,0,1613,0,1106,0,1444,1206,-1,1634,22101,0,-5,1,21101,0,1627,0,1106,0,1694,1206,1,1634,21102,2,1,-3,22107,1,-4,-8,22201,-1,-8,-8,1206,-8,1649,21201,-5,1,-5,1206,-3,1663,21201,-3,-1,-3,21201,-4,1,-4,1105,1,1667,21201,-4,-1,-4,21208,-4,0,-1,1201,-5,716,1676,22002,0,-1,-1,1206,-1,1686,21102,1,1,-4,1106,0,1477,109,-10,2106,0,0,109,11,21102,1,0,-6,21101,0,0,-8,21102,1,0,-7,20208,-6,920,-9,1205,-9,1880,21202,-6,3,-9,1201,-9,921,1725,20101,0,0,-5,1001,1725,1,1733,20101,0,0,-4,22101,0,-4,1,21102,1,1,2,21101,9,0,3,21101,0,1754,0,1105,1,1889,1206,1,1772,2201,-10,-4,1766,1001,1766,716,1766,21001,0,0,-3,1105,1,1790,21208,-4,-1,-9,1206,-9,1786,22102,1,-8,-3,1106,0,1790,21201,-7,0,-3,1001,1733,1,1796,20102,1,0,-2,21208,-2,-1,-9,1206,-9,1812,21201,-8,0,-1,1106,0,1816,21201,-7,0,-1,21208,-5,1,-9,1205,-9,1837,21208,-5,2,-9,1205,-9,1844,21208,-3,0,-1,1105,1,1855,22202,-3,-1,-1,1105,1,1855,22201,-3,-1,-1,22107,0,-1,-1,1105,1,1855,21208,-2,-1,-9,1206,-9,1869,21201,-1,0,-8,1105,1,1873,22101,0,-1,-7,21201,-6,1,-6,1105,1,1708,21201,-8,0,-10,109,-11,2106,0,0,109,7,22207,-6,-5,-3,22207,-4,-6,-2,22201,-3,-2,-1,21208,-1,0,-6,109,-7,2106,0,0,0,109,5,1201,-2,0,1912,21207,-4,0,-1,1206,-1,1930,21102,1,0,-4,21201,-4,0,1,21201,-3,0,2,21101,1,0,3,21102,1949,1,0,1106,0,1954,109,-5,2106,0,0,109,6,21207,-4,1,-1,1206,-1,1977,22207,-5,-3,-1,1206,-1,1977,22101,0,-5,-5,1105,1,2045,22102,1,-5,1,21201,-4,-1,2,21202,-3,2,3,21101,1996,0,0,1106,0,1954,22102,1,1,-5,21101,1,0,-2,22207,-5,-3,-1,1206,-1,2015,21102,1,0,-2,22202,-3,-2,-3,22107,0,-4,-1,1206,-1,2037,21201,-2,0,1,21101,0,2037,0,105,1,1912,21202,-3,-1,-3,22201,-5,-3,-5,109,-6,2105,1,0"));
    }
}
/*
Walk input instructions:
NOT C J
AND D J
NOT A T
OR T J
WALK

Run input instructions:
NOT C J
AND D J
OR E T
AND I T
OR H T
AND T J
NOT A T
OR T J
NOT J T
NOT T T
OR B T
OR E T
NOT T T
OR T J
RUN
 */