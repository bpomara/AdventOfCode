package AoC2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayThirteen {
    private static final ArrayList<PseudoList> leftList = new ArrayList<>();
    private static final ArrayList<PseudoList> rightList = new ArrayList<>();
    private static final ArrayList<PseudoList> sortedList = new ArrayList<>();
    private static final ArrayList<PseudoList> unsortedList = new ArrayList<>();
    public static void fileToArray() {
        try {
            File file = new File("src\\AoC2022\\input\\dayThirteen.txt");
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                leftList.add(new PseudoList(scan.nextLine()));
                rightList.add(new PseudoList(scan.nextLine()));
                scan.nextLine();
            }
            scan.close();
        }catch (FileNotFoundException error) {
            System.err.println("Whoops! File not found.");
        }
    }

    public static void main(String[] args){
        fileToArray();
        int indexSum = 0;
        for(int i = 0; i < leftList.size(); i++){
            if(leftList.get(i).isLeftOf(rightList.get(i))){
                indexSum += i+1;
            }
            unsortedList.add(leftList.get(i));
            unsortedList.add(rightList.get(i));
        }
        System.out.println(indexSum);
        PseudoList dividerPacket1 = new PseudoList("[[2]]");
        PseudoList dividerPacket2 = new PseudoList("[[6]]");
        sortedList.add(dividerPacket1);
        sortedList.add(dividerPacket2);
        outerLoop:
        for (PseudoList object : unsortedList){
            for(int i = 0; i < sortedList.size()-1; i++){
                if(object.isLeftOf(sortedList.get(i))){
                    sortedList.add(i,object);
                    continue outerLoop;
                }
                sortedList.add(object);
            }
        }
        int decoderKey = 1;
        for (int i = 0; i < sortedList.size(); i++){
            if(sortedList.get(i).isEqualTo(dividerPacket1) || sortedList.get(i).isEqualTo(dividerPacket2)){
                decoderKey *= i+1;
            }
        }
        System.out.println(decoderKey);
    }
}

abstract class PseudoObject{
    boolean isEqualTo(PseudoObject object) {
        if(object.isList()){
            return isEqualTo((PseudoList) object);
        }else {
            return isEqualTo((PseudoInteger) object);
        }
    }

    abstract boolean isEqualTo(PseudoInteger integer);
    abstract boolean isEqualTo(PseudoList list);
    boolean isLeftOf(PseudoObject object){
        if(object.isList()){
            return isLeftOf((PseudoList) object);
        }else {
            return isLeftOf((PseudoInteger) object);
        }
    }
    abstract boolean isLeftOf(PseudoInteger integer);
    abstract boolean isLeftOf(PseudoList list);
    abstract boolean isList();
    abstract String stringify();
}

class PseudoList extends PseudoObject{
    ArrayList<PseudoObject> contents = new ArrayList<>();

    PseudoList(PseudoObject onlyContent){
        add(onlyContent);
    }

    PseudoList(String input){
        input = input.substring(1,input.length()-1);
        ArrayList<String> arrayedInput = new ArrayList<>();
        int bracketValue = 0;
        for (int i = 0; !input.isEmpty() && i<input.length(); i++){
            if (input.charAt(i) == '[') bracketValue++;
            else if (input.charAt(i) == ']') bracketValue--;
            else if (input.charAt(i) == ',' && bracketValue == 0){
                arrayedInput.add(input.substring(0,i));
                input = input.substring(i+1);
                i = -1;
            }
        }
        if(!input.isEmpty()) arrayedInput.add(input);
        for(String item : arrayedInput){
            if (item.contains("[")){
                contents.add(new PseudoList(item));
            }else{
                contents.add(new PseudoInteger(Integer.parseInt(item)));
            }
        }
    }
    @Override
    boolean isEqualTo(PseudoList list){
        if (contents.size() != list.contents.size())return false;
        for (int i = 0; i < contents.size(); i++){
            if (!contents.get(i).isEqualTo(list.get(i))) return false;
        }
        return true;
    }

    @Override
    boolean isEqualTo(PseudoInteger integer){
        return isEqualTo(new PseudoList(integer));
    }

    @Override
    boolean isLeftOf(PseudoList list) {
        try {
            for (int i = 0; i < contents.size(); i++) {
                if (contents.get(i).isEqualTo(list.get(i)))continue;
                return contents.get(i).isLeftOf(list.get(i));
            }
        }catch (IndexOutOfBoundsException e){
            return false;
        }
        return true;
    }

    @Override
    boolean isList() {
        return true;
    }

    @Override
    boolean isLeftOf(PseudoInteger integer) {
        return isLeftOf(new PseudoList(integer));
    }

    void add(PseudoObject newObject){
        contents.add(newObject);
    }

    PseudoObject get(int index){
        return contents.get(index);
    }
    @Override
    String stringify(){
        if(contents.isEmpty())return "[]";
        StringBuilder output = new StringBuilder("[");
        for (PseudoObject object : contents){
            output.append(object.stringify()).append(",");
        }
        return output.substring(0,output.length()-1) + "]";
    }
}

class PseudoInteger extends PseudoObject{
    int value;

    PseudoInteger(int newValue){
        value = newValue;
    }

    @Override
    boolean isEqualTo(PseudoList list){
        return list.isEqualTo(this);
    }

    @Override
    boolean isEqualTo(PseudoInteger integer){
        return value == integer.value;
    }

    @Override
    boolean isLeftOf(PseudoList list) {
        return !list.isLeftOf(this);
    }

    @Override
    boolean isList() {
        return false;
    }

    @Override
    boolean isLeftOf(PseudoInteger integer) {
        return value < integer.value;
    }
    @Override
    String stringify(){
        return ""+value;
    }
}
