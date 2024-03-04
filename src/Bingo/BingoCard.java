package Bingo;

import java.util.ArrayList;

public class BingoCard {
    public int[][] numbers;
    private int ID;

    public BingoCard(int ID) {
        this.ID = ID;
        numbers = new int[5][5];

        generateRandomNumbers();
    }

    public int getID() { return ID; }


    private void generateRandomNumbers() {
        /*
        B: 1-15
        I: 16-30
        N: 31-45
        G: 46-60
        O: 61-75
         */

        int maxNum = 15;
        int minNum = 1;

        for(int i = 0; i < 5; i++) {

            ArrayList<Integer> numsGenerated = new ArrayList<>();

            for(int j = 0; j < 5; j++) {

                if(i == 2 && j == 2) {
                    numbers[i][j] = 0;
                    continue;
                }

                while (true) {
                    int randomNum = minNum + (int)(Math.random() * ((maxNum-minNum) + 1));

                    if(!numsGenerated.contains(randomNum)) {
                        numsGenerated.add(randomNum);

                        numbers[i][j] = randomNum;
                        break;
                    }
                }

            }
            maxNum += 15;
            minNum += 15;
        }
    }

    public void printNums() {
        for(int i = 0; i < 5; i++) {

            for(int j = 0; j < 5; j++) {

                System.out.print(numbers[j][i] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

}
