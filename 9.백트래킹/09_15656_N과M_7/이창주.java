import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int M;
    static int[] arr;
    static int[] choice;
    static int[] dist;
    static StringBuilder sb = new StringBuilder();


    static void recur(int n, int m) {

        if (m == M) {
            for (int i = 0; i < m; i++) {
                sb.append(choice[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        if(n == N) return;

        for (int i = 0; i < N; i++) {

            choice[m] = arr[i];
            recur(n + 1, m + 1);


        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        StringTokenizer st2 = new StringTokenizer(br.readLine());
        arr = new int[N];
        choice = new int[M];
        dist = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st2.nextToken());
        }
        Arrays.sort(arr);
        recur(0,0);


        System.out.println(sb);
    }
}