import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    // 나이트가 이동할 수 있는 건
    static int[] dx = {-2, 2, 2, -2, 1, -1, 1, -1};
    static int[] dy = {1, 1, -1, -1, 2, 2, -2, -2};

    static int[][] dist;
    static int I;
    static int TX;
    static int TY;
    static Queue<int[]> queue = new LinkedList<>();

    static void bfs() {
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            if (poll[0] == TX && poll[1] == TY) {
                System.out.println(dist[poll[0]][poll[1]]);
                queue.clear();
                return;
            }
            for (int i = 0; i < dx.length; i++) {
                int x = poll[0] + dx[i];
                int y = poll[1] + dy[i];


                if(x < 0 || y < 0 || x >=I || y >= I) continue;
                if(dist[x][y] != -1) continue;
                dist[x][y] = dist[poll[0]][poll[1]] + 1;
                queue.offer(new int[]{x, y});
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            I = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            StringTokenizer st2 = new StringTokenizer(br.readLine());

            TX = Integer.parseInt(st2.nextToken());
            TY = Integer.parseInt(st2.nextToken());

            dist = new int[I][I];
            for (int j = 0; j < I; j++) {
                Arrays.fill(dist[j], -1);
            }
            dist[N][M] = 0;
            queue.offer(new int[]{N, M});

            bfs();

        }
    }
}