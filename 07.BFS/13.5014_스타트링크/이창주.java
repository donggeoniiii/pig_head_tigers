import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int f;
    static int g;
    static int s;
    static int u;
    static int d;
    static int[] e;

    static int[] dx;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        f = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        u = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        dx = new int[]{u, d};
        e = new int[f + 1];
        Arrays.fill(e, -1);
    }

    static void upDown() {
        e[s] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.offer(s);
        while (!q.isEmpty()) {
            Integer poll = q.poll();
            int up = poll + dx[0];
            int down = poll - dx[1];

            if (up <= f && e[up] == -1) {
                q.offer(up);
                e[up] = e[poll] + 1;
            }
            if (down > 0 && e[down] == -1) {
                q.offer(down);
                e[down] = e[poll] + 1;
            }
        }

        if(e[g] == -1) System.out.println("use the stairs");
        else System.out.println(e[g]);

    }

    public static void main(String[] args) throws IOException {
        input();
        upDown();
    }
}