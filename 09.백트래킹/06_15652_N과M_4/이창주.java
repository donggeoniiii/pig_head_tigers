import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static StringBuilder sb = new StringBuilder();
    static int[] nums;

    static void recur(int n) {
        if (n == M + 1) {
            for (int i = 1; i <= M; i++) {
                sb.append(nums[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = nums[n - 1]; i <= N; i++) {
            nums[n] = i;
            recur(n + 1);
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[M + 1];
        nums[0] = 1;
        recur(1);

        System.out.println(sb);
    }
}