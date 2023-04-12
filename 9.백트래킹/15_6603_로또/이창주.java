import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int K;
    final static int N = 6;
    static int[] nums;
    static int[] choice;
    static StringBuilder sb = new StringBuilder();


    static void recur(int n, int start) {

        if(n + (K - start) < N) return;
        if (n == N) {
            for (int i = 0; i < N; i++) {
                sb.append(choice[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = start; i < K; i++) {
            choice[n] = nums[i];
            recur(n+1,i+1);
        }
    }

    public static void main(String[] args) throws IOException {
        // 집합 S와 k가 주어졌을 때 수를 고르는 모든 방법을 구하는 프로그램
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = "";
        while (!(s = br.readLine()).equals("0")) {
            StringTokenizer st = new StringTokenizer(s);
            K = Integer.parseInt(st.nextToken());
            nums = new int[K];
            choice = new int[6];
            for (int i = 0; i < K; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }

            recur(0, 0);
            sb.append("\n");
        }

        System.out.println(sb);
    }
}