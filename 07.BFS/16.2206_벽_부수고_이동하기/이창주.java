import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    // 벽을 부쉈는지 여부
    // 이동 시간
    static class Node {
        int x;
        int y;
        boolean is_Crash;
        int time;

        public Node(int x, int y, boolean is_Crash, int time) {
            this.x = x;
            this.y = y;
            this.is_Crash = is_Crash;
            this.time = time;
        }
    }

    // 벽을 부수고 이동한 것과 부수지 않고 이동한 것
    // 벽 안부순애들은 똑같이
    // 방문한 곳이 있다면 가지 않아야 함
    // 새롭게 벽을 부술 때만 예외

    // 맵배열
    static boolean[][] map;

    // 방문여부
    static int[][] dist;

    static boolean[][] vis;
    static Queue<Node> q = new LinkedList<>();

    static int N;
    static int M;

    static int minTime;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static int bfs() {
        while (!q.isEmpty()) {

            Node poll = q.poll();

            if (poll.x == N - 1 && poll.y == M - 1) {
                return poll.time;
            }
            for (int i = 0; i < 4; i++) {

                int x = poll.x + dx[i];
                int y = poll.y + dy[i];
                // 좌표가 벗어난다면

                if(x<0 || y<0 || x >= N || y >=M) continue;
                // 벽을 이미 부쉈다면
                if (poll.is_Crash) {

                    // 벽인 곳은 못가!
                    if (map[x][y] || (dist[x][y] != 0 && dist[x][y] <= poll.time + 1)) continue;

                    // 벽이 아니고 방문하지 않은 곳은 가보기
                    q.offer(new Node(x, y, true, poll.time + 1));
                    dist[x][y] = poll.time + 1;
                    // 벽이 아니고 방문한 곳은 시간 비교

                }
                // 벽을 이미 부수지 않았다면
                else {
                    if(vis[x][y]) continue;
                    // 벽이라면
                    if (map[x][y]) {
                        q.offer(new Node(x, y, true, poll.time + 1));
                        dist[x][y] = poll.time + 1;
                    }
                    // 벽이 아니라면 안부순 방문여부 배열 사용
                    else {
                        q.offer(new Node(x, y, false, poll.time + 1));
                        if(dist[x][y] > poll.time +1) dist[x][y] = poll.time + 1;

                    }
                    vis[x][y] = true;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new boolean[N][M];
        dist = new int[N][M];
        vis = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                if(s.charAt(j) == '1') map[i][j] = true;
            }
        }
        dist[0][0] = 1;
        vis[0][0] = true;
        q.offer(new Node(0, 0, false, 1));
        System.out.println(bfs());
    }
}