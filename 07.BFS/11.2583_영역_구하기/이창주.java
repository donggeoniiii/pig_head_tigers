import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
         // bfs
        // 직사각형의 꼭지점이 주어지고 해당 부분을 직사각형을 채우기
        // 여러 군데에서 시작하는 bfs
        // cnt && 넓이

        // 모눈종이 배열
        boolean[][] graphPaper = new boolean[101][101];
        // 방문 여부 배열

        // cnt, 각 넓이 변수
        int cnt = 0;
        int w = 0;
        // 넓이를 담을 배열 카운트 정렬로
        int[] result = new int[10001];

        // bfs 수행할 queue
        Queue<int[]> queue = new LinkedList<>();

        // 상하좌우 탐색 배열
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < K; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st2.nextToken());
            int y1 = Integer.parseInt(st2.nextToken());
            int x2 = Integer.parseInt(st2.nextToken());
            int y2 = Integer.parseInt(st2.nextToken());

            for (int j = x1; j < x2; j++) {
                for (int k = y1; k < y2; k++) {
                    graphPaper[j][k] = true;
                }
            }
        }

        for (int j = 0; j < N; j++) {
            for (int k = 0; k < M; k++) {
                if (!graphPaper[j][k]) {
                    queue.offer(new int[]{j, k});
                    graphPaper[j][k] = true;
                    cnt++;
                    w = 1;
                    while (!queue.isEmpty()) {
                        int[] poll = queue.poll();
                        for (int l = 0; l < 4; l++) {
                            int x = poll[0] + dx[l];
                            int y = poll[1] + dy[l];

                            if(x< 0 || y < 0 || x >= N || y >= M) continue;
                            if(graphPaper[x][y]) continue;
                            graphPaper[x][y] = true;
                            w++;
                            queue.offer(new int[]{x, y});
                        }
                    }
                    result[w]++;
                }
            }
        }
        System.out.println(cnt);
        for (int i = 0; i < result.length; i++) {
            if (result[i] != 0) {
                while (result[i] != 0) {
                    System.out.printf("%d ", i);
                    result[i]--;
                }
            }
        }
    }
}