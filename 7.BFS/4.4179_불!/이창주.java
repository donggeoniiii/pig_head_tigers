import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        char[][] map = new char[N][M];
        int[][] distJ = new int[N][M];
        int[][] distF = new int[N][M];

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        for (int i = 0; i < N; i++) {
            Arrays.fill(distF[i], -1);
            Arrays.fill(distJ[i], -1);

        }
        Queue<Point> queueJ = new LinkedList<>();
        Queue<Point> queueF = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = s.charAt(j);
                map[i][j] = c;
                if (c == 'F') {
                    queueF.offer(new Point(i, j));
                    distF[i][j] = 0;
                }
                else if (c == 'J') {
                    queueJ.offer(new Point(i, j));
                    distJ[i][j] = 0;
                }
            }
        }
        while (!queueF.isEmpty()) {
            Point poll = queueF.poll();
            for (int i = 0; i < 4; i++) {
                int x = poll.x + dx[i];
                int y = poll.y + dy[i];

                if (x < 0 || y < 0 || x >= N || y >= M) {
                    continue;
                }
                if (map[x][y] == '#' || distF[x][y] >= 0) continue;

                queueF.offer(new Point(x, y));
                distF[x][y] = distF[poll.x][poll.y] + 1;
            }
        }


        while (!queueJ.isEmpty()) {
            Point poll = queueJ.poll();
            for (int i = 0; i < 4; i++) {
                int x = poll.x + dx[i];
                int y = poll.y + dy[i];
                int temp = distJ[poll.x][poll.y] + 1;
                if (x < 0 || y < 0 || x >= N || y >= M) {
                    System.out.println(temp);
                    return;
                }
                if(map[x][y] == '#' || distJ[x][y] >= 0) continue;
                if(distF[x][y] <= temp && distF[x][y] >= 0 ) continue;
                queueJ.offer(new Point(x, y));
                distJ[x][y] = temp;
            }
        }

        System.out.println("IMPOSSIBLE");
    }
    static class Point{
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}