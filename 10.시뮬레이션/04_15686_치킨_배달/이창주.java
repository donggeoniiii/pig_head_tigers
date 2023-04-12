import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    // 맵의 크기
    static int N;

    // 맵
    static int[][] map;

    // 골라야할 치킨 집 수
    static int M;

    // 치킨집 위치
    static List<Point> cList = new ArrayList<>();

    // 집의 위치
    static List<Point> hList = new ArrayList<>();

    // 고른 치킨집의 위치
    static Point[] pick;

    // 치킨거리 최솟값
    static int min = Integer.MAX_VALUE;

    // 좌표 노드
    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // 치킨 거리 구하기
    static void distance() {

        // 최솟값 임시저장
        int temp = 0;

        for (int i = 0; i < hList.size(); i++) {

            // a의 치킨거리
            int tempA = Integer.MAX_VALUE;
            for (int j = 0; j < M; j++) {
                tempA = Math.min(tempA, Math.abs(hList.get(i).x - pick[j].x) + Math.abs(hList.get(i).y - pick[j].y));
            }
            temp += tempA;
            if (min <= temp) return;
        }

        min = Math.min(min, temp);
    }

    // 백트래킹 치킨집 고르기
    static void pickC(int n, int c) {

        if (c == M) {
            distance();
            return;
        }
        if (n >= cList.size()) return;

        // 선택한다 안한다
        pick[c] = cList.get(n);
        pickC(n + 1, c + 1);

        pickC(n + 1, c);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        pick = new Point[M];
        for (int i = 0; i < N; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st2.nextToken());
                if (map[i][j] == 2) cList.add(new Point(i, j));
                else if (map[i][j] == 1) hList.add(new Point(i, j));
            }
        }
        pickC(0, 0);
        System.out.println(min);
    }
}