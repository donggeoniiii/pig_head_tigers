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
    static StringBuilder sb = new StringBuilder();

    static void recur(int n, int start) {
        if (n == M) {
            for (int i = 0; i < M; i++) {
                sb.append(choice[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        int temp = -1;
        for (int i = start; i < N; i++) {
            if(temp == arr[i]) continue;
            temp = arr[i];
            choice[n] = arr[i];
            recur(n+1,i);
        }
    }
    public static void main(String[] args) throws IOException {
        // 같은 수를 여러 번 골라도 된다.
        // 비내림차순 이어야한다.
        // 중복되는 수열을 여러 번 출력 하면 안되고
        // 수열은 사전 순으로 증가하는 순서로 출력해야 한다.

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        choice = new int[M];
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st2.nextToken());
        }
        Arrays.sort(arr);
        recur(0, 0);
        System.out.println(sb);
    }
}