import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    // 칠공주 맵
    // 5*5를 선택할 수 있는 모든 경우의 수를 구해서
    // 첫시작점에서 이어지는지 확인해보자

    // 5*5 맵
    static char[][] map = new char[5][5];

    // 칠공주 방문배열
    static boolean[][] vis = new boolean[5][5];


    // 좌표 기억하는
    static Queue<int[]> q;
    // 맨처음 고른 애만 기억하고 그 애로 부터 한 붓 그리기 해서 큐로 탐색 7개 다 있으면 카운트 업
    // 0 1 2 3 4
    // 몫이 x 나머지가 y 5로 나누었을 때
    // 칠공주 선택배열
    static int[] start = new int[2];
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static int cnt = 0;

    static void recur(int n, int c, int yc) {


        if (yc >= 4) return;



        if (c == 7) {

            // 한 붓그리기 시작하자
            int temp = 1;
            boolean[][] vis2 = new boolean[5][5];
            q = new LinkedList<>();
            q.offer(start);
            vis2[start[0]][start[1]] = true;
            while (!q.isEmpty()) {
                int[] poll = q.poll();
                for (int i = 0; i < 4; i++) {
                    int x = poll[0] + dx[i];
                    int y = poll[1] + dy[i];
                    if (x < 0 || y < 0 || x >= 5 || y >= 5) continue;
                    if (!vis[x][y] || vis2[x][y]) continue;
                    temp++;
                    vis2[x][y] = true;
                    q.offer(new int[]{x, y});
                }
            }
            if(temp == 7) cnt++;
            return;
        }

        if(n > 24) return;

        vis[n / 5][n % 5] = true;

        if (c == 0) {
            start[0] = n / 5;
            start[1] = n % 5;
        }
        if (map[n / 5][n % 5] == 'Y') {
            recur(n + 1, c + 1, yc + 1);
        } else {
            recur(n + 1, c + 1, yc);
        }
        vis[n / 5][n % 5] = false;
        recur(n + 1, c, yc);
    }

    public static void main(String[] args) throws IOException {
        // 소문난 칠공주 결성
        // 맵은 무조건 5*5
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 5; i++) {
            String s = br.readLine();
            for (int j = 0; j < 5; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        recur(0, 0, 0);

        System.out.println(cnt);


    }
}
