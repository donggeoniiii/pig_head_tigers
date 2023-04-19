import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    // 배열 입력 받기
    static int[][][] map = new int[5][5][5];

    // 면을 쌓아 올린 새로운 정육면체
    static int[][][] nMap = new int[5][5][5];

    // BFS 탐색을 위한 방문 배열
    static boolean[][][] vis = new boolean[5][5][5];

    // 쌓는 순서 저장할 배열
    static int[] choice = new int[5];
    // 쌓는 순서 방문 배열
    static boolean[] vc = new boolean[5];

    // 최솟값
    static int min = Integer.MAX_VALUE;

    // 델타탐색
    static int[] dx = {1, 0, -1, 0, 0, 0};
    static int[] dy = {0, 1, 0, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};

    static class Node {
        int n;
        int x;
        int y;
        int time;

        public Node(int n, int x, int y, int time) {
            this.n = n;
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    // 입구에서 출발하여 출구에 도착하는 메소드
    static void go(int n, int sx, int sy) {
        if(min == 12) return;

        // 출구 있는지 확인하기
        if (nMap[4 - n][4 - sx][4 - sy] == 0) return;

        Queue<Node> q = new LinkedList<>();
        vis = new boolean[5][5][5];
        q.offer(new Node(n, sx, sy, 0));
        vis[n][sx][sy] = true;

        while (!q.isEmpty()) {

            Node poll = q.poll();

            if (poll.n == 4 - n) {
                if ((poll.x == 4 - sx && poll.y == 4 - sy)) {
                    min = min < poll.time ? min : poll.time;
                    return;
                }
            }
            if(poll.time == min) return;
            for (int i = 0; i < 6; i++) {
                int z = poll.n + dz[i];
                int x = poll.x + dx[i];
                int y = poll.y + dy[i];

                if (z < 0 || x < 0 || y < 0 || x >= 5 || y >= 5 || z >= 5) continue;
                if (nMap[z][x][y] == 0) continue;
                if (vis[z][x][y]) continue;
                q.offer(new Node(z, x, y, poll.time + 1));
                vis[z][x][y] = true;
            }
        }
    }

    // 면을 쌓는 메소드
    static void st(int n) {
        if (n == 5) {

            // 3차원 배열
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    for (int k = 0; k < 5; k++) {
                        nMap[i][j][k] = map[choice[i]][j][k];
                    }
                }
            }
            // 첫번째 면에서 시작 꼭짓점 찾기
            // 왼쪽위
            if (nMap[0][0][0] == 1) {
                go(0, 0, 0);
            }

        }
        for (int i = 0; i < 5; i++) {
            if (vc[i]) continue;
            choice[n] = i;
            vc[i] = true;
            st(n + 1);
            vc[i] = false;
        }
    }

    // 면을 회전하는 메소드
    static void rotate(int n, int time) {
        int[][] temp = new int[5][5];

        for (int t = 0; t < time; t++) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    temp[i][j] = map[n][4 - j][i];
                }
            }
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    map[n][i][j] = temp[i][j];
                }
            }
        }
    }


    // 회전하는 면을 선택하는 메소드
    static void recur(int n) {
        if (n == 5) {
            st(0);
            return;
        }
        for (int i = 0; i < 4; i++) {
            rotate(n, i);
            recur(n + 1);
            rotate(n, 4 - i);
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 5; k++) {
                    map[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }
        recur(0);
        if (min == Integer.MAX_VALUE) min = -1;
        System.out.println(min);
    }
}