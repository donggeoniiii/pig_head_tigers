import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    // 입력
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // 지도 크기
    static int n;
    // 단지 배열
    static boolean[][] apart;

    // 델타탐색
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    // BFS 처리할 Queue 케이스가 하나
    static Queue<int[]> queue = new LinkedList<>();
    // 단지 넓이 배열
    static int[] w = new int[626];
    // 단지 넓이를 정렬 해야함. 빈도수, 버블 ,삽입,
    // 최대 단지수는 ? 25*25 625
    // 빈도수 정렬 선택
    static int cnt = 0;

    // BFS
    static void bfs() {
        int tempW = 1;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            for (int i = 0; i < 4; i++) {
                int x = poll[0] + dx[i];
                int y = poll[1] + dy[i];
                if(x < 0 || y <0 || x >= n || y >= n || !apart[x][y]) continue;

                apart[x][y] = false;
                tempW++;
                queue.offer(new int[]{x, y});
            }
        }
        w[tempW]++;
    }

    // 입력
    static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        apart = new boolean[n][n];
        // 입력 받기
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < n; j++) {
                if (s.charAt(j) == '1') {
                    apart[i][j] = true;
                }
            }
        }
    }
    static void output() {
        // 출력
        System.out.println(cnt);
        for (int i = 1; i < w.length; i++) {
            if (w[i] > 0) {
                while (w[i] > 0) {
                    System.out.println(i);
                    w[i]--;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {

        //입력
        input();

        // BFS 시작
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (apart[i][j]) {

                    apart[i][j] = false;
                    cnt++;
                    queue.offer(new int[]{i, j});
                    bfs();
                }
            }
        }
        //출력
        output();

    }
}