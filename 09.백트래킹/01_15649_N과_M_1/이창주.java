import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static int[] sequence;
    static int N;
    static int M;

    static void recur(int n, boolean[] isIn) {
        // 다 골랐으면 탈출
        if (n == M) {
            for (int i = 0; i < M; i++) {
                sb.append(sequence[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = 1; i <= N; i++) {
            if(isIn[i]) continue;
            sequence[n] = i;
            isIn[i] = true;
            recur(n + 1, isIn);
            isIn = new boolean[N + 1];
            for (int j = 0; j < n; j++) {
                isIn[sequence[j]] = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        sequence = new int[M];
        boolean[] isIn = new boolean[N + 1];


        recur(0, isIn);
        System.out.println(sb);

    }
}