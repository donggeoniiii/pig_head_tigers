import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int M;

    static int[][] map;
    static int[][] bada;
    static int[][] bada2;
    static boolean[][] vis;
    static int cnt;
    static int time;

    static Queue<int[]> queue = new LinkedList<>();

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};


    static void bfs() {
        while (cnt < 2) {

            // 빙산 시간 지나기
            time++;
            bada2 = new int[N][M];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    bada2[i][j] = bada[i][j];
                }
            }
            for (int i = 1; i < N - 1; i++) {
                for (int j = 1; j < M - 1; j++) {
                    if (map[i][j] > 0) {
                        map[i][j] -= bada2[i][j];
                        if (map[i][j] <= 0) {
                            map[i][j] = 0;
                            for (int k = 0; k < 4; k++) {
                                int x = i + dx[k];
                                int y = j + dy[k];
                                bada[x][y]++;
                            }
                        }
                    }
                }
            }
            cnt = 0;
            vis = new boolean[N][M];
            for (int i = 1; i < N-1; i++) {
                for (int j = 0; j < M-1; j++) {
                    if (map[i][j] > 0 && !vis[i][j]) {
                        vis[i][j] = true;
                        queue.offer(new int[]{i, j});
                        cnt++;
                        while (!queue.isEmpty()) {
                            int[] poll = queue.poll();
                            for (int k = 0; k < 4; k++) {
                                int x = poll[0] + dx[k];
                                int y = poll[1] + dy[k];

                                if (x < 0 || y < 0 || x >= N - 1 || y >= M - 1) {
                                    continue;
                                }
                                if(map[x][y] == 0 || vis[x][y]) continue;

                                vis[x][y] = true;
                                queue.offer(new int[]{x, y});
                            }
                        }
                    }
                }
            }
            if (cnt == 0) {
                System.out.println(0);
                return;
            }
            // 빙산 개수 새기

        }
        System.out.println(time);
    }


    public static void main(String[] args) throws IOException {
        /*
        반도체는 인간의 뇌를 카피한것
        의사결정 기억, 감각 인지
        상상하는 모든 것을 반도체로 실현하고자 함

        빙산이 녹고 있다.
        바닷물에 많이 접해 있는 부분에서 더 빨리 줄어들기 때문에,
        동서 남북 4개에 붙어 있는 0이 저장된 칸 수 만큼 줄어든다.

        한 덩어리의 빙산이 주어질 때
        두 덩어리 이상으로 분리되는 최초의 시간을 구하라

        두 덩어리 이상으로 분리되지 않으면 0을 출력한다.


        2차원 배열에 입력받고
        0보다 크면 주변 바다 계산하여
        깎기
        한 번 씩 모두 깎고 나면
        빙산이 분리되었는지 탐색

        분리되었다면 출력
        빙산의 개수가 0 이라면 0출력

         */


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        bada = new int[N][M];
        for (int i = 0; i < N; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st2.nextToken());
                if (map[i][j] == 0) {
                    for (int k = 0; k < 4; k++) {
                        int x = i + dx[k];
                        int y = j + dy[k];

                        if(x < 0 || y < 0 ||  x >= N || y >= M) continue;
                        bada[x][y]++;
                    }
                }
            }
        }

        bfs();
    }
}