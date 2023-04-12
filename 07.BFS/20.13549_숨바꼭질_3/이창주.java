import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        // 수빈이 순간이동 걷거나
        // x-1 x+1
        // 0초후에 2*X
        // 동생 찾기

        // 5 17 -> 10 15
        // 어디까지? 배열의 크기를 지정해야 하는지가 관건

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] time = new int[100001];
        Arrays.fill(time,-1);

        Queue<Integer> q = new LinkedList<>();
        q.offer(N);
        time[N] = 0;
        while (!q.isEmpty()) {
            int poll = q.poll();

            for (int i : new int[]{poll + 1, poll - 1, poll * 2}) {
                if(i < 0 || i >= time.length) continue;
                if(time[i] != -1 && time[i] <= time[poll]) continue;
                if (i == poll * 2) {
                    time[i] = time[poll];
                } else {
                    time[i] = time[poll] + 1;
                }
                q.offer(i);
                if(i == K) break;
            }
        }
        System.out.println(time[K]);
    }
}