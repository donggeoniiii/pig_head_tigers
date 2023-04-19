import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int M;
    static boolean[][] map;
    static boolean[][] vis;
    static int r;
    static int c;
    static int d;
    static int cnt;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};


    static boolean rotate() {
        for (int i = 0; i < 4; i++) {
            int x = r + dx[(d + 3 - i) % 4];
            int y = c + dy[(d + 3 - i) % 4];
            if (x < 0 || y < 0 || x >= N || y >= M || vis[x][y]) continue;
            if (!map[x][y]) {
                r = x;
                c = y;
                d = (d + 3 - i) % 4;
                return true;
            }
        }

        return false;
    }

    static void clean() {

        while (true) {
            if(!vis[r][c]) cnt++;
            vis[r][c] = true;
            if (!rotate()) {
                // 방향에서 뒤로 가기
                int nr = r + dx[(d + 2) % 4];
                int nc = c + dy[(d + 2) % 4];
                if (nr < 0 || nc < 0 || nr >= N || nc >= M || map[nr][nc]) return;
                r = nr;
                c = nc;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new boolean[N][M];
        vis = new boolean[N][M];
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                if (st.nextToken().equals("1")) map[i][j] = true;
            }
        }

        clean();
        System.out.println(cnt);
    }
}