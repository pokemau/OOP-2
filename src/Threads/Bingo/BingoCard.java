package Threads.Bingo;

/*
BingoCard
    fields:
        - random list of integers from 1-75
            - 5 each from B, I, N, G, O (only 4 for N)
            - first 5 must be 1-15 in random order
            - second 5 must be 16-30, etc
            - third must have its 3rd element as 0 (FREE) 31-45
        - integer id
 */

import java.util.ArrayList;
import java.util.Random;

public class BingoCard {

    private int[][] nums;
    private int ID;

    public BingoCard(int ID) {
        this.ID = ID;
        nums = new int[5][5];

        generateNumbers();
    }

    public void generateNumbers() {

        int min = 1;
        int max = 15;

        Random random = new Random();

        for(int i = 0; i < 5; i++) {

            ArrayList<Integer> generatedNums = new ArrayList<>();
            int randomNum;

            for(int j = 0; j < 5; j++) {

                if(i == 2 && j == 2) {
                    nums[i][j] = 0;
                    continue;
                }

                while(true) {
                    randomNum = random.nextInt(min, max+1);
                    if(!generatedNums.contains(randomNum)) {
                        generatedNums.add(randomNum);
                        break;
                    }
                }
                nums[i][j] = randomNum;
            }

            min = max;
            max += 15;
        }

    }

    public int[][] getNums() { return nums; }

    public int getID() { return ID; }
    public void printNums() {

        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                System.out.print(nums[j][i] + "\t");
            }
            System.out.println();
        }
    }
}
