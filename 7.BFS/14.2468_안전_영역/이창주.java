import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] ground = new int[n][n];

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        int max = 1;
        int maxH = 0;
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                ground[i][j] = Integer.parseInt(st.nextToken());
                maxH = maxH > ground[i][j] ? maxH : ground[i][j];
            }
        }

        for (int i = 1; i < maxH; i++) {
            boolean[][] vis = new boolean[n][n];
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (ground[j][k] > i && !vis[j][k]) {
                        queue.offer(new int[]{j, k});
                        vis[j][k] = true;
                        cnt++;
                        while (!queue.isEmpty()) {
                            int[] poll = queue.poll();
                            for (int l = 0; l < 4; l++) {
                                int x = poll[0] + dx[l];
                                int y = poll[1] + dy[l];

                                if(x< 0 || y <0 || x >= n || y >= n) continue;
                                if (vis[x][y] || ground[x][y] <= i) continue;

                                queue.offer(new int[]{x, y});
                                vis[x][y] = true;

                            }
                        }
                    }
                }
            }
            max = max > cnt ? max : cnt;
        }
        System.out.println(max);
    }
}