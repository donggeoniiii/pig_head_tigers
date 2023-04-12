import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {


    static int N;
    static int[][] map;
    static int[] count = new int[3];


    static boolean isPaper(int x, int y, int n) {

        int same = map[x][y];

        for (int i = x; i < x + n; i++) {
            for (int j = y; j < y + n; j++) {
                if (same != map[i][j]) return false;
            }

        }
        count[same + 1]++;
        return true;
    }

    static void recur(int x, int y, int a) {


        boolean isAll = isPaper(x, y, a);

        if (a == 1 || isAll) return;


        // 9방향으로 나누기
        recur(x, y, a / 3);
        recur(x, y + a / 3, a / 3);
        recur(x, y + a / 3 * 2, a / 3);
        recur(x + a / 3, y, a / 3);
        recur(x + a / 3, y + a / 3, a / 3);
        recur(x + a / 3, y + a / 3 * 2, a / 3);
        recur(x + a / 3 * 2, y, a / 3);
        recur(x + a / 3 * 2, y + a / 3, a / 3);
        recur(x + a / 3 * 2, y + a / 3 * 2, a / 3);

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        recur(0, 0, N);
        for (int i = 0; i < 3; i++) {
            System.out.println(count[i]);
        }


    }
}