import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static boolean[][] map;
    static int[] count = new int[2];

    static boolean isSame(int x, int y, int n) {
        boolean same = map[x][y];
        for (int i = x; i < x + n; i++) {
            for (int j = y; j < y + n; j++) {
                if (map[i][j] != same) {
                    return false;
                }
            }
        }

        if (same) count[1]++;
        else count[0]++;
        return true;
    }

    static void recur(int x, int y, int a) {

        if (isSame(x, y, a) || a == 1) return;

        recur(x, y, a / 2);
        recur(x + a / 2, y, a / 2);
        recur(x, y + a / 2, a / 2);
        recur(x + a / 2, y + a / 2, a / 2);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) map[i][j] = true;
            }
        }


        recur(0, 0, N);

        System.out.println(count[0]);
        System.out.println(count[1]);
    }
}