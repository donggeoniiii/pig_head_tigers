import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
다리만들기

BFS로 섬의 번호를 짓기

1번섬 2번섬 3번섬

섬의 가장자리에서 출발하여 다른섬에 가장 먼저 닿는 거리
min값 갱신
 */
public class Main {
    static int cnt;
    static int[][] map;
    static boolean[][] vis;
    static int N;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static Queue<int[]> q = new LinkedList<>();

    static void bfs() {
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            for (int i = 0; i < 4; i++) {
                int x = poll[0] + dx[i];
                int y = poll[1] + dy[i];
                if (x < 0 || y < 0 || x >= N || y >= N) continue;
                if (map[x][y] == 0 || vis[x][y]) continue;
                q.offer(new int[]{x, y});
                vis[x][y] = true;
                map[x][y] = cnt;
            }
        }
    }

    static int bfs2(int number) {

        int[][] dist = new int[N][N];
        int distance = 0;
        while (!q.isEmpty()) {

            int[] poll = q.poll();

            for (int i = 0; i < 4; i++) {
                int x = poll[0] + dx[i];
                int y = poll[1] + dy[i];


                if (x < 0 || y < 0 || x >= N || y >= N) continue;
                if (map[x][y] == number || dist[x][y] != 0) continue;

                // 섬을 만나면
                if (map[x][y] != 0 && map[x][y] != number) return dist[poll[0]][poll[1]];


                dist[x][y] = dist[poll[0]][poll[1]] + 1;
                q.offer(new int[]{x, y});
            }
        }
        return Integer.MAX_VALUE;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        vis = new boolean[N][N];
        // 입력 받기
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 섬 개수에 따라 섬 번호 짓기
        // bfs()
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0 || vis[i][j]) continue;
                cnt++;
                map[i][j] = cnt;
                q.offer(new int[]{i, j});
                vis[i][j] = true;
                bfs();
            }
        }
        // 방문배열 초기화
        vis = new boolean[N][N];

        // 최소값
        int min = Integer.MAX_VALUE;
        // 가장 가까운 섬거리 찾기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0 || vis[i][j]) continue;
                // 큐 초기화 이전 탐색에서 섬끼리 만나면 탐색하지 않고 기다리고 있던 것들이 큐에 남아있음
                q = new LinkedList<>();
                q.offer(new int[]{i, j});
                vis[i][j] = true;

                min = Math.min(bfs2(map[i][j]), min);
            }
        }
        System.out.println(min);
    }
}