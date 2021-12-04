package AoC2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DayFour {
	static int[] drawList;
	public static ArrayList<int[][]> fileToArray() {
		try {
			ArrayList<int[][]> inputList = new ArrayList<>();
			File file = new File("src\\AoC2021\\input\\dayFour.txt");
			Scanner scan = new Scanner(file);
			String[] stringDrawList = scan.nextLine().split(",");
			drawList = new int[stringDrawList.length];
			for (int i = 0; i < stringDrawList.length; i++){
				drawList[i] = Integer.parseInt(stringDrawList[i]);
			}
			scan.nextLine();
			int[][] bingoCard = new int[5][5];
			int row = 0;
			while (scan.hasNextLine()) {
				String nextLine = scan.nextLine();
				if(nextLine.isBlank()){
					row = 0;
					inputList.add(bingoCard);
					bingoCard = new int[5][5];
				}else{
					String[] rowStrings = nextLine.trim().split(" +");
					for (int i = 0; i < rowStrings.length; i++){
						bingoCard[row][i] = Integer.parseInt(rowStrings[i]);
					}
					row++;
				}
			}
			scan.close();
			return inputList;
		}catch (FileNotFoundException error) {
			System.err.println("Whoops! File not found.");
			return new ArrayList<>();
		}
	}

	public static int partOne(){
		ArrayList<int[][]> bingoCards= fileToArray();
		ArrayList<boolean[][]> marks = new ArrayList<>();
		for (int i = 0; i < bingoCards.size(); i++){
			marks.add(new boolean[5][5]);
		}
		int[][] winningCard = new int[1][1];
		boolean[][] winningMarks = new boolean[1][1];
		int winningDraw = 0;
		outerLoop:
		for (int draw : drawList){
			for(int card = 0; card < bingoCards.size(); card++){
				for (int row = 0; row < 5; row++){
					for (int num = 0; num < 5; num++){
						if (draw == bingoCards.get(card)[row][num]) marks.get(card)[row][num] = true;
					}
				}
			}
			for (int cardNum = 0; cardNum < bingoCards.size(); cardNum++){
				boolean[][] card = marks.get(cardNum);
				for (int i = 0; i < 5; i++){
					if ((card[i][0] && card[i][1] && card[i][2] && card[i][3] && card[i][4]) || (card[0][i] && card[1][i] && card[2][i] && card[3][i] && card[4][i])) {
						winningCard = bingoCards.get(cardNum);
						winningMarks = card;
						winningDraw = draw;
						break outerLoop;
					}
				}
			}
		}
		int unusedSum = 0;
		for (int row = 0; row < 5; row++){
			for (int col = 0; col < 5; col++){
				if (!winningMarks[row][col]) unusedSum+=winningCard[row][col];
			}
		}
		return unusedSum*winningDraw;
	}

	public static int partTwo(){
		ArrayList<int[][]> bingoCards= fileToArray();
		ArrayList<boolean[][]> marks = new ArrayList<>();
		for (int i = 0; i < bingoCards.size(); i++){
			marks.add(new boolean[5][5]);
		}
		int[][] winningCard = new int[1][1];
		boolean[][] winningMarks = new boolean[1][1];
		int winningDraw = 0;
		outerLoop:
		for (int draw : drawList){
			for(int card = 0; card < bingoCards.size(); card++){
				for (int row = 0; row < 5; row++){
					for (int num = 0; num < 5; num++){
						if (draw == bingoCards.get(card)[row][num]) marks.get(card)[row][num] = true;
					}
				}
			}
			for (int cardNum = 0; cardNum < bingoCards.size(); cardNum++){
				boolean[][] card = marks.get(cardNum);
				for (int i = 0; i < 5; i++){
					if ((card[i][0] && card[i][1] && card[i][2] && card[i][3] && card[i][4]) || (card[0][i] && card[1][i] && card[2][i] && card[3][i] && card[4][i])) {
						if(bingoCards.size() == 1){
							winningCard = bingoCards.get(0);
							winningMarks = marks.get(0);
							winningDraw = draw;
							break outerLoop;
						}
						bingoCards.remove(cardNum);
						marks.remove(cardNum);
						break;
					}
				}
			}
		}
		int unusedSum = 0;
		for (int row = 0; row < 5; row++){
			for (int col = 0; col < 5; col++){
				if (!winningMarks[row][col]) unusedSum+=winningCard[row][col];
			}
		}
		return unusedSum*winningDraw;
	}

	public static void main(String[] args){
		System.out.println(partOne());
		System.out.println(partTwo());
	}
}
