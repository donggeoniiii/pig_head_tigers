import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Point {
        int x;
        int y;
        boolean is_fire;
        int cnt;

        public Point(int x, int y, boolean is_fire, int cnt) {
            this.x = x;
            this.y = y;
            this.is_fire = is_fire;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());

            int[] dx = {1, 0, -1, 0};
            int[] dy = {0, 1, 0, -1};

            char[][] building = new char[N][M];
            boolean[][] vis = new boolean[N][M];

            Queue<Point> queue = new LinkedList<>();

            for (int j = 0; j < N; j++) {
                String s = br.readLine();
                for (int k = 0; k < M; k++) {
                    char c = s.charAt(k);
                    building[j][k] = c;
                    if (c== '*') {
                        queue.offer(new Point(j, k, true , 1));
                    } else if (c == '@') {
                        queue.offer(new Point(j, k, false, 1));
                    }
                }
            }
            while (!queue.isEmpty() && queue.peek().is_fire) {
                queue.offer(queue.poll());
            }
            boolean is_IMPOSSIBLE = true;
            int result = 0;

            while (!queue.isEmpty()) {
                Point poll = queue.poll();

                if (poll.is_fire) {
                    for (int j = 0; j < 4; j++) {
                        int x = poll.x + dx[j];
                        int y = poll.y + dy[j];

                        if(x< 0 || y < 0 || x >=N || y >=M) continue;
                        if(building[x][y] == '*' || building[x][y] == '#') continue;
                        building[x][y] = '*';
                        queue.offer(new Point(x, y, true, poll.cnt+1));
                    }
                } else {
                    if (building[poll.x][poll.y] != '@') continue;
                    for (int j = 0; j < 4; j++) {
                        int x = poll.x + dx[j];
                        int y = poll.y + dy[j];

                        if (x < 0 || y < 0 || x >= N || y >= M) {
                            is_IMPOSSIBLE = false;
                            result = poll.cnt;
                            queue.clear();
                            break;
                        };
                        if (building[x][y] == '.') {
                            building[x][y] = '@';
                            queue.offer(new Point(x, y, false, poll.cnt + 1));
                        }
                    }
                }
            }
            if (is_IMPOSSIBLE) {
                System.out.println("IMPOSSIBLE");

            } else {
                System.out.println(result);

            }
        }
    }
}