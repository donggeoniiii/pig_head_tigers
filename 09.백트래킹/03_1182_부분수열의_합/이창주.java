import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static boolean[] issue1 = new boolean[40];
    static boolean[] issue2 = new boolean[40];
    static boolean[] issue3 = new boolean[40];

    static int N;
    static int cnt = 0;


    static void recur(int qC) {
        if (qC == N) {
            cnt++;
            return;
        }

        for (int i = 0; i < N; i++) {
            if(issue1[i] || issue2[i+qC] || issue3[qC - i + N - 1]) continue;
            issue1[i] = true;
            issue2[i + qC] = true;
            issue3[qC - i + N - 1] = true;
            recur(qC + 1);
            issue1[i] = false;
            issue2[i + qC] = false;
            issue3[qC - i + N - 1] = false;

        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        recur(0);
        System.out.println(cnt);
    }
}