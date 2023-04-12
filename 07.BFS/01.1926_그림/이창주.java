import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    public static void main(String[] args) throws IOException {
        // 그림의 개수
        // 그림 중 넓이가 가장 넓은 것의 넓이

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = 0;
        int max = 0;
        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0};

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] paper = new int[N][M];
        boolean[][] vis = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                paper[i][j] = Integer.parseInt(st2.nextToken());
            }
        }

        Queue<Node> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int area = 0;
                if (paper[i][j] == 1 && !vis[i][j]) {
                    vis[i][j] = true;
                    queue.offer(new Node(i, j));
                    area++;
                    cnt++;
                    while (!queue.isEmpty()) {
                        Node poll = queue.poll();

                        for (int k = 0; k < 4; k++) {
                            int x = poll.x + dx[k];
                            int y = poll.y + dy[k];
                            if(x < 0 || x>=N || y<0 || y>=M) continue;
                            if (paper[x][y] == 1 && !vis[x][y]) {
                                area++;
                                vis[x][y] = true;
                                queue.offer(new Node(x, y));
                            }
                        }
                    }
                    max = Math.max(max, area);
                }
            }
        }
        System.out.println(cnt);
        System.out.println(max);
    }
}