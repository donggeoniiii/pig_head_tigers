import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    // 지도 크기
    static int R;
    static int C;

    // 명령 개수
    static int order;

    // 지도
    static int[][] map;

    // 주사위에 적힌 숫자
    static int dice[] = new int[7];

    // 주사위 위치
    static int x;
    static int y;

    // 이동 
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};

    // 주사위 상태 나타내는 인덱스 위치
    static int top = 1;
    static int up = 2;
    static int right = 3;
    static int left = 4;
    static int down = 5;
    static int bottom = 6;

    // 주사위 굴리기
    static void roll(int dir) {
        int tempT = top;
        int tempB = bottom;
        int tempR = right;
        int tempL = left;
        int tempU = up;
        int tempD = down;

        switch (dir) {
            // 동쪽
            case 1:
                top = tempL;
                bottom = tempR;
                right = tempT;
                left = tempB;
                break;
            // 서쪽
            case 2:
                top = tempR;
                bottom = tempL;
                right = tempB;
                left = tempT;
                break;
            // 북쪽
            case 3:
                top = tempD;
                bottom = tempU;
                up = tempT;
                down = tempB;
                break;
            // 남쪽
            case 4:
                top = tempU;
                bottom = tempD;
                up = tempB;
                down = tempT;
                break;
            default:
                break;
        }
    }

    static void copy() {
        // 빈 맵이면 주사위 바닥면 칸에 복사
        if (map[x][y] == 0) {
            map[x][y] = dice[bottom];
        }
        // 빈 맵이 아니면 칸에 있는 것 주사위 바닥면으로 복사 후 칸 0
        else {
            dice[bottom] = map[x][y];
            map[x][y] = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 출력
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        order = Integer.parseInt(st.nextToken());


        map = new int[R][C];

        for (int i = 0; i < R; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st2.nextToken());
            }
        }
        StringTokenizer st3 = new StringTokenizer(br.readLine());
        for (int i = 0; i < order; i++) {
            int dir = Integer.parseInt(st3.nextToken());
            if (x + dx[dir] < 0 || y + dy[dir] < 0 || x + dx[dir] >= R || y + dy[dir] >= C) continue;
            x += dx[dir];
            y += dy[dir];
            roll(dir);
            copy();
            sb.append(dice[top]).append("\n");
        }
        System.out.println(sb);

    }
}