import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
    1 -> 3 -> 3
    2 -> 1 -> 3 -> 3
    3 -> 3
    4 -> 7 -> 6 -> 4
    5 -> 3 -> 3
    6 -> 4 -> 7 -> 6
    7 -> 6 -> 4 -> 7

    첫 x와 마지막 x가 같으면

2 3 4 5 4

 1 -> 2 -> 3 -> 4 -> 5 -> 4
 2
 3
 4
 5

 */

public class Main {
    // 학생 수
    static int N;

    // 학생들의 일차원 배열
    static int[] student;
    // 이미 탐색되었다면 스킵할 수 있도록 방문 배열 방문하지 않음 0/ 속하지않음 1/ 속함 2
    static boolean[] vis;
    // 학생들의 연속 이동 담을 Stack
    static Deque<Integer> queue;
    // 스택에 담겼는지 배열
    static int cnt;
    // bfs
    static void bfs(int x) {
        queue = new LinkedList<>();

        queue.offer(x);
        vis[x] = true;
        int a = student[queue.peek()];
        while (!vis[a]) {
            queue.offer(a);
            vis[a] = true;
            a = student[queue.peekLast()];
        }
        while (!queue.isEmpty() && queue.poll() != a) {
            cnt++;
        }
        return;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 0; tc < T; tc++) {
            cnt = 0;
            N = Integer.parseInt(br.readLine());
            student = new int[N + 1];
            vis = new boolean[N + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                student[i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 1; i <= N; i++) {
                if(vis[i]) continue;
                bfs(i);
            }
            sb.append(cnt).append("\n");
        }
        System.out.println(sb);
    }
}