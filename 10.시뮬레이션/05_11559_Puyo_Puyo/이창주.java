import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    // 뿌요뿌요

    // 총 12줄
    static int N = 12;

    // 맵
    static char[][] map = new char[12][6];

    // 방문처리 배열

    static boolean[][] vis;

    // 연쇄 카운트
    static int cnt = 0;

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    // 4방향 뿌요탐색 메소드
    static boolean isPuyo(char type, int x, int y) {

        Queue<int[]> q = new LinkedList<>();
        Queue<int[]> point = new LinkedList<>();
        q.offer(new int[]{x, y});
        point.offer(new int[]{x, y});
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            for (int i = 0; i < 4; i++) {
                int xx = poll[0] + dx[i];
                int yy = poll[1] + dy[i];
                if (xx < 0 || yy < 0 || xx >= N || yy >= 6 ) continue;
                if (vis[xx][yy] || map[xx][yy] != type) continue;
                q.offer(new int[]{xx, yy});
                vis[xx][yy] = true;
                point.offer(new int[]{xx, yy});
            }

        }

        if (point.size() > 3) {
            while (!point.isEmpty()) {
                int[] poll = point.poll();
                map[poll[0]][poll[1]] = '.';
            }
            return true;
        } else return false;
    }

    // 중력작용 메소드
    static void gravity() {
        for (int i = 0; i < 6; i++) {
            int idx = N - 1;
            for (int j = N - 1; j >= 0; j--) {
                if (map[j][i] != '.') {
                    char temp = map[j][i];
                    map[j][i] = '.';
                    map[idx--][i] = temp;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < 6; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        // 연쇄작용이 일어나는지 플래그
        boolean flag = true;

        while (flag) {
            flag = false;
            vis = new boolean[N][6];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < 6; j++) {
                    if (vis[i][j] || map[i][j] == '.') continue;
                    vis[i][j] = true;
                    if (isPuyo(map[i][j], i, j)) flag = true;
                }
            }
            if (flag) {
                gravity();
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}