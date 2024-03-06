package Threads.PasswordHacking;

public class PasswordThread implements Runnable {

    private char start;
    private StringBuilder atk;
    private String pass;

    private int name;
    private int len;

    private static boolean found;

    public PasswordThread(char start, String pass, int name) {
        this.start = start;
        this.pass = pass;
        this.name = name;

        found = false;

        len = pass.length();
        atk = new StringBuilder();
    }

    @Override
    public void run() {

        int ctr = 0;

        while(!atk.toString().equals(pass) && !found) {
            ctr++;
            int[] res = new int[len];
            int tmp = ctr;

            for(int i = len-1, j = 0; i >= 0; i--, j++) {
                res[j] = tmp / (int) Math.pow(26, i);
                tmp  = tmp % (int) Math.pow(26, i);
            }

            atk = new StringBuilder();
            atk.append(start);
            for(int i = 1; i < len; i++) {
                atk.append((char)('a'+res[i]));
            }
            System.out.println("THREAD: " + start + " = " + atk);
        }

        if(atk.toString().equals(pass)) {
            System.out.println("FOUND" + atk);
            found = true;
        }
    }
}
