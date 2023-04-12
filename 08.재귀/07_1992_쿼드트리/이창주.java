import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static boolean[][] map;

    static boolean isSame(int x, int y, int n) {
                for (int i = x; i < x + n; i++) {
            for (int j = y; j < y + n; j++) {
                if (map[x][y] != map[i][j]) {
                    return false;
                }
            }
        }
        if (map[x][y]) sb.append("1");
        else sb.append("0");
        return true;
    }

    static void quadTree(int x, int y, int n) {
        if (isSame(x, y, n) || n == 1) return;

        sb.append("(");
        quadTree(x, y, n / 2);
        quadTree(x, y + n / 2, n / 2);
        quadTree(x + n / 2, y, n / 2);
        quadTree(x + n / 2, y + n / 2, n / 2);
        sb.append(")");
    }

    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        map = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                if (s.charAt(j) == '1') map[i][j] = true;
            }
        }

        quadTree(0, 0, N);
        System.out.println(sb);
    }
}