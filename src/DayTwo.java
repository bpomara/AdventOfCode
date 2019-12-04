import java.util.ArrayList;

public class DayTwo {
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

    public static int[] compute(int[] input) {
        int opcode = 0;
        while (input[opcode] != 99) {
            if (input[opcode] == 1) {
                input[input[opcode + 3]] = input[input[opcode + 1]] + input[input[opcode + 2]];
            } else if (input[opcode] == 2) {
                input[input[opcode + 3]] = input[input[opcode + 1]] * input[input[opcode + 2]];
            } else {
                System.out.println("Error in opcode");
                return new int[100];
            }
            opcode = opcode + 4;
        }
        return input;
    }

    public static String convertToString(int[] input) {
        String answer = "";
        for (int num : input) {
            answer = answer.concat(num + ",");
        }
        answer = answer.substring(0,answer.length()-1);
        return answer;
    }

    public static void main(String[] args) {
        int[] array = convertToArray("1,0,0,3,1,1,2,3,1,3,4,3,1,5,0,3,2,1,10,19,2,6,19,23,1,23,5,27,1,27,13,31,2,6,31,35,1,5,35,39,1,39,10,43,2,6,43,47,1,47,5,51,1,51,9,55,2,55,6,59,1,59,10,63,2,63,9,67,1,67,5,71,1,71,5,75,2,75,6,79,1,5,79,83,1,10,83,87,2,13,87,91,1,10,91,95,2,13,95,99,1,99,9,103,1,5,103,107,1,107,10,111,1,111,5,115,1,115,6,119,1,119,10,123,1,123,10,127,2,127,13,131,1,13,131,135,1,135,10,139,2,139,6,143,1,143,9,147,2,147,6,151,1,5,151,155,1,9,155,159,2,159,6,163,1,163,2,167,1,10,167,0,99,2,14,0,0");
        for (int noun = 0; noun < 100; noun++) {
            for (int verb = 0; verb < 100; verb++) {
                int[] oldArray = array.clone();
                oldArray[1] = noun;
                oldArray[2] = verb;
                if (compute(oldArray)[0] == 19690720) System.out.println(100 * noun + verb);
            }
        }
    }
}
