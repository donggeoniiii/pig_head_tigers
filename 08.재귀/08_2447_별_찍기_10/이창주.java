import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static char[][] map;

    static void recur(int x, int y, int n, boolean star) {
        if (n == 1) {
            if (star) map[x][y] = '*';
            else map[x][y] = ' ';
            return;
        }

        recur(x, y, n / 3, star);
        recur(x, y + n / 3, n / 3, star);
        recur(x, y + n / 3 * 2, n / 3, star);
        recur(x + n / 3, y, n / 3, star);
        recur(x + n / 3, y + n / 3, n / 3, false);
        recur(x + n / 3, y + n / 3 * 2, n / 3, star);
        recur(x + n / 3 * 2, y, n / 3, star);
        recur(x + n / 3 * 2, y + n / 3, n / 3, star);
        recur(x + n / 3 * 2, y + n / 3 * 2, n / 3, star);
    }

    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        recur(0, 0, N, true);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }
}