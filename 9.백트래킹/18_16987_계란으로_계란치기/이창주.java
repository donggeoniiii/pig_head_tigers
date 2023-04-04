import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    // 계란 숫자
    static int N;

    // 계란의 내구도 / 무게 배열
    static int[][] eggs;

    // 계란이 깨졌는지 확인하는 배열
    static boolean[] vis;

    static int max;

    static void fight(int n, int cnt) {
        if (n == N || cnt + 1 == N) {
            max = Math.max(max, cnt);
            return;
        }

        if (vis[n]) {
            fight(n + 1, cnt);
        } else {
            for (int i = 0; i < N; i++) {
                // 깨졌으면 넘어가
                if (vis[i]) continue;

                if (n == i) {
                    continue;
                }

                int temp = cnt;
                eggs[n][0] -= eggs[i][1];
                eggs[i][0] -= eggs[n][1];

                if (eggs[n][0] <= 0) {
                    vis[n] = true;
                    temp++;
                }
                if (eggs[i][0] <= 0) {
                    vis[i] = true;
                    temp++;
                }

                fight(n + 1, temp);

                eggs[n][0] += eggs[i][1];
                eggs[i][0] += eggs[n][1];

                vis[n] = false;
                vis[i] = false;

            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        eggs = new int[N][2];
        vis = new boolean[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            eggs[i][0] = Integer.parseInt(st.nextToken());
            eggs[i][1] = Integer.parseInt(st.nextToken());
        }
        fight(0, 0);
        System.out.println(max);
    }
}