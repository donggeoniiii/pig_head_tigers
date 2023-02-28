// 토마토 배열
// dist 에 0 이라면 -1로
// 3중 포문으로 익은 토마토 큐에 넣어주기
// BFS탐색
// dist 중에 가장 큰 값 출력
// dist 에 -1이 있다면 -1 출력
// dx[]
// dy[]
// dz[]

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int H;
    static int N;
    static int M;

    static int[][][] tomato;

    static int[][][] dist;
    static int[] dx = {1, 0, -1, 0, 0, 0};
    static int[] dy = {0, 1, 0, -1, 0 ,0};
    static int[] dz = {0, 0, 0, 0, 1, -1};
    static Queue<int[]> queue = new LinkedList<>();

    static void bfs() {

        while (!queue.isEmpty()) {

            int[] poll = queue.poll();
            for (int i = 0; i < 6; i++) {
                int x = poll[1] + dx[i];
                int y = poll[2] + dy[i];
                int z = poll[0] + dz[i];

                if (x < 0 || y < 0 || x >= N || y >= M || z<0 || z >= H) continue;
                if (tomato[z][x][y] == -1 || dist[z][x][y] != -1) continue;

                dist[z][x][y] = dist[poll[0]][poll[1]][poll[2]] + 1;
                queue.offer(new int[]{z, x, y});
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        tomato = new int[H][N][M];
        dist = new int[H][N][M];

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                StringTokenizer st2 = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    int a = Integer.parseInt(st2.nextToken());
                    if (a == 0) {
                        dist[i][j][k] = -1;
                    } else {
                        tomato[i][j][k] = a;

                        if (a == 1) {
                            queue.offer(new int[]{i, j, k});
                        }
                    }
                }
            }
        }

        bfs();

        int max = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (dist[i][j][k] == -1) {
                        System.out.println(-1);
                        return;
                    }
                    max = Math.max(max, dist[i][j][k]);
                }
            }
        }
        System.out.println(max);
    }
}