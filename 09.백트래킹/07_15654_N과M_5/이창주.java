import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[] arr;
    static int[] choice;
    static boolean[] dist;
    static StringBuilder sb = new StringBuilder();

    static void recur(int n) {


        if (n == M) {
            for (int i = 0; i < M; i++) {
                sb.append(choice[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {

            if(dist[i]) continue;
            dist[i] = true;
            choice[n] = arr[i];
            recur(n + 1);
            dist[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        arr = new int[N];
        choice = new int[N];
        dist = new boolean[N];
        
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st2.nextToken());
        }

        for (int i = 0; i < N - 1; i++) {
            for (int j = i+1; j < N; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        
        recur(0);
        System.out.println(sb);
    }
}