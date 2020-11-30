package AoC2019;

public class DaySixteen {
    private static int[] calculate(int[] input) {
        /*
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
         */

        int[] output = new int[input.length];
        for (int outputNum = 0; outputNum < input.length; outputNum++) {
            for (int inputNum = 0; inputNum < input.length; inputNum++) {
                if ((inputNum+1)/(outputNum+1)%4 == 1) {
                    output[outputNum] += input[inputNum];
                }else if((inputNum+1)/(outputNum+1)%4 == 3){
                    output[outputNum] -= input[inputNum];
                }
            }
            output[outputNum] = Math.abs(output[outputNum] % 10);
        }
        return output;
    }

    public static void main(String[] args) {
        String string = "";
        String simpleString = "59767332893712499303507927392492799842280949032647447943708128134759829623432979665638627748828769901459920331809324277257783559980682773005090812015194705678044494427656694450683470894204458322512685463108677297931475224644120088044241514984501801055776621459006306355191173838028818541852472766531691447716699929369254367590657434009446852446382913299030985023252085192396763168288943696868044543275244584834495762182333696287306000879305760028716584659188511036134905935090284404044065551054821920696749822628998776535580685208350672371545812292776910208462128008216282210434666822690603370151291219895209312686939242854295497457769408869210686246";
        for (int i = 0; i < 1; i++) string = string.concat(simpleString);
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
        System.out.println(answer);
        //int offset = Integer.parseInt(simpleString.substring(0,7));
        //System.out.println(answer.substring(offset, offset+8));
    }
}
