import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    // 노트북 가로 세로
    static int N, M;

    // 스티커 가로 세로
    static int R, C;
    // 스티커 개수
    static int K;
    // 스티커 배열
    static boolean[][] sMap;

    // 회전 시킨 스티커 배열
    static boolean[][] tMap;

    // 노트북 배열
    static boolean[][] nMap;
    // 노트북에 붙은 스티커 숫자
    static int cnt;

    // 스티커 붙일 수 있는지 탐색하기

    // 회전 메소드
    static void turn(int dir) {
        if (dir == 0) {
            tMap = new boolean[R][C];
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    tMap[i][j] = sMap[i][j];
                }
            }
        } else if (dir == 1) {
            tMap = new boolean[C][R];
            for (int i = 0; i < C; i++) {
                for (int j = 0; j < R; j++) {
                    tMap[i][j] = sMap[R - j - 1][i];
                }
            }

        } else if (dir == 2) {
            tMap = new boolean[R][C];
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    tMap[i][j] = sMap[R - i - 1][C - j - 1];
                }
            }
        } else {
            tMap = new boolean[C][R];
            for (int i = 0; i < C; i++) {
                for (int j = 0; j < R; j++) {
                    tMap[i][j] = sMap[j][C - i - 1];
                }
            }
        }
    }

    // 붙일 수 있는지 확인
    static boolean can(int x, int y, int r, int c) {

        for (int i = x; i < x + r; i++) {
            for (int j = y; j < y + c; j++) {
                if (tMap[i - x][j - y] && nMap[i][j]) return false;
            }
        }
        return true;
    }

    // 붙일 수 있는지 확인하기
    static boolean isOk(int dir) {
        // 0,0에서 n-1, n-1까지 탐색하기

        int r = R;
        int c = C;
        if (dir == 1 || dir == 3) {
            r = C;
            c = R;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i + r > N || j + c > M) continue;

                if (can(i, j, r, c)) {
                    attach(i, j, r, c);
                    return true;
                }

            }
        }

        return false;

    }

    // 스티커 붙이기 때기
    static void attach(int x, int y, int r, int c) {

        for (int i = x; i < x + r; i++) {
            for (int j = y; j < y + c; j++) {
                if (tMap[i - x][j - y]) {

                    nMap[i][j] = true;
                    cnt++;
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        nMap = new boolean[N][M];

        // 스티커 붙이기
        for (int i = 0; i < K; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st2.nextToken());
            C = Integer.parseInt(st2.nextToken());
            sMap = new boolean[R][C];

            for (int j = 0; j < R; j++) {
                StringTokenizer st3 = new StringTokenizer(br.readLine());
                for (int k = 0; k < C; k++) {
                    if (st3.nextToken().equals("1")) {
                        sMap[j][k] = true;
                    }
                }
            }

            // 스티커 붙이기
            for (int j = 0; j < 4; j++) {
                turn(j);
                if (isOk(j)) {
                    break;
                }
            }
        }
        // 출력
        System.out.println(cnt);
    }
}