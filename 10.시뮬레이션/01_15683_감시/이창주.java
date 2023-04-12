import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    // 지도의 크기
    static int H;
    static int W;

    // 지도 맵
    static int[][] map;
    // 방문배열
    static boolean[][] vis;

    static boolean[][] vis2;

    // CCTV 개수
    static int N = 0;

    // CCTV 위치 배열
    static int[][] cctv;
    // CCTV 방향 배열 방향은 (0 오른쪽, 1왼쪽, 2위쪽, 3아래쪽)
    static int[] dir;

    // 사각지대 개수
    static int cnt = 0;
    static int cnt2 = 0;

    // 사각지대 최소 개수
    static int min = Integer.MAX_VALUE;

    // 델타 배열
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    // 씨씨티비비비
    static void oneWay(int a, int x, int y) {

        while (true) {
            x += dx[a];
            y += dy[a];
            if (x < 0 || y < 0 || x >= H || y >= W) return;
            if (map[x][y] == 6) return;
            if (vis2[x][y]) {
                cnt2--;
                vis2[x][y] = false;
            }
        }
    }

    // 사각지대 구하기
    static int blind() {

        // 방문 배열 복사

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                vis2[i][j] = vis[i][j];
            }
        }

        // 사각지대 개수 복사
        cnt2 = cnt;

        // 이제 사각지대를 없애보자
        for (int i = 0; i < N; i++) {
            int type = map[cctv[i][0]][cctv[i][1]];
            int x = cctv[i][0];
            int y = cctv[i][1];

            // 0 하 1 상 2 우 3 좌
            switch (type) {
                case 1:
                    oneWay(dir[i], x, y);
                    break;
                case 2:
                    if (dir[i] % 2 == 0) {
                        oneWay(0, x, y);
                        oneWay(1, x, y);
                    } else {
                        oneWay(2, x, y);
                        oneWay(3, x, y);
                    }
                    break;
                case 3:

                    switch (dir[i]) {
                        // 우상
                        case 0:
                            oneWay(1, x, y);
                            oneWay(2, x, y);
                            break;
                        // 좌상
                        case 1:
                            oneWay(3, x, y);
                            oneWay(1, x, y);
                            break;

                        // 좌하
                        case 2:
                            oneWay(0, x, y);
                            oneWay(3, x, y);
                            break;
                        // 우하
                        case 3:
                            oneWay(0, x, y);
                            oneWay(2, x, y);
                            break;
                        default:
                            break;
                    }
                    break;
                case 4:

                    switch (dir[i]) {
                        // 좌상우
                        case 0:
                            oneWay(3, x, y);
                            oneWay(1, x, y);
                            oneWay(2, x, y);
                            break;
                        // 좌상하
                        case 1:
                            oneWay(3, x, y);
                            oneWay(1, x, y);
                            oneWay(0, x, y);
                            break;
                        // 좌하우
                        case 2:
                            oneWay(3, x, y);
                            oneWay(0, x, y);
                            oneWay(2, x, y);
                            break;
                        // 하우상
                        case 3:
                            oneWay(0, x, y);
                            oneWay(1, x, y);
                            oneWay(2, x, y);
                            break;
                        default:
                            break;
                    }

                    break;

                case 5:
                    oneWay(0, x, y);
                    oneWay(1, x, y);
                    oneWay(2, x, y);
                    oneWay(3, x, y);
                    break;
                default:
                    break;
            }

        }

        return cnt2;
    }

    // 백트래킹
    static void recur(int n) {

        // 이미 최소값을 탐색 했다면
        if (min == 0) return;

        // CCTV 방향을 모두 골랐다면
        if (N == n) {
            min = Math.min(blind(), min);
            return;
        }

        // 방향 고르기
        for (int i = 0; i < 4; i++) {
            // 방향 고를 때 2번 이나 5번 cctv는 4방향이 아니다.
            dir[n] = i;
            recur(n + 1);
        }
    }


    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        map = new int[H][W];
        vis = new boolean[H][W];
        vis2 = new boolean[H][W];


        // 맵정보입력
        for (int i = 0; i < H; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st2.nextToken());
                // 맵이 0 이면 감시해야하는 공간
                if (map[i][j] == 0) {
                    cnt++;
                    vis[i][j] = true;
                }
                // CCTV 이면 CCTV 개수 증가
                else if (0 < map[i][j] && map[i][j] < 6) {
                    N++;
                }
            }
        }

        // cctv 정보 입력
        dir = new int[N];
        cctv = new int[N][2];
        int idx = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] > 0 && map[i][j] < 6) {
                    cctv[idx][0] = i;
                    cctv[idx++][1] = j;
                }
            }
        }
        recur(0);
        // 출력
        System.out.println(min);
    }
}