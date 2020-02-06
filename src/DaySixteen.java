public class DaySixteen {
    private static int[] calculate(int[] input) {
        int[] output = new int[input.length];
        for (int patternNum = 1; patternNum <= input.length; patternNum++) {
            int[] pattern = new int[input.length + 1];
            for (int j = 0; j < pattern.length; j++) {
                switch ((j/patternNum)%4) {
                    case 0:
                    case 2:
                        pattern[j] = 0;
                        break;
                    case 1:
                        pattern[j] = 1;
                        break;
                    case 3:
                        pattern[j] = -1;
                        break;
                }
            }
            int total = 0;
            for (int k = 0; k < input.length; k++) total += pattern[k+1]*input[k];
            output[patternNum-1] = Math.abs(total%10);
        }
        return output;
    }

    public static void main(String[] args) {
        String string = "";
        String simpleString = "03036732577212944063491565474664";
        for (int i = 0; i < 10000; i++) string = string.concat(simpleString);
        int[] array = new int[string.length()];
        for (int i = 0; i < array.length; i++) {
            int digit = Integer.parseInt(string.substring(i,i+1));
            array[i] = digit;
        }
        for (int phaseNum = 0; phaseNum < 100; phaseNum++) {
            System.out.println(phaseNum);
            array = calculate(array);
        }
        String answer = "";
        for (int digit : array) {
            answer = answer.concat(String.valueOf(digit));
        }
        int offset = Integer.parseInt(simpleString.substring(0,7));
        System.out.println(answer.substring(offset, offset+8));
    }
}
