import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int M;
    static int[] arr;
    static int[] choice;
    static StringBuilder sb = new StringBuilder();
    static void recur(int n, int m) {
        if (m == M) {
            for (int i = 0; i < m; i++) {
                sb.append(choice[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        if (n == N) {
            return;
        }
        choice[m] = arr[n];
        recur(n+1,m+1);
        recur(n + 1, m);

    }

    public static void main(String[] args) throws IOException {
        // N개의 자연수 중에서 M개를 고른 수열
        // 오름 차순 이어야 한다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        choice = new int[N];
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st2.nextToken());
        }

        for (int i = 0; i < N - 1; i++) {
            for (int j = i; j < N; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        recur(0, 0);

        System.out.println(sb);
    }
}