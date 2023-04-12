// 적록 색약이 아닌 사람
// 빨강 탐색
// 초록 탐색

// 적록 색약인 사람
// 빨강 || 초록 탐색

// 파랑 탐색 = 공통 으로 개수 추가

// wCnt = 0
// nCnt = 0

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    static char[][] painting;
    static boolean[][] visW;
    static boolean[][] visN;

    static int wCnt = 0;
    static int nCnt = 0;
    static int N;
    static Queue<int[]> queue = new LinkedList<>();

    static void NBFS() {

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            for (int i = 0; i < 4; i++) {
                int x = poll[0] + dx[i];
                int y = poll[1] + dy[i];

                if(x<0 || y < 0 || x >= N || y >= N) continue;
                if(visN[x][y] || painting[x][y] != painting[poll[0]][poll[1]]) continue;
                visN[x][y] = true;
                queue.offer(new int[]{x, y});
            }
        }
    }

    static void WBFS() {

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            for (int i = 0; i < 4; i++) {
                int x = poll[0] + dx[i];
                int y = poll[1] + dy[i];

                if(x<0 || y < 0 || x >= N || y >= N) continue;
                if(visW[x][y]) continue;
                char c = painting[poll[0]][poll[1]];
                if (c == 'B') {
                    if (c != painting[x][y]) continue;
                } else {
                    if (painting[x][y] == 'B') continue;
                }
                visW[x][y] = true;
                queue.offer(new int[]{x, y});
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        painting = new char[N][N];
        visW = new boolean[N][N];
        visN = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                painting[i][j] = s.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (!visW[i][j]) {
                    wCnt++;
                    queue.offer(new int[]{i, j});
                    WBFS();
                }

                if (!visN[i][j]) {
                    nCnt++;
                    queue.offer(new int[]{i, j});
                    NBFS();
                }
            }
        }
        System.out.println(nCnt+" " + wCnt);
    }
}