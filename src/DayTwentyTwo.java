import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DayTwentyTwo {
    public static int[] deckOfCards = new int[10007];
    public static void reverse() {
        int[] newDeck = new int[deckOfCards.length];
        for (int i = 0; i < deckOfCards.length; i++) {
            newDeck[i] = deckOfCards[deckOfCards.length-i-1];
        }
        System.arraycopy(newDeck,0,deckOfCards,0,newDeck.length);
    }
    public static void cut(int input){
        int cutPos = input;
        if (input < 0) cutPos = deckOfCards.length + input;
        int[] newDeck = new int[deckOfCards.length];
        System.arraycopy(deckOfCards,0,newDeck,newDeck.length-cutPos,cutPos);
        System.arraycopy(deckOfCards,cutPos,newDeck,0,deckOfCards.length-cutPos);
        System.arraycopy(newDeck,0,deckOfCards,0,newDeck.length);
    }
    public static void increment(int input){
        int[] newDeck = new int[deckOfCards.length];
        for (int i = 0; i < deckOfCards.length; i++)newDeck[input*i%deckOfCards.length] = deckOfCards[i];
        System.arraycopy(newDeck,0,deckOfCards,0,newDeck.length);
    }
    public static void main(String[] args) {
        for (int i = 0; i < deckOfCards.length; i++) deckOfCards[i] = i;
        try {
            File file = new File("src\\dayTwentyTwo.txt");
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if(line.equals("deal into new stack"))reverse();
                else if (line.substring(0,3).equals("cut"))cut(Integer.parseInt(line.substring(4)));
                else if(line.substring(0,19).equals("deal with increment"))increment(Integer.parseInt(line.substring(20)));
                else System.out.println("Uh-oh! Incorrect direction");
            }
            scan.close();
        }catch (FileNotFoundException error) {
            System.err.println("Whoops! File not found.");
        }
        for (int card : deckOfCards) System.out.print(card + " ");
        System.out.println();
        for (int i = 0; i < deckOfCards.length;i++){
            if(deckOfCards[i] == 2019){
                System.out.print(i);
            }
        }
    }
}
