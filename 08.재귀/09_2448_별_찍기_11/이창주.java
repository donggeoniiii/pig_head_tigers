import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static char[][] map;
    static int N;

    static void recur(int x, int y, int n, boolean isTree) {

        if (!isTree) return;
        if (n == 3) {
            map[x][y] = '*';
            map[x + 1][y - 1] = '*';
            map[x + 1][y + 1] = '*';
            for (int i = -2; i < 3; i++) {
                map[x + 2][y + i] = '*';
            }
            return;
        }

        recur(x, y, n / 2, true);
        recur(x + n / 2, y, n / 2, false);
        recur(x + n / 2, y - n / 2, n / 2, true);
        recur(x + n / 2, y + n / 2, n / 2, true);

    }


    public static void main(String[] args) throws IOException {
        // 별찍기
        /*
            48 46 44 42 40

         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N * 2];

        recur(0, N, N, true);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 1; j < N *2; j++) {
                if (map[i][j] == '*') {
                    sb.append("*");
                } else sb.append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
/*


   *
  * *
 ***** <- 이게 하나네

   5xn 배열
   3이 되면 !
 */