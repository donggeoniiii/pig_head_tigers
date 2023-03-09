import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int K;
    static int W;
    static int H;

    // 말처럼 움직이기
    static int[] hdx = {-2, -1, -2, -1, 1, 2, 2, 1};
    static int[] hdy = {1, 2, -1, -2, 2, 1, -1, -2};
    // 상하좌우
    static int[] dx = {1, -1, 0 ,0};
    static int[] dy = {0, 0, 1, -1};

    // map
    static boolean[][] map;
    static boolean[][][] dist;
    static Queue<Node> q = new LinkedList<>();

    static class Node {
        int x;
        int y;
        int num;

        int cnt;
        public Node(int x, int y, int num,int cnt) {
            this.x = x;
            this.y = y;
            this.num = num;
            this.cnt = cnt;
        }
    }
    // 방문여부 3차원배열 말 움직인 횟수만큼

    static int bfs() {

        while (!q.isEmpty()) {
            Node poll = q.poll();
            if(poll.x == H-1 && poll.y == W-1)
                return poll.cnt;


            if(poll.num < K) {
                for (int i = 0; i < hdx.length; i++) {
                    int x = poll.x + hdx[i];
                    int y = poll.y + hdy[i];

                    if(x <0 || y < 0 || x >= H || y >= W) continue;
                    if(map[x][y] || dist[x][y][poll.num+1]) continue;

                    q.offer(new Node(x,y, poll.num+1, poll.cnt+1));
                    dist[x][y][poll.num+1] = true;
                }
            }

            for (int i = 0; i < 4; i++) {
                int x = poll.x + dx[i];
                int y = poll.y + dy[i];

                if(x <0 || y < 0 || x >= H || y >= W) continue;
                if(map[x][y] || dist[x][y][poll.num]) continue;

                q.offer(new Node(x,y, poll.num, poll.cnt+1));
                dist[x][y][poll.num] = true;
            }

        }
        return -1;
    }
    public static void main(String[] args) throws IOException {
        // K번 말처럼
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        W =Integer.parseInt(st.nextToken());
        H =Integer.parseInt(st.nextToken());

        map = new boolean[H][W];
        dist = new boolean[H][W][K+1];
        for (int i = 0; i < H; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                if(Integer.parseInt(st2.nextToken()) == 1) map[i][j] = true;
            }
        }
        // 그외에는 인접한 칸으로만

        q.offer(new Node(0,0,0,0));
        System.out.println(bfs());

    }
}