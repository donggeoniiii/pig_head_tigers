import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

//        int[] dx = {1, -1, 2};


        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] dist = new int[100001];
        Arrays.fill(dist, -1);

        Queue<Integer> queue = new LinkedList<>();
        dist[N] = 0;
        queue.offer(N);
        while (!queue.isEmpty()) {
            int poll = queue.poll();
            int time = dist[poll] + 1;
            if (poll + 1 < 100001 && dist[poll + 1] == -1) {
                if (poll + 1 == K ) {
                    System.out.println(time);
                    return;
                }
                queue.offer(poll + 1);
                dist[poll + 1] = time;
            }
            if (poll - 1 >= 0 && dist[poll - 1] == -1) {

                if (poll - 1 == K) {
                    System.out.println(time);
                    return;
                }
                queue.offer(poll - 1);
                dist[poll - 1] = time;
            }
            if (poll * 2 < 100001 && dist[poll * 2] == -1) {
                if (poll * 2 == K) {
                    System.out.println(time);
                    return;
                }
                queue.offer(poll * 2);
                dist[poll * 2] = time;
            }
        }
        System.out.println(0);
    }
}