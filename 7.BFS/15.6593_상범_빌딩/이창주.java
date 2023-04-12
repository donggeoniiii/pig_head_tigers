import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


// 상범빌딩
// 정육면체
// 동 서 남 북 상 하

// S 상범 E 탈출 # 벽
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 0 0 0 나올 때 까지 입력 받기
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int L = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            if(L == 0) return;
            // 3차원 배열 BFS
            // 3차원 배열
            char[][][] building = new char[L][R][C];
            // 3차원 배열 델타
            int[] dx = {1, -1, 0, 0, 0, 0};
            int[] dy = {0, 0, 1, -1, 0, 0};
            int[] dz = {0, 0, 0, 0, 1, -1};
            // dist 배열
            int[][][] dist = new int[L][R][C];
            // BFS 진행할 큐
            Queue<int[]> queue = new LinkedList<>();

            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    String s = br.readLine();

                    for (int k = 0; k < C; k++) {
                        building[i][j][k] = s.charAt(k);
                        if (building[i][j][k] == 'S') {
                            queue.offer(new int[]{i, j, k});
                            dist[i][j][k] = 1;
                        }
                    }
                }
                String s1 = br.readLine();
            }
            int min = 0;
            while (!queue.isEmpty()) {
                int[] poll = queue.poll();

                for (int i = 0; i < 6; i++) {
                    int z = poll[0] + dz[i];
                    int x = poll[1] + dx[i];
                    int y = poll[2] + dy[i];

                    if (z < 0 || x < 0 || y < 0 || z >= L || x >= R || y >= C) {
                        continue;
                    }
                    if(dist[z][x][y] > 0 || building[z][x][y] == '#') continue;
                    if (building[z][x][y] == 'E') {
                        min = dist[poll[0]][poll[1]][poll[2]];
                        while (!queue.isEmpty()) {
                            queue.poll();
                        }
                    } else {
                        queue.offer(new int[]{z, x, y});
                        dist[z][x][y] = dist[poll[0]][poll[1]][poll[2]] + 1;
                    }
                }
            }
            if(min == 0) System.out.println("Trapped!");
            else System.out.println("Escaped in " + min+" minute(s).");

        }


    }
}