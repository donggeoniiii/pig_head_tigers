import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        // count가 올라갈 때 , 너비 탐색
        // m,n에 도착 count 출력

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean[][] maze = new boolean[N][M];
        boolean[][] vis = new boolean[N][M];

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                if (s.charAt(j) == '1') {
                    maze[i][j] = true;
                }
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        vis[0][0] = true;

        queue.offer(new int[] {0, 0, 1});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            if (poll[0] == N-1 && poll[1] == M-1) {
                System.out.println(poll[2]);
                break;
            }
            for (int i = 0; i < 4; i++) {
                int x = poll[0] + dx[i];
                int y = poll[1] + dy[i];
                if(x < 0 || x >= N || y < 0 || y >= M) continue;
                if(!maze[x][y] || vis[x][y]) continue;
                queue.offer(new int[]{x, y, poll[2]+1});
                vis[x][y] = true;
            }
        }
    }
}