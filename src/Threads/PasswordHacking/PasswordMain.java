package Threads.PasswordHacking;

import java.util.ArrayList;
import java.util.Scanner;

public class PasswordMain {

    static ArrayList<Thread> trd = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your password: ");
        String pass = sc.nextLine();

        int len = pass.length();

        Thread[] threads = new Thread[26];

        for(int i = 0; i < 26; i++) {
            threads[i] = new Thread(new PasswordThread((char)(i+97), pass, i));
        }

        float timeStart = System.currentTimeMillis();

        for (Thread t : threads) {
            t.start();
        }

        float timeEnd = System.currentTimeMillis();

//        System.out.println("TIME: " + (timeEnd - timeStart));
    }
}
