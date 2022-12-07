package AoC2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DaySeven {
    static ArrayList<Directory> allDirects = new ArrayList<>();
    public static void fileToArray() {
        try {
            File file = new File("src\\AoC2022\\input\\daySeven.txt");
            Scanner scan = new Scanner(file);
            Directory currentDir = new Directory("/");
            scan.nextLine();
            while (scan.hasNextLine()) {
                String input = scan.nextLine();
                switch (input.substring(0,4)){
                    case "$ cd":
                        if (input.substring(input.lastIndexOf(" ")).equals(" ..")){
                            currentDir = currentDir.getParent();
                        }else{
                            currentDir = currentDir.selectSubDirectory(input.substring(input.lastIndexOf(" ")+1));
                        }
                        break;
                    case "$ ls":
                        break;
                    case"dir ":
                        currentDir.add(new Directory(input.substring(input.indexOf(" ")+1),currentDir));
                        break;
                    default:
                        currentDir.add(new FakeFile(input.substring(input.indexOf(" ")+1),currentDir,Integer.parseInt(input.substring(0,input.indexOf(" ")))));
                }
            }
            scan.close();
        }catch (FileNotFoundException error) {
            System.err.println("Whoops! File not found.");
        }
    }

    public static void main(String[] args){
        fileToArray();
        int totalSize = 0;
        int minSize = Integer.MAX_VALUE;
        final int necessaryDeletion = allDirects.get(0).getSize()-40000000;
        for(Directory directory: allDirects){
            int size = directory.getSize();
            if(size <= 100000){
                totalSize += size;
            }else if(size >= necessaryDeletion && size < minSize){
                minSize = size;
            }
        }
        System.out.println(totalSize);
        System.out.println(minSize);
    }
}

abstract class SystemObject {
    String name;
    Directory parent;
    abstract int getSize();

    String getName(){
        return name;
    }

    Directory getParent(){
        return parent;
    }
}

class FakeFile extends SystemObject{
    final private int size;

    FakeFile(String title,Directory upperDir, int bytes){
        size = bytes;
        name = title;
        parent = upperDir;
    }

    @Override
    int getSize(){
        return size;
    }
}

class Directory extends SystemObject{
    ArrayList<SystemObject> contents = new ArrayList<>();
    Directory(String title, Directory upperDir){
        name = title;
        parent = upperDir;
        DaySeven.allDirects.add(this);
    }
    Directory(String title){
        name = title;
        DaySeven.allDirects.add(this);
    }
    @Override
    int getSize(){
        int totalSize = 0;
        for (SystemObject object: contents){
            totalSize += object.getSize();
        }
        return totalSize;
    }

    void add(SystemObject newObject){
        contents.add(newObject);
    }

    Directory selectSubDirectory(String title){
        for (SystemObject object : contents){
            if (object.getName().equals(title)){
                return (Directory) object;
            }
        }
        return new Directory("MISTAKE!!!!");
    }
}
