import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int M;
    static StringBuilder sb = new StringBuilder();
    static int[] pick;

    static void recur(int n, int start) {
        if (n == M) {

            for (int i = 0; i < M; i++) {
                sb.append(pick[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start + 1; i <= N; i++) {
            pick[n] = i;
            recur(n + 1, i);
        }
    }

    public static void main(String[] args) throws IOException {
        // 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
        // 수열은 오름차순
        // 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        pick = new int[M];
        recur(0,0);
        System.out.println(sb);
    }
}