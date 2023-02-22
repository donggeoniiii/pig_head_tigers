import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        // 배추 BFS
        // 큐에 넣고
        // 방문여부
        // for ij로 탐색
        // 배추가 있다면 큐에 들어가서 인접한 배추들 탐색 cnt++

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            boolean[][] cabbage = new boolean[M][N];
            boolean[][] vis = new boolean[M][N];

            for (int j = 0; j < K; j++) {
                StringTokenizer st2 = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st2.nextToken());
                int y = Integer.parseInt(st2.nextToken());
                cabbage[x][y] = true;
            }
            Queue<int[]> queue = new LinkedList<>();
            int cnt = 0;
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < N; k++) {
                    if (cabbage[j][k] && !vis[j][k]) {
                        queue.offer(new int[]{j, k});
                        vis[j][k] = true;
                        cnt++;
                        while (!queue.isEmpty()) {
                            int[] poll = queue.poll();
                            for (int l = 0; l < 4; l++) {
                                int x = poll[0] + dx[l];
                                int y = poll[1] + dy[l];

                                if (x < 0 || y < 0 || x >= M || y >= N) {
                                    continue;
                                }
                                if(vis[x][y] || !cabbage[x][y]) continue;
                                queue.offer(new int[]{x, y});
                                vis[x][y] = true;
                            }
                        }
                    }
                }
            }
            System.out.println(cnt);
        }
    }
}